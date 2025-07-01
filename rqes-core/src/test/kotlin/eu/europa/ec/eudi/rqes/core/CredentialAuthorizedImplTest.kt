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
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.io.File
import java.time.Instant
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class CredentialAuthorizedImplTest {

    val outputPath = System.getProperty("java.io.tmpdir") as String

    @Test
    fun `test signDocuments returns the SignedDocuments for SCAL1`() = runTest {
        val documentOutputPathMock = outputPath + File.pathSeparator + "singed_document1.pdf"
        val labelMock = "label1"
        val mockClient = mockk<CSCClient>(relaxed = true)
        val documentsToSignList = listOf<DocumentToSign>(mockk {
            every { label } returns labelMock
            every { documentOutputPath } returns documentOutputPathMock
        })
        val documentDigestList = mockk<DocumentDigestList> {
            every { hashAlgorithmOID } returns HashAlgorithmOID.SHA_256
            every { hashCalculationTime } returns Instant.now()
        }
        val signatureList = mockk<SignaturesList> {
            every { signatures } returns listOf(mockk())
        }
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
                createSignedDocuments(signatureList.signatures)
            }
        } just runs


        val result = authorizedCredentialService.signDocuments()
        assertTrue(result.isSuccess)

        val signedDocuments = result.getOrThrow()
        assertEquals(1, signedDocuments.size)
        val firstSignedDocument = signedDocuments[labelMock]
        assertNotNull(firstSignedDocument)
        assertEquals(documentOutputPathMock, firstSignedDocument.absolutePath)

    }

    @Test
    fun `test signDocuments returns the SignedDocuments for SCAL2`() = runTest {
        val documentOutputPathMock = outputPath + File.pathSeparator + "singed_document1.pdf"
        val labelMock = "label1"
        val mockClient = mockk<CSCClient>(relaxed = true)
        val documentsToSignList = listOf<DocumentToSign>(mockk {
            every { label } returns labelMock
            every { documentOutputPath } returns documentOutputPathMock
        })
        val documentDigestList = mockk<DocumentDigestList> {
            every { hashAlgorithmOID } returns HashAlgorithmOID.SHA_256
            every { hashCalculationTime } returns Instant.now()
        }
        val signatureList = mockk<SignaturesList> {
            every { signatures } returns listOf<Signature>(mockk())
        }
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
                createSignedDocuments(signatureList.signatures)
            }
        } just runs


        val result = authorizedCredentialService.signDocuments()
        assertTrue(result.isSuccess)

        val signedDocuments = result.getOrThrow()
        assertEquals(1, signedDocuments.size)
        val firstSignedDocument = signedDocuments[labelMock]
        assertNotNull(firstSignedDocument)
        assertEquals(documentOutputPathMock, firstSignedDocument.absolutePath)

    }
}