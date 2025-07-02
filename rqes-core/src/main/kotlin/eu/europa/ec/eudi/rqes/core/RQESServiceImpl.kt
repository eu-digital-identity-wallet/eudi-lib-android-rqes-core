/*
 * Copyright (c) 2024-2025 European Commission
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

import eu.europa.ec.eudi.rqes.AuthorizationCode
import eu.europa.ec.eudi.rqes.CSCClient
import eu.europa.ec.eudi.rqes.CSCClientConfig
import eu.europa.ec.eudi.rqes.CredentialAuthorizationRequestPrepared
import eu.europa.ec.eudi.rqes.CredentialAuthorizationSubject
import eu.europa.ec.eudi.rqes.CredentialAuthorized
import eu.europa.ec.eudi.rqes.CredentialInfo
import eu.europa.ec.eudi.rqes.CredentialRef
import eu.europa.ec.eudi.rqes.CredentialsListRequest
import eu.europa.ec.eudi.rqes.DefaultHttpClientFactory
import eu.europa.ec.eudi.rqes.DocumentDigestList
import eu.europa.ec.eudi.rqes.DocumentToSign
import eu.europa.ec.eudi.rqes.HashAlgorithmOID
import eu.europa.ec.eudi.rqes.HttpsUrl
import eu.europa.ec.eudi.rqes.RSSPMetadata
import eu.europa.ec.eudi.rqes.ServiceAccessAuthorized
import eu.europa.ec.eudi.rqes.ServiceAuthorizationRequestPrepared
import eu.europa.ec.eudi.rqes.SigningAlgorithmOID
import io.ktor.client.HttpClient
import org.jetbrains.annotations.VisibleForTesting
import java.io.File
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * The RQES service implementation.
 * This class provides the implementation of the RQES service.
 *
 * @property serviceEndpointUrl The RQES service endpoint URL.
 * @property config The RQES service configuration.
 * @property outputPathDir Directory where signed documents will be stored.
 * @property hashAlgorithm The algorithm OID, for hashing the documents.
 * @property clientFactory The HTTP client factory. If this property is null, the default HTTP client factory will be used.
 *
 * @param serviceEndpointUrl The RQES service endpoint URL.
 * @param config The RQES service configuration.
 * @param outputPathDir Directory where signed documents will be stored.
 * @param hashAlgorithm The algorithm OID, for hashing the documents.
 * @param clientFactory The HTTP client factory. If this property is null, the default HTTP client factory will be used.
 */
class RQESServiceImpl(
    @VisibleForTesting internal val serviceEndpointUrl: String,
    @VisibleForTesting internal val config: CSCClientConfig,
    @VisibleForTesting internal val outputPathDir: String,
    override val hashAlgorithm: HashAlgorithmOID,
    @VisibleForTesting internal val clientFactory: (() -> HttpClient)? = null
) : RQESService {

    /**
     * The server state. This property is used to store the server state.
     */
    internal lateinit var serverState: String

    /**
     * The service authorization request prepared. This property is used to store the service authorization request prepared.
     */
    internal var serviceAuthRequestPrepared: ServiceAuthorizationRequestPrepared? = null

    /**
     * The client. This property is used to store the client.
     */
    @VisibleForTesting
    internal lateinit var client: CSCClient

    /**
     * Get or create the client.
     * This method is used to get or create the client.
     * @return The client.
     */
    /**
     * Gets or creates the CSC client for communicating with the RSSP.
     *
     * This method implements lazy initialization of the CSC client, creating it only when needed
     * and reusing the same instance for subsequent calls. It initializes the client using the
     * OAuth2 authentication flow with the configured service endpoint and client configuration.
     *
     * @return An initialized [CSCClient] for communicating with the RSSP
     * @throws Throwable if client initialization fails
     */
    @Throws(Throwable::class)
    @VisibleForTesting
    internal suspend fun getOrCreateClient(): CSCClient {
        if (::client.isInitialized.not()) {
            client = CSCClient.oauth2(
                config,
                serviceEndpointUrl,
                clientFactory ?: DefaultHttpClientFactory
            ).getOrThrow()
        }
        return client
    }

    /**
     * Retrieves metadata from the Remote Signature Service Provider (RSSP).
     *
     * This method fetches information about the RSSP service capabilities, supported
     * algorithms, identity, and other service-specific details. It's typically used
     * as the initial step to discover service capabilities before starting the
     * authorization workflow.
     *
     * Implementation details:
     * 1. Ensures the CSC client is initialized via [getOrCreateClient]
     * 2. Retrieves the metadata from the RSSP
     * 3. Returns the metadata wrapped in a [Result]
     *
     * @return A [Result] containing the [RSSPMetadata] if successful,
     *         or an error if the retrieval failed
     */
    override suspend fun getRSSPMetadata(): Result<RSSPMetadata> {
        return runCatching { getOrCreateClient().rsspMetadata }
    }

    /**
     * Generates a service authorization URL for initiating the user authorization flow.
     *
     * This method is the first step in the authorization workflow. It:
     * 1. Generates a random server state value for security validation
     * 2. Prepares a service authorization request through the CSC client
     * 3. Stores the prepared request for later use in [authorizeService]
     *
     * The returned URL should be presented to the user (typically by redirecting them to it),
     * allowing them to authorize the application to access the remote signature service.
     * After authorization, the user will be redirected back with an authorization code
     * that should be passed to [authorizeService].
     *
     * @return A [Result] containing the [HttpsUrl] for the authorization endpoint if successful,
     *         or an error if the request preparation failed
     */
    @OptIn(ExperimentalUuidApi::class)
    override suspend fun getServiceAuthorizationUrl(): Result<HttpsUrl> {
        serverState = Uuid.random().toString()
        return runCatching {
            with(getOrCreateClient()) {
                val authorizationCodeURL = prepareServiceAuthorizationRequest(serverState)
                    .getOrThrow()
                    .also { serviceAuthRequestPrepared = it }
                    .value.authorizationCodeURL
                // returns
                authorizationCodeURL
            }
        }
    }

    /**
     * Completes the service authorization process by exchanging the authorization code for service access.
     *
     * This method requires that [getServiceAuthorizationUrl] has been called previously to initialize
     * the server state and prepare the authorization request. It exchanges the authorization code
     * received after user authentication for service access credentials.
     *
     * Implementation workflow:
     * 1. Verifies that the server state has been initialized through [getServiceAuthorizationUrl]
     * 2. Uses the prepared authorization request to exchange the code for access
     * 3. Creates an [AuthorizedImpl] instance with the authorized service access
     *
     * @param authorizationCode The authorization code received after user completes the authorization flow
     * @return A [Result] containing the [RQESService.Authorized] implementation if successful,
     *         or an error if the authorization process failed
     * @throws IllegalStateException If [getServiceAuthorizationUrl] was not called before this method
     */
    override suspend fun authorizeService(authorizationCode: AuthorizationCode): Result<RQESService.Authorized> {
        return runCatching {
            check(::serverState.isInitialized) {
                "Server state not initialized. Call getServiceAuthorizationUrl() first"
            }
            val prepared = serviceAuthRequestPrepared
            checkNotNull(prepared) {
                "Service authorization request not prepared. Call getServiceAuthorizationUrl() first"
            }
            with(getOrCreateClient()) {
                val authorized = prepared.authorizeWithAuthorizationCode(
                    authorizationCode = authorizationCode,
                    serverState = serverState
                ).getOrThrow()

                // returns
                AuthorizedImpl(
                    serverState = serverState,
                    client = this@with,
                    serviceAccessAuthorized = authorized,
                    outputPathDir = outputPathDir,
                    hashAlgorithm = this@RQESServiceImpl.hashAlgorithm,
                )
            }
        }
    }


    /**
     * Implementation of the [RQESService.Authorized] interface that provides access to
     * user credentials and document signing operations after successful service authorization.
     *
     * This class maintains state across the credential selection and document signing workflow,
     * storing information about documents to be signed, credential authorization state, and
     * cryptographic parameters needed for the signing operation.
     *
     * @property serverState Security state token used for validating authorization responses.
     * @property client The CSC client for communicating with the RSSP.
     * @property serviceAccessAuthorized The authorized service access credentials from OAuth2 flow.
     * @property outputPathDir Directory where signed documents will be stored.
     * @property hashAlgorithm The algorithm used for creating document hashes.
     */
    class AuthorizedImpl(
        @VisibleForTesting internal val serverState: String,
        @VisibleForTesting internal val client: CSCClient,
        @VisibleForTesting internal val serviceAccessAuthorized: ServiceAccessAuthorized,
        @VisibleForTesting internal val outputPathDir: String,
        val hashAlgorithm: HashAlgorithmOID,
    ) : RQESService.Authorized {

        @VisibleForTesting
        internal lateinit var documentsToSign: List<DocumentToSign>

        @VisibleForTesting
        internal lateinit var documentDigestList: DocumentDigestList

        @VisibleForTesting
        internal lateinit var credAuthRequestPrepared: CredentialAuthorizationRequestPrepared

        @VisibleForTesting
        internal var signingAlgorithmOID: SigningAlgorithmOID? = null

        /**
         * Retrieves the list of available credentials for the authorized user.
         *
         * This method queries the RSSP for credentials associated with the authorized user
         * that can be used for document signing. By default, only valid credentials are returned
         * if no specific request criteria are provided.
         *
         * Implementation workflow:
         * 1. Uses the authorized service access to list credentials from the RSSP
         * 2. Applies any filtering criteria specified in the request
         * 3. Returns the list of matching credentials
         *
         * @param request Optional filter criteria for the credentials list. If null, all valid
         *                credentials will be returned.
         * @return A [Result] containing a list of [CredentialInfo] objects if successful,
         *         or an error if the retrieval failed
         */
        override suspend fun listCredentials(request: CredentialsListRequest?): Result<List<CredentialInfo>> {
            return runCatching {
                with(client) {
                    // returns
                    serviceAccessAuthorized.listCredentials(
                        request ?: CredentialsListRequest(onlyValid = true)
                    ).getOrThrow()
                }
            }
        }

        /**
         * Generates a credential authorization URL for document signing.
         *
         * This method prepares the documents for signing with the specified credential and
         * creates an authorization URL for the user to approve the signing operation. It:
         * 1. Selects an appropriate signing algorithm from those supported by the credential
         * 2. Prepares the documents for signing and converts them to the appropriate format
         * 3. Calculates cryptographic hashes of the documents using the configured algorithm
         * 4. Prepares a credential authorization request with the document hashes
         * 5. Stores the prepared request and document information for later use
         *
         * The returned URL should be presented to the user (typically by redirecting them to it),
         * allowing them to authorize the use of their specific credential for signing.
         * After authorization, the user will be redirected back with an authorization code
         * that should be passed to [authorizeCredential].
         *
         * @param credential The credential to be used for signing the documents
         * @param documents The collection of unsigned documents to be signed
         * @param signingAlgorithmOID Optional algorithm OID for signing the documents. If null, the first supported algorithm of the credential will be used.
         * @return A [Result] containing the [HttpsUrl] for credential authorization if successful,
         *         or an error if preparation failed
         */
        override suspend fun getCredentialAuthorizationUrl(
            credential: CredentialInfo,
            documents: UnsignedDocuments,
            signingAlgorithmOID: SigningAlgorithmOID?
        ): Result<HttpsUrl> {
            return runCatching {
                this.signingAlgorithmOID =
                    signingAlgorithmOID ?: credential.key.supportedAlgorithms.first()
                require(this.signingAlgorithmOID in credential.key.supportedAlgorithms) {
                    "Signing algorithm not supported by credential"
                }
                with(client) {
                    this@AuthorizedImpl.documentsToSign =
                        documents.asDocumentToSignList(outputPathDir)
                    this@AuthorizedImpl.documentDigestList = calculateDocumentHashes(
                        documents = documentsToSign,
                        hashAlgorithmOID = hashAlgorithm,
                        credentialCertificate = credential.certificate
                    )
                    val authorizationCodeURL = prepareCredentialAuthorizationRequest(
                        credentialAuthorizationSubject = CredentialAuthorizationSubject(
                            credentialRef = CredentialRef.ByCredentialID(credential.credentialID),
                            documentDigestList = documentDigestList,
                        ),
                        walletState = serverState
                    )
                        .getOrThrow()
                        .also { credAuthRequestPrepared = it }
                        .authorizationRequestPrepared
                        .authorizationCodeURL
                    // returns
                    authorizationCodeURL
                }
            }
        }

        /**
         * Completes the credential authorization process for document signing.
         *
         * This method requires that [getCredentialAuthorizationUrl] has been called previously to
         * initialize the document hashes and prepare the credential authorization request. It exchanges
         * the authorization code received after user authentication for credential access credentials.
         *
         * Implementation workflow:
         * 1. Verifies that all required state has been initialized through [getCredentialAuthorizationUrl]
         * 2. Uses the prepared request to exchange the code for credential access
         * 3. Creates a [CredentialAuthorizedImpl] instance with the authorized credential and document info
         *
         * This is the final authorization step before actual document signing can occur.
         *
         * @param authorizationCode The authorization code received after user completes the credential authorization flow
         * @return A [Result] containing a [RQESService.CredentialAuthorized] implementation if successful,
         *         or an error if the authorization process failed
         * @throws IllegalStateException If [getCredentialAuthorizationUrl] was not called before this method
         *         or if required state is missing
         */
        override suspend fun authorizeCredential(authorizationCode: AuthorizationCode): Result<RQESService.CredentialAuthorized> {
            return runCatching {
                check(::credAuthRequestPrepared.isInitialized) {
                    "Server state not initialized. Call getServiceAuthorizationUrl() first"
                }
                check(::documentsToSign.isInitialized) {
                    "Documents not initialized. Call getCredentialAuthorizationUrl() first"
                }
                check(::documentDigestList.isInitialized) {
                    "Document hashes not initialized. Call getCredentialAuthorizationUrl() first"
                }
                val signingAlgorithmOID = signingAlgorithmOID
                checkNotNull(signingAlgorithmOID) {
                    "Signing algorithm not initialized. Call getCredentialAuthorizationUrl() first"
                }
                with(client) {
                    val authorized = credAuthRequestPrepared
                        .authorizeWithAuthorizationCode(authorizationCode, serverState)
                        .getOrThrow()

                    // returns
                    CredentialAuthorizedImpl(
                        client = this@with,
                        documentsToSign = documentsToSign,
                        documentDigestList = documentDigestList,
                        credentialAuthorized = authorized,
                        signingAlgorithm = signingAlgorithmOID
                    )
                }
            }
        }
    }


    /**
     * Implementation of the [RQESService.CredentialAuthorized] interface that performs
     * document signing operations with an authorized credential.
     *
     * This class holds all the information needed for signing the documents that were
     * specified during the credential authorization process, including the document content,
     * cryptographic digests, and the authorized credential for signing.
     *
     * @property client The CSC client for communicating with the RSSP.
     * @property documentsToSign List of documents prepared for signing.
     * @property documentDigestList Document digests prepared for the signing request.
     * @property credentialAuthorized The authorized credential access for signing.
     * @property signingAlgorithm The algorithm to be used for the signing operation.
     *
     * @param client The CSC client for communicating with the RSSP.
     * @param documentsToSign List of documents prepared for signing.
     * @param documentDigestList Document digests prepared for the signing request.
     * @param credentialAuthorized The authorized credential access for signing.
     * @param signingAlgorithm The algorithm to be used for the signing operation.
     */
    class CredentialAuthorizedImpl(
        @VisibleForTesting internal val client: CSCClient,
        @VisibleForTesting internal val documentsToSign: List<DocumentToSign>,
        @VisibleForTesting internal val documentDigestList: DocumentDigestList,
        @VisibleForTesting internal val credentialAuthorized: CredentialAuthorized,
        val signingAlgorithm: SigningAlgorithmOID
    ) : RQESService.CredentialAuthorized {

        /**
         * Signs the previously specified documents using the authorized credential.
         *
         * This method performs the actual document signing operation by:
         * 1. Using the authorized credential to sign the document hashes
         * 2. Creating the final signed documents with the obtained signatures
         * 3. Returning the collection of signed document files
         *
         * The signing process adapts based on the credential's Signature Creation Assurance Level (SCAL):
         * - For SCAL1 credentials, the document hashes are sent with the signing request
         * - For SCAL2 credentials, the hashes were previously uploaded during authorization
         *
         * @return A [Result] containing [SignedDocuments] with the paths to the signed files if successful,
         *         or an error if the signing operation failed
         */
        override suspend fun signDocuments(): Result<SignedDocuments> {
            return runCatching {
                with(client) {
                    val signatureList = when (credentialAuthorized) {
                        is CredentialAuthorized.SCAL1 -> credentialAuthorized
                            .signHash(
                                documentDigestList = documentDigestList,
                                signingAlgorithmOID = signingAlgorithm
                            )

                        is CredentialAuthorized.SCAL2 -> credentialAuthorized
                            .signHash(
                                signingAlgorithmOID = signingAlgorithm
                            )

                    }.getOrThrow()

                    createSignedDocuments(signatures = signatureList.signatures)
                    // returns
                    documentsToSign.associate {
                        it.label to File(it.documentOutputPath)
                    }.let { SignedDocuments(it) }
                }
            }
        }
    }

    companion object
}
