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

import eu.europa.ec.eudi.rqes.AuthorizationCode
import eu.europa.ec.eudi.rqes.CSCClient
import eu.europa.ec.eudi.rqes.CredentialAuthorizationRequestPrepared
import eu.europa.ec.eudi.rqes.CredentialAuthorized
import eu.europa.ec.eudi.rqes.DocumentDigestList
import eu.europa.ec.eudi.rqes.DocumentToSign
import eu.europa.ec.eudi.rqes.HashAlgorithmOID
import eu.europa.ec.eudi.rqes.ServiceAccessAuthorized
import eu.europa.ec.eudi.rqes.Signature
import eu.europa.ec.eudi.rqes.SignaturesList
import eu.europa.ec.eudi.rqes.SigningAlgorithmOID
import eu.europa.ec.eudi.rqes.core.RQESServiceImpl.AuthorizedImpl
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.test.runTest
import java.io.InputStream
import java.time.Instant
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class ExtensionsTest {
    val mockClient = mockk<CSCClient>(relaxed = true)
    val serverState = "server-state"
    lateinit var authorizedService: AuthorizedImpl
    val serviceAccessAuthorized: ServiceAccessAuthorized = mockk(relaxed = true)

    @BeforeTest
    fun setUp() {
        authorizedService = AuthorizedImpl(
            serverState = serverState,
            client = mockClient,
            serviceAccessAuthorized = serviceAccessAuthorized,
            hashAlgorithm = HashAlgorithmOID.SHA_256,
        )
    }

    @AfterTest
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `test Authorized signDocuments extension`() = runTest {
        val authorizationCode = AuthorizationCode(code = "let me in")
        authorizedService.documentsToSign = listOf<DocumentToSign>(mockk())
        authorizedService.documentDigestList = mockk<DocumentDigestList> {
            every { hashAlgorithmOID } returns HashAlgorithmOID.SHA_256
            every { hashCalculationTime } returns Instant.now()
        }
        val signatureList = mockk<SignaturesList> {
            every { signatures } returns listOf<Signature>(mockk())
        }
        authorizedService.signingAlgorithmOID = SigningAlgorithmOID.ECDSA_SHA256
        val signedDocumentsInputStreams = listOf<InputStream>(mockk())
        val credentialAuthorized = mockk<CredentialAuthorized.SCAL2> {
            every { credentialCertificate } returns mockk()
            coEvery {
                with(mockClient) {
                    signHash(authorizedService.signingAlgorithmOID!!)
                }
            } returns Result.success(signatureList)
        }

        authorizedService.credAuthRequestPrepared =
            mockk<CredentialAuthorizationRequestPrepared> {
                coEvery {
                    with(mockClient) {
                        authorizeWithAuthorizationCode(
                            authorizationCode,
                            authorizedService.serverState
                        )
                    }
                } returns Result.success(credentialAuthorized)

            }

        coEvery {
            with(mockClient) {
                getSignedDocuments(
                    documents = authorizedService.documentsToSign,
                    signatures = signatureList.signatures,
                    credentialCertificate = credentialAuthorized.credentialCertificate,
//                        hashAlgorithmOID = documentDigestList.hashAlgorithmOID,
                    hashAlgorithmOID = any(), // TODO: Fix this
                    signatureTimestamp = authorizedService.documentDigestList.hashCalculationTime
                )
            }
        } returns signedDocumentsInputStreams

        val result = authorizedService.signDocuments(authorizationCode)
        assert(result.isSuccess)
    }
}