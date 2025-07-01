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
import eu.europa.ec.eudi.rqes.AuthorizationRequestPrepared
import eu.europa.ec.eudi.rqes.CSCClient
import eu.europa.ec.eudi.rqes.CredentialAuthorizationRequestPrepared
import eu.europa.ec.eudi.rqes.CredentialAuthorizationSubject
import eu.europa.ec.eudi.rqes.CredentialAuthorized
import eu.europa.ec.eudi.rqes.CredentialID
import eu.europa.ec.eudi.rqes.CredentialInfo
import eu.europa.ec.eudi.rqes.CredentialRef
import eu.europa.ec.eudi.rqes.DocumentDigestList
import eu.europa.ec.eudi.rqes.HashAlgorithmOID
import eu.europa.ec.eudi.rqes.HttpsUrl
import eu.europa.ec.eudi.rqes.ServiceAccessAuthorized
import eu.europa.ec.eudi.rqes.SigningAlgorithmOID
import eu.europa.ec.eudi.rqes.core.RQESServiceImpl.AuthorizedImpl
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertThrows
import java.io.File
import kotlin.io.path.absolutePathString
import kotlin.io.path.createTempDirectory
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertNull
import kotlin.test.assertTrue

class AuthorizedImplTest {
    val mockClient = mockk<CSCClient>(relaxed = true)
    val serverState = "server-state"
    lateinit var authorizedService: AuthorizedImpl
    val serviceAccessAuthorized: ServiceAccessAuthorized = mockk(relaxed = true)

    val outputPathDir = createTempDirectory().absolutePathString()

    @BeforeTest
    fun setUp() {
        authorizedService = AuthorizedImpl(
            serverState = serverState,
            client = mockClient,
            serviceAccessAuthorized = serviceAccessAuthorized,
            outputPathDir = outputPathDir,
            hashAlgorithm = HashAlgorithmOID.SHA_256,
        )
    }

    @AfterTest
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `verify listCredentials returns list of credentials`() = runTest {

        val credentialInfoList = listOf(mockk<CredentialInfo>(), mockk<CredentialInfo>())
        coEvery {
            with(mockClient) {
                serviceAccessAuthorized.listCredentials(any())
            }
        } returns Result.success(credentialInfoList)

        val result = authorizedService.listCredentials()
        assert(result.isSuccess)
        assert(result.getOrNull() == credentialInfoList)
    }

    @Test
    fun `verify getCredentialAuthorizationUrl sets the properties documentsToSign documentDigestList credAuthRequestPrepared and signingAlgorithmOID and returns the credential authorization url`() =
        runTest {

            assertThrows(UninitializedPropertyAccessException::class.java) {
                authorizedService.documentsToSign
            }
            assertThrows(UninitializedPropertyAccessException::class.java) {
                authorizedService.documentDigestList
            }
            assertThrows(UninitializedPropertyAccessException::class.java) {
                authorizedService.credAuthRequestPrepared
            }
            assertNull(authorizedService.signingAlgorithmOID)


            val document = UnsignedDocument(
                label = "my pdf file",
                file = File.createTempFile(
                    AuthorizedImplTest::class.simpleName!!, ".pdf"
                )
            )
            val signingAlgorithmOID = SigningAlgorithmOID.ECDSA_SHA256
            val documents = UnsignedDocuments(listOf(document))
            val documentsList = documents.asDocumentToSignList(outputPathDir)
            val credentialInfo = mockk<CredentialInfo>(relaxed = true) {
                every { credentialID } returns CredentialID("credential-id")
                every { certificate } returns mockk()
                every { key } returns mockk {
                    every { supportedAlgorithms } returns listOf(signingAlgorithmOID)
                }
            }
            val documentDigestList = mockk<DocumentDigestList>()

            coEvery {
                mockClient.calculateDocumentHashes(
                    documents = documentsList,
                    credentialCertificate = credentialInfo.certificate,
                    hashAlgorithmOID = authorizedService.hashAlgorithm,
                )
            } returns documentDigestList

            val mockAuthorizationCodeURL = HttpsUrl("https://example.com/auth").getOrThrow()
            val mockAuthorizationRequestPrepared = mockk<AuthorizationRequestPrepared> {
                every { authorizationCodeURL } returns mockAuthorizationCodeURL
            }
            val credentialAuthorizationRequestPrepared =
                mockk<CredentialAuthorizationRequestPrepared> {
                    every { authorizationRequestPrepared } returns mockAuthorizationRequestPrepared
                }

            val credentialAuthorizationSubject = CredentialAuthorizationSubject(
                credentialRef = CredentialRef.ByCredentialID(credentialInfo.credentialID),
                documentDigestList = documentDigestList,
            )
            coEvery {
                with(mockClient) {
                    prepareCredentialAuthorizationRequest(
                        credentialAuthorizationSubject = credentialAuthorizationSubject,
                        walletState = serverState
                    )
                }
            } returns Result.success(credentialAuthorizationRequestPrepared)

            val result = authorizedService.getCredentialAuthorizationUrl(credentialInfo, documents)
            assertTrue(result.isSuccess)
            assertEquals(mockAuthorizationCodeURL, result.getOrThrow())

            assertEquals(documentsList, authorizedService.documentsToSign)
            assertEquals(documentDigestList, authorizedService.documentDigestList)
            assertEquals(
                credentialAuthorizationRequestPrepared,
                authorizedService.credAuthRequestPrepared
            )
            assertEquals(signingAlgorithmOID, authorizedService.signingAlgorithmOID)

        }

    @Test
    fun `authorizeCredential returns failure if credAuthRequestPrepared in not set`() = runTest {
        val authorizationCode = AuthorizationCode(code = "let me in")

        authorizedService.documentsToSign = mockk()
        val result = authorizedService.authorizeCredential(authorizationCode)
        assert(result.isFailure)
        assertIs<IllegalStateException>(result.exceptionOrNull())


    }

    @Test
    fun `authorizeCredential returns failure if documentsToSign in not set`() = runTest {
        val authorizationCode = AuthorizationCode(code = "let me in")

        authorizedService.credAuthRequestPrepared = mockk()
        val result = authorizedService.authorizeCredential(authorizationCode)
        assert(result.isFailure)
        assertIs<IllegalStateException>(result.exceptionOrNull())


    }

    @Test
    fun `authorizeCredential returns authorized credential`() = runTest {

        val credentialAuthRequestPrepared = mockk<CredentialAuthorizationRequestPrepared>()
        val credentialAuthorized = mockk<CredentialAuthorized>()

        authorizedService.signingAlgorithmOID = SigningAlgorithmOID.ECDSA_SHA256
        authorizedService.documentsToSign = mockk()
        authorizedService.documentDigestList = mockk()
        authorizedService.credAuthRequestPrepared = credentialAuthRequestPrepared

        val authorizationCode = AuthorizationCode(code = "let me in")
        coEvery {
            with(mockClient) {
                credentialAuthRequestPrepared.authorizeWithAuthorizationCode(
                    authorizationCode,
                    authorizedService.serverState
                )
            }
        } returns Result.success(credentialAuthorized)

        val result = authorizedService.authorizeCredential(authorizationCode)
        assert(result.isSuccess)
    }

    @Test
    fun `verify getCredentialAuthorizationUrl with signingAlgorithm keeps the passed algorithm to object state`() =
        runTest {
            val document = UnsignedDocument(
                label = "my pdf file",
                file = File.createTempFile(
                    AuthorizedImplTest::class.simpleName!!, ".pdf"
                )
            )
            val supportedSigningAlgorithmOID = SigningAlgorithmOID.ECDSA_SHA256

            val documents = UnsignedDocuments(listOf(document))
            val documentsList = documents.asDocumentToSignList(outputPathDir)
            val credentialInfo = mockk<CredentialInfo>(relaxed = true) {
                every { credentialID } returns CredentialID("credential-id")
                every { certificate } returns mockk()
                every { key } returns mockk {
                    every { supportedAlgorithms } returns listOf(supportedSigningAlgorithmOID)
                }
            }
            val documentDigestList = mockk<DocumentDigestList>()

            coEvery {
                mockClient.calculateDocumentHashes(
                    documents = documentsList,
                    credentialCertificate = credentialInfo.certificate,
                    hashAlgorithmOID = authorizedService.hashAlgorithm,
                )
            } returns documentDigestList

            val mockAuthorizationCodeURL = HttpsUrl("https://example.com/auth").getOrThrow()
            val mockAuthorizationRequestPrepared = mockk<AuthorizationRequestPrepared> {
                every { authorizationCodeURL } returns mockAuthorizationCodeURL
            }
            val credentialAuthorizationRequestPrepared =
                mockk<CredentialAuthorizationRequestPrepared> {
                    every { authorizationRequestPrepared } returns mockAuthorizationRequestPrepared
                }

            val credentialAuthorizationSubject = CredentialAuthorizationSubject(
                credentialRef = CredentialRef.ByCredentialID(credentialInfo.credentialID),
                documentDigestList = documentDigestList,
            )
            coEvery {
                with(mockClient) {
                    prepareCredentialAuthorizationRequest(
                        credentialAuthorizationSubject = credentialAuthorizationSubject,
                        walletState = serverState
                    )
                }
            } returns Result.success(credentialAuthorizationRequestPrepared)

            val result = authorizedService.getCredentialAuthorizationUrl(
                credentialInfo,
                documents,
            )
            assertTrue(result.isSuccess)
            assertEquals(mockAuthorizationCodeURL, result.getOrThrow())

            assertEquals(documentsList, authorizedService.documentsToSign)
            assertEquals(documentDigestList, authorizedService.documentDigestList)
            assertEquals(
                credentialAuthorizationRequestPrepared,
                authorizedService.credAuthRequestPrepared
            )
            assertEquals(supportedSigningAlgorithmOID, authorizedService.signingAlgorithmOID)
        }
}