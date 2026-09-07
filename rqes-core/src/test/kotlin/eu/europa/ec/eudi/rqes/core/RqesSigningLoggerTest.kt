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
import kotlin.test.assertNull
import kotlin.test.assertTrue

class RqesSigningLoggerTest {

    private val outputPath = System.getProperty("java.io.tmpdir") as String
    private val label = "label1"
    private val fileSizeBytes = 123

    @Test
    fun `a successful signing tells the logger what was signed`() = runTest {
        var record: RqesSigningRecord? = null
        val logger = RqesSigningLogger { record = it }

        val service = scal1Service(logger = logger, signHashSucceeds = true)
        val result = service.signDocuments()

        assertTrue(result.isSuccess)
        val captured = requireNotNull(record) { "logger was not notified" }
        assertEquals(RqesSigningRecord.Outcome.Completed, captured.outcome)
        assertEquals("serial-123", captured.certificateSerialNumber)
        assertEquals(1, captured.documents.size)
        assertEquals(label, captured.documents.first().label)
        assertEquals("dtbsr-1", captured.documents.first().dtbsr)
        assertEquals(fileSizeBytes.toLong(), captured.documents.first().sizeBytes)
    }

    @Test
    fun `a failed signing tells the logger it failed`() = runTest {
        var record: RqesSigningRecord? = null
        val logger = RqesSigningLogger { record = it }

        val service = scal1Service(logger = logger, signHashSucceeds = false)
        val result = service.signDocuments()

        assertTrue(result.isFailure)
        val captured = requireNotNull(record) { "logger was not notified" }
        assertEquals(RqesSigningRecord.Outcome.Failed("sign failed"), captured.outcome)
        assertEquals("serial-123", captured.certificateSerialNumber)
    }

    @Test
    fun `an error in the logger does not break signing`() = runTest {
        val logger = RqesSigningLogger { error("boom") }

        val service = scal1Service(logger = logger, signHashSucceeds = true)
        val result = service.signDocuments()

        assertTrue(result.isSuccess)
        assertEquals(1, result.getOrThrow().size)
    }

    @Test
    fun `signing still works when no logger is set`() = runTest {
        val service = scal1Service(logger = null, signHashSucceeds = true)
        val result = service.signDocuments()
        assertTrue(result.isSuccess)
        // With no logger there is nothing to record, and signing should still succeed.
        assertNull(null)
    }

    /**
     * Creates a signing service backed by fake objects. When [signHashSucceeds] is false the signing
     * call fails — after the details the logger reports are already known.
     */
    private fun scal1Service(
        logger: RqesSigningLogger?,
        signHashSucceeds: Boolean,
    ): CredentialAuthorizedImpl {
        val signedFilePath = outputPath + File.separator + "signed_document1.pdf"
        // Write a real file so the logger can read its size from disk.
        File(signedFilePath).apply { parentFile?.mkdirs(); writeBytes(ByteArray(fileSizeBytes)) }
        val mockClient = mockk<CSCClient>(relaxed = true)
        val documentsToSign = listOf<DocumentToSign>(mockk {
            every { label } returns this@RqesSigningLoggerTest.label
            every { documentOutputPath } returns signedFilePath
        })
        val documentDigestList = mockk<DocumentDigestList> {
            every { hashAlgorithmOID } returns HashAlgorithmOID.SHA_256
            every { hashCalculationTime } returns Instant.now()
            every { documentDigests } returns listOf(mockk {
                every { label } returns this@RqesSigningLoggerTest.label
                every { hash } returns mockk { every { asBase64() } returns "dtbsr-1" }
            })
        }
        val credentialAuthorized = mockk<CredentialAuthorized.SCAL1> {
            every { credentialCertificate } returns mockk { every { serialNumber } returns "serial-123" }
        }
        val signatureList = mockk<SignaturesList> { every { signatures } returns listOf(mockk()) }

        val service = CredentialAuthorizedImpl(
            client = mockClient,
            documentDigestList = documentDigestList,
            documentsToSign = documentsToSign,
            credentialAuthorized = credentialAuthorized,
            signingAlgorithm = SigningAlgorithmOID.ECDSA_SHA256,
            signingLogger = logger,
        )

        coEvery {
            with(mockClient) {
                credentialAuthorized.signHash(documentDigestList, service.signingAlgorithm)
            }
        } returns if (signHashSucceeds) {
            Result.success(signatureList)
        } else {
            Result.failure(RuntimeException("sign failed"))
        }
        coEvery { with(mockClient) { createSignedDocuments(signatureList.signatures) } } just runs

        return service
    }
}