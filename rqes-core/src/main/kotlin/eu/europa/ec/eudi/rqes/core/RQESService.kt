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
import eu.europa.ec.eudi.rqes.CSCClientConfig
import eu.europa.ec.eudi.rqes.CredentialInfo
import eu.europa.ec.eudi.rqes.CredentialsListRequest
import eu.europa.ec.eudi.rqes.HashAlgorithmOID
import eu.europa.ec.eudi.rqes.HttpsUrl
import eu.europa.ec.eudi.rqes.RSSPMetadata
import eu.europa.ec.eudi.rqes.SigningAlgorithmOID
import io.ktor.client.HttpClient
import java.io.File

/**
 * The RQES (Remote Qualified Electronic Signature) service interface.
 *
 * This interface provides methods to interact with the RQES service for document signing.
 * The service workflow is divided into two parts:
 * - The authorization phase, which authenticates and authorizes the service to access user credentials
 * - The credential phase, which handles document signing with authorized credentials
 *
 * @property hashAlgorithm The algorithm OID used for hashing documents during the signing process.
 * @property signingAlgorithm The default [SigningAlgorithm] used when signing documents, unless
 * overridden per request in [Authorized.getCredentialAuthorizationUrl]. The resolved algorithm must
 * be supported by the credential used for signing.
 */
interface RQESService {

    val hashAlgorithm: HashAlgorithmOID

    val signingAlgorithm: SigningAlgorithm

    /**
     * Retrieves the Remote Signature Service Provider (RSSP) metadata.
     *
     * The metadata contains information about the RSSP service capabilities,
     * supported algorithms, and other service-specific details.
     *
     * @return A [Result] containing [RSSPMetadata] if successful, or an error if the operation failed.
     * @see [RSSPMetadata]
     */
    suspend fun getRSSPMetadata(): Result<RSSPMetadata>

    /**
     * Retrieves the service authorization URL.
     *
     * This URL is used to initiate the authorization flow, allowing the service to
     * access the user's credentials. The user should be redirected to this URL to
     * complete the authorization process.
     *
     * @return A [Result] containing an [HttpsUrl] for authorization if successful,
     *         or an error if the operation failed.
     */
    suspend fun getServiceAuthorizationUrl(): Result<HttpsUrl>

    /**
     * Completes the authorization process with the service.
     *
     * After the user completes the authorization flow at the service authorization URL,
     * an authorization code is provided. This method exchanges that code for service
     * access and returns an authorized service instance.
     *
     * @param authorizationCode The authorization code received after user authorization.
     * @return A [Result] containing an [Authorized] service instance if successful,
     *         or an error if the authorization failed.
     */
    suspend fun authorizeService(authorizationCode: AuthorizationCode): Result<Authorized>


    /**
     * Interface for interacting with the RQES service after successful authorization.
     *
     * This interface provides methods to access user credentials and initiate the
     * document signing process after the service has been authorized.
     */
    interface Authorized {

        /**
         * Retrieves a list of available signing credentials.
         *
         * Returns all credentials that can be used for document signing, optionally filtered
         * by the provided request parameters.
         *
         * @param request Optional filter criteria for the credentials list. If null, all valid
         *                credentials will be returned.
         * @return A [Result] containing a list of [CredentialInfo] objects if successful,
         *         or an error if the operation failed.
         */
        suspend fun listCredentials(request: CredentialsListRequest? = null): Result<List<CredentialInfo>>

        /**
         * Retrieves the credential authorization URL for document signing.
         *
         * This URL is used to initiate the credential authorization flow, which allows
         * the specified credential to be used for signing the provided documents.
         *
         * @param credential The credential to be used for signing.
         * @param documents The collection of unsigned documents to be signed.
         * @param signingAlgorithmOID Optional algorithm OID for signing the documents. If null, the service's default signing algorithm is used. In either case the selected algorithm must be supported by the credential, otherwise an error is returned.
         * @return A [Result] containing an [HttpsUrl] for credential authorization if successful,
         *         or an error if the operation failed.
         */
        suspend fun getCredentialAuthorizationUrl(
            credential: CredentialInfo,
            documents: UnsignedDocuments,
            signingAlgorithmOID: SigningAlgorithmOID? = null
        ): Result<HttpsUrl>

        /**
         * Completes the credential authorization process.
         *
         * After the user completes the credential authorization flow, an authorization code
         * is provided. This method exchanges that code for credential access and returns
         * an interface to sign the previously specified documents.
         *
         * @param authorizationCode The authorization code received after user credential authorization.
         * @return A [Result] containing a [CredentialAuthorized] instance if successful,
         *         or an error if the authorization failed.
         */
        suspend fun authorizeCredential(authorizationCode: AuthorizationCode): Result<CredentialAuthorized>
    }

    /**
     * Interface for signing documents with an authorized credential.
     *
     * This interface provides access to document signing operations after a specific
     * credential has been authorized. It operates on the documents that were previously
     * specified during the credential authorization process.
     */
    interface CredentialAuthorized {

        /**
         * Signs the previously specified documents using the authorized credential.
         *
         * This method performs the actual document signing operation with the credential
         * that was authorized through the [RQESService.Authorized.authorizeCredential] process.
         * The documents to be signed are those that were provided to
         * [RQESService.Authorized.getCredentialAuthorizationUrl].
         *
         * @return A [Result] containing the [SignedDocuments] if signing is successful,
         *         or an error if the signing operation failed.
         */
        suspend fun signDocuments(): Result<SignedDocuments>
    }

    /**
     * The signing algorithm used as the service default when signing documents.
     *
     * The value is resolved to a concrete [SigningAlgorithmOID] at the moment a credential is
     * selected in [Authorized.getCredentialAuthorizationUrl]. The resolved algorithm must be
     * supported by that credential, otherwise the operation fails.
     */
    sealed interface SigningAlgorithm {

        /**
         * Uses the first algorithm listed in the selected credential's supported algorithms
         * (`credential.key.supportedAlgorithms`).
         *
         * This guarantees the default is always one the credential supports, at the cost of not
         * controlling which specific algorithm is chosen.
         */
        data object FirstSupportedByCredential : SigningAlgorithm

        /**
         * Uses the explicitly provided [oid] as the signing algorithm.
         *
         * The algorithm must be supported by the selected credential, otherwise resolving it fails
         * with an [IllegalArgumentException].
         *
         * @property oid The signing algorithm OID to use.
         */
        data class Specific(val oid: SigningAlgorithmOID) : SigningAlgorithm
    }

    companion object {
        /**
         * Creates an instance of the RQES service.
         *
         * This factory method constructs and configures an RQES service implementation with
         * the specified parameters.
         *
         * @param serviceEndpointUrl The RSSP service endpoint URL.
         * @param config The Cloud Signature Consortium client configuration.
         * @param outputPathDir The directory where signed documents will be stored.
         * @param hashAlgorithm The algorithm OID to use for document hashing, defaults to SHA-256.
         * @param signingAlgorithm The default [SigningAlgorithm] to use when signing documents,
         * defaults to [SigningAlgorithm.FirstSupportedByCredential]. It can be overridden per request
         * in [Authorized.getCredentialAuthorizationUrl] and the resolved algorithm must be supported
         * by the credential used for signing.
         * @param httpClientFactory Optional custom HTTP client factory for network requests.
         * @return A configured [RQESService] implementation.
         * @throws IllegalArgumentException If [outputPathDir] does not point to a valid directory.
         */
        operator fun invoke(
            serviceEndpointUrl: String,
            config: CSCClientConfig,
            outputPathDir: String,
            hashAlgorithm: HashAlgorithmOID = HashAlgorithmOID.SHA_256,
            signingAlgorithm: SigningAlgorithm = SigningAlgorithm.FirstSupportedByCredential,
            httpClientFactory: (() -> HttpClient)? = null,
        ): RQESService {
            require(File(outputPathDir).isDirectory) {
                "Output path must be a directory"
            }
            return RQESServiceImpl(
                serviceEndpointUrl,
                config,
                outputPathDir,
                hashAlgorithm,
                signingAlgorithm,
                httpClientFactory
            )
        }
    }
}