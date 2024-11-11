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

import eu.europa.ec.eudi.rqes.CSCClient
import eu.europa.ec.eudi.rqes.CredentialAuthorized
import eu.europa.ec.eudi.rqes.DocumentDigestList
import eu.europa.ec.eudi.rqes.DocumentToSign
import eu.europa.ec.eudi.rqes.HashAlgorithmOID
import eu.europa.ec.eudi.rqes.Signature
import eu.europa.ec.eudi.rqes.SignaturesList
import eu.europa.ec.eudi.rqes.SigningAlgorithmOID
import eu.europa.ec.eudi.rqes.core.RQESServiceImpl.CredentialAuthorizedImpl
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.io.InputStream
import java.time.Instant
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CredentialAuthorizedImplTest {

    @Test
    fun `test signDocuments returns the SignedDocuments for SCAL1`() = runTest {

        val mockClient = mockk<CSCClient>(relaxed = true)
        val documentsToSignList = listOf<DocumentToSign>(mockk())
        val documentDigestList = mockk<DocumentDigestList> {
            every { hashAlgorithmOID } returns HashAlgorithmOID.SHA_256
            every { hashCalculationTime } returns Instant.now()
        }
        val signatureList = mockk<SignaturesList> {
            every { signatures } returns listOf<Signature>(mockk())
        }
        val signedDocumentsInputStreams = listOf<InputStream>(mockk())
        val credentialAuthorized = mockk<CredentialAuthorized.SCAL1>()

        val authorizedCredentialService = CredentialAuthorizedImpl(
            client = mockClient,
            documentDigestList = documentDigestList,
            documentsToSign = documentsToSignList,
            credentialAuthorized = credentialAuthorized,
            signingAlgorithm = SigningAlgorithmOID.ECDSA_SHA256,
        )

        every { credentialAuthorized.credentialCertificate } returns mockk()
        coEvery {
            with(mockClient) {
                credentialAuthorized.signHash(
                    documentDigestList,
                    authorizedCredentialService.signingAlgorithm
                )
            }
        } returns Result.success(signatureList)

        coEvery {
            with(mockClient) {
                getSignedDocuments(
                    documents = documentsToSignList,
                    signatures = signatureList.signatures,
                    credentialCertificate = credentialAuthorized.credentialCertificate,
//                        hashAlgorithmOID = documentDigestList.hashAlgorithmOID,
                    hashAlgorithmOID = any(), // TODO: Fix this
                    signatureTimestamp = documentDigestList.hashCalculationTime
                )
            }
        } returns signedDocumentsInputStreams


        val result = authorizedCredentialService.signDocuments()
        assertTrue(result.isSuccess)

        val signedDocuments = result.getOrThrow()
        assertEquals(1, signedDocuments.size)
        assertEquals(signedDocumentsInputStreams[0], signedDocuments[0])

    }

    @Test
    fun `test signDocuments returns the SignedDocuments for SCAL2`() = runTest {
        val mockClient = mockk<CSCClient>(relaxed = true)
        val documentsToSignList = listOf<DocumentToSign>(mockk())
        val documentDigestList = mockk<DocumentDigestList> {
            every { hashAlgorithmOID } returns HashAlgorithmOID.SHA_256
            every { hashCalculationTime } returns Instant.now()
        }
        val signatureList = mockk<SignaturesList> {
            every { signatures } returns listOf<Signature>(mockk())
        }
        val signedDocumentsInputStreams = listOf<InputStream>(mockk())
        val credentialAuthorized = mockk<CredentialAuthorized.SCAL2>()

        val authorizedCredentialService = CredentialAuthorizedImpl(
            client = mockClient,
            documentDigestList = documentDigestList,
            documentsToSign = documentsToSignList,
            credentialAuthorized = credentialAuthorized,
            signingAlgorithm = SigningAlgorithmOID.ECDSA_SHA256,
        )

        every { credentialAuthorized.credentialCertificate } returns mockk()
        coEvery {
            with(mockClient) {
                credentialAuthorized.signHash(authorizedCredentialService.signingAlgorithm)
            }
        } returns Result.success(signatureList)

        coEvery {
            with(mockClient) {
                getSignedDocuments(
                    documents = documentsToSignList,
                    signatures = signatureList.signatures,
                    credentialCertificate = credentialAuthorized.credentialCertificate,
//                        hashAlgorithmOID = documentDigestList.hashAlgorithmOID,
                    hashAlgorithmOID = any(), // TODO: Fix this
                    signatureTimestamp = documentDigestList.hashCalculationTime
                )
            }
        } returns signedDocumentsInputStreams


        val result = authorizedCredentialService.signDocuments()
        assertTrue(result.isSuccess)

        val signedDocuments = result.getOrThrow()
        assertEquals(1, signedDocuments.size)
        assertEquals(signedDocumentsInputStreams[0], signedDocuments[0])

    }
}