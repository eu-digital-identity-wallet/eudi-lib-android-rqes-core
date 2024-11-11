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

import eu.europa.ec.eudi.rqes.ASICContainer
import eu.europa.ec.eudi.rqes.ConformanceLevel
import eu.europa.ec.eudi.rqes.SignatureFormat
import eu.europa.ec.eudi.rqes.SignedEnvelopeProperty
import eu.europa.ec.eudi.rqes.SigningAlgorithmOID
import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

class UnsignedDocumentTest {

    @Test
    fun `test asDocumentToSign transformation`() {
        val documentFile: File = File.createTempFile(UnsignedDocumentTest::class.simpleName!!, ".pdf")
        val unsignedDocument = UnsignedDocument(
            file = documentFile,
            label = "my pdf document",
            signingConfig = UnsignedDocument.Config(
                signatureFormat = SignatureFormat.X,
                conformanceLevel = ConformanceLevel.ADES_T,
                signedEnvelopeProperty = SignedEnvelopeProperty.DETACHED,
                asicContainer = ASICContainer.ASIC_S,
            )
        )

        val documentToSign = unsignedDocument.asDocumentToSign(SigningAlgorithmOID.DSA)

        assertEquals(SignatureFormat.X, documentToSign.signatureFormat)
        assertEquals(ConformanceLevel.ADES_T, documentToSign.conformanceLevel)
        assertEquals(SigningAlgorithmOID.DSA, documentToSign.signAlgo)
        assertEquals(SignedEnvelopeProperty.DETACHED, documentToSign.signedEnvelopeProperty)
        assertEquals(ASICContainer.ASIC_S, documentToSign.asicContainer)
        assertEquals("my pdf document", documentToSign.file.label)
        assertEquals(documentFile, documentToSign.file.content)

    }
}