/*
 * Copyright (c) 2024 European Commission
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package eu.europa.ec.eudi.rqes.core

import eu.europa.ec.eudi.rqes.AlgorithmOID
import eu.europa.ec.eudi.rqes.AuthorizationCode
import eu.europa.ec.eudi.rqes.CSCClient
import eu.europa.ec.eudi.rqes.CSCClientConfig
import eu.europa.ec.eudi.rqes.CredentialAuthorizationRequestPrepared
import eu.europa.ec.eudi.rqes.CredentialAuthorized
import eu.europa.ec.eudi.rqes.CredentialInfo
import eu.europa.ec.eudi.rqes.CredentialsListRequest
import eu.europa.ec.eudi.rqes.DefaultHttpClientFactory
import eu.europa.ec.eudi.rqes.DocumentDigest
import eu.europa.ec.eudi.rqes.DocumentList
import eu.europa.ec.eudi.rqes.HashAlgorithmOID
import eu.europa.ec.eudi.rqes.HttpsUrl
import eu.europa.ec.eudi.rqes.RSSPMetadata
import eu.europa.ec.eudi.rqes.ServiceAccessAuthorized
import eu.europa.ec.eudi.rqes.ServiceAuthorizationRequestPrepared
import io.ktor.client.HttpClient
import java.security.cert.X509Certificate
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * The RQES service implementation.
 * This class provides the implementation of the RQES service.
 *
 * @property serviceEndpointUrl The RQES service endpoint URL.
 * @property config The RQES service configuration.
 * @property digestGenerator The document hash calculator.
 * @property signatureEmbedder The document signature embedder.
 * @property clientFactory The HTTP client factory. If this property is null, the default HTTP client factory will be used.
 *
 * @constructor Creates a RQES service implementation.
 * @param serviceEndpointUrl The RQES service endpoint URL.
 * @param config The RQES service configuration.
 * @param digestGenerator The document hash calculator.
 * @param signatureEmbedder The document signature embedder.
 * @param clientFactory The HTTP client factory. If this property is null, the default HTTP client factory will be used.
 */
class RQESServiceImpl(
    private val serviceEndpointUrl: String,
    private val config: CSCClientConfig,
    private val scaService: SCAService,
    private val clientFactory: (() -> HttpClient)? = null
) : RQESService {

    /**
     * The server state. This property is used to store the server state.
     */
    private var serverState: String? = null

    /**
     * The service authorization request prepared. This property is used to store the service authorization request prepared.
     */
    private var serviceAuthRequestPrepared: ServiceAuthorizationRequestPrepared? = null

    /**
     * The client. This property is used to store the client.
     */
    private lateinit var client: CSCClient

    /**
     * Get or create the client.
     * This method is used to get or create the client.
     * @return The client.
     */
    @Throws(Throwable::class)
    private suspend fun getOrCreateClient(): CSCClient {
        if (::client.isInitialized.not()) {
            client = CSCClient.oauth2(
                config,
                serviceEndpointUrl,
                clientFactory ?: DefaultHttpClientFactory
            )
                .getOrThrow()
        }
        return client
    }

    override suspend fun getRSSPMetadata(): Result<RSSPMetadata> {
        return try {
            Result.success(getOrCreateClient().rsspMetadata)
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }

    /* The method initializes the [serverState] and [serviceAuthRequestPrepared] properties. */
    @OptIn(ExperimentalUuidApi::class)
    override suspend fun getServiceAuthorizationUrl(): Result<HttpsUrl> {
        serverState = Uuid.random().toString()
        return try {
            with(getOrCreateClient()) {
                val authorizationCodeURL = prepareServiceAuthorizationRequest().getOrThrow()
                    .also { serviceAuthRequestPrepared = it }
                    .value.authorizationCodeURL
                Result.success(authorizationCodeURL)
            }
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }

    /* The method requires the [serverState] and [serviceAuthRequestPrepared] properties to be initialized. */
    override suspend fun authorizeService(authorizationCode: AuthorizationCode): Result<RQESService.Authorized> {
        return try {
            (serverState to serviceAuthRequestPrepared).letBoth { serverState, serviceAuthRequestPrepared ->
                with(getOrCreateClient()) {
                    val serviceAccessAuthorized =
                        serviceAuthRequestPrepared.authorizeWithAuthorizationCode(
                            authorizationCode,
                            serverState
                        ).getOrThrow()
                    Result.success(
                        AuthorizedImpl(
                            serverState,
                            this@with,
                            serviceAccessAuthorized,
                            scaService
                        )
                    )
                }
            }
                ?: throw IllegalStateException("Service authorization request not prepared. Call getServiceAuthorizationUrl() first")
        } catch (e: Throwable) {
            return Result.failure(e)
        }
    }


    class AuthorizedImpl(
        private val serverState: String,
        private val client: CSCClient,
        private val serviceAccessAuthorized: ServiceAccessAuthorized,
        private val scaService: SCAService,
    ) : RQESService.Authorized {

        private var documents: List<Document>? = null
        private var credAuthRequestPrepared: CredentialAuthorizationRequestPrepared? = null

        override suspend fun listCredentials(request: CredentialsListRequest?): Result<List<CredentialInfo>> {
            return try {
                with(client) {
                    val credentials = serviceAccessAuthorized.listCredentials(
                        request ?: CredentialsListRequest(
                            onlyValid = true
                        )
                    ).getOrThrow()

                    Result.success(credentials)
                }
            } catch (e: Throwable) {
                Result.failure(e)
            }
        }

        override suspend fun getCredentialAuthorizationUrl(
            credential: CredentialInfo,
            documents: List<Document>,
            hashAlgorithmOID: HashAlgorithmOID?,
            certificates: List<X509Certificate>?
        ): Result<HttpsUrl> {
            return try {
                with(client) {
                    val documentDigests = documents.map { document ->
                        DocumentDigest(
                            scaService.calculateHash(document, certificates),
                            document.id
                        )
                    }

                    val documentList = DocumentList(
                        documentDigests,
                        hashAlgorithmOID ?: HashAlgorithmOID.SHA256RSA
                    )
                    val authorizationCodeURL =
                        serviceAccessAuthorized.prepareCredentialAuthorizationRequest(
                            credential,
                            documentList,
                        ).getOrThrow()
                            .also { credAuthRequestPrepared = it }
                            .value
                            .authorizationCodeURL

                    this@AuthorizedImpl.documents = documents
                    Result.success(authorizationCodeURL)
                }
            } catch (e: Throwable) {
                Result.failure(e)
            }
        }

        override suspend fun authorizeCredential(authorizationCode: AuthorizationCode): Result<RQESService.CredentialAuthorized> {
            return try {
                (credAuthRequestPrepared to documents).letBoth { credAuthRequestPrepared, documents ->
                    with(client) {
                        val credentialAuthorized =
                            credAuthRequestPrepared.authorizeWithAuthorizationCode(
                                authorizationCode,
                                serverState
                            )
                                .getOrThrow()
                        return Result.success(
                            CredentialAuthorizedImpl(
                                this@with,
                                documents,
                                credentialAuthorized as CredentialAuthorized.SCAL2,
                                scaService
                            )
                        )
                    }
                }
                    ?: throw IllegalStateException("Credential authorization request not prepared. Call getCredentialAuthorizationUrl() first")
            } catch (e: Throwable) {
                return Result.failure(e)
            }
        }
    }

    class CredentialAuthorizedImpl(
        private val client: CSCClient,
        private val documents: List<Document>,
        private val credentialAuthorized: CredentialAuthorized.SCAL2,
        private val signatureEmbedder: DocumentSignatureEmbedder
    ) : RQESService.CredentialAuthorized {
        override suspend fun signDocuments(
            algorithmOID: AlgorithmOID?,
            certificates: List<X509Certificate>?
        ): Result<List<Document>> {
            return try {
                with(client) {
                    val signatures =
                        credentialAuthorized.signHash(algorithmOID ?: AlgorithmOID.ECDSA_SHA256)
                            .getOrThrow()
                    val signedDocuments = signatures.value.mapIndexed { index, signature ->
                        signatureEmbedder.embedSignature(
                            documents[index],
                            signature,
                            certificates
                        )
                    }
                    Result.success(signedDocuments)
                }
            } catch (e: Throwable) {
                Result.failure(e)
            }
        }
    }
}

private inline fun <T1, T2, R> Pair<T1?, T2?>.letBoth(block: (T1, T2) -> R?): R? {
    return first?.let { first ->
        second?.let { second ->
            block(first, second)
        }
    }
}
