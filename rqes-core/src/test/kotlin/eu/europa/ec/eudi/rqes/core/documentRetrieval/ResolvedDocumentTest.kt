/*
 * Copyright (c) 2025 European Commission
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

package eu.europa.ec.eudi.rqes.core.documentRetrieval

import eu.europa.ec.eudi.documentretrieval.DocumentDigest
import eu.europa.ec.eudi.documentretrieval.DocumentLocation
import eu.europa.ec.eudi.rqes.core.UnsignedDocument
import io.mockk.mockk
import org.junit.Test
import kotlin.io.path.createTempFile
import kotlin.test.assertEquals

class ResolvedDocumentTest {

    @Test
    fun convertResolvedDocumentToUnsignedDocument() {
        val file = createTempFile().toFile()
        val resolvedDocument = ResolvedDocument(
            location = mockk<DocumentLocation>(),
            digest = mockk<DocumentDigest>(),
            file = file
        )
        val unsignedDocument = resolvedDocument.toUnsignedDocument("Test Document")

        assertEquals("Test Document", unsignedDocument.label)
        assertEquals(file, unsignedDocument.file)
        assertEquals(UnsignedDocument.Config.DEFAULT, unsignedDocument.signingConfig)
    }

    @Test
    fun convertResolvedDocumentToUnsignedDocumentWithCustomConfig() {
        val file = createTempFile().toFile()
        val resolvedDocument = ResolvedDocument(
            location = mockk<DocumentLocation>(),
            digest = mockk<DocumentDigest>(),
            file = file
        )
        val customConfig = UnsignedDocument.Config.DEFAULT
        val unsignedDocument = resolvedDocument.toUnsignedDocument("Test Document", customConfig)

        assertEquals("Test Document", unsignedDocument.label)
        assertEquals(file, unsignedDocument.file)
        assertEquals(customConfig, unsignedDocument.signingConfig)
    }

    @Test
    fun convertListOfResolvedDocumentsToUnsignedDocuments() {
        val file1 = createTempFile().toFile()
        val file2 = createTempFile().toFile()
        val resolvedDocuments = listOf(
            ResolvedDocument(
                location = mockk<DocumentLocation>(),
                digest = mockk<DocumentDigest>(),
                file = file1
            ),
            ResolvedDocument(
                location = mockk<DocumentLocation>(),
                digest = mockk<DocumentDigest>(),
                file = file2
            )
        )
        val unsignedDocuments = resolvedDocuments.toUnsignedDocuments()

        assertEquals(2, unsignedDocuments.size)
        assertEquals("Document 0", unsignedDocuments[0].label)
        assertEquals(file1, unsignedDocuments[0].file)
        assertEquals("Document 1", unsignedDocuments[1].label)
        assertEquals(file2, unsignedDocuments[1].file)
    }

    @Test
    fun convertListOfResolvedDocumentsToUnsignedDocumentsWithCustomConfig() {
        val file1 = createTempFile().toFile()
        val file2 = createTempFile().toFile()
        val resolvedDocuments = listOf(
            ResolvedDocument(
                location = mockk<DocumentLocation>(),
                digest = mockk<DocumentDigest>(),
                file = file1
            ),
            ResolvedDocument(
                location = mockk<DocumentLocation>(),
                digest = mockk<DocumentDigest>(),
                file = file2
            )
        )
        val customConfig = UnsignedDocument.Config.DEFAULT
        val unsignedDocuments = resolvedDocuments.toUnsignedDocuments(customConfig)

        assertEquals(2, unsignedDocuments.size)
        assertEquals("Document 0", unsignedDocuments[0].label)
        assertEquals(file1, unsignedDocuments[0].file)
        assertEquals(customConfig, unsignedDocuments[0].signingConfig)
        assertEquals("Document 1", unsignedDocuments[1].label)
        assertEquals(file2, unsignedDocuments[1].file)
        assertEquals(customConfig, unsignedDocuments[1].signingConfig)
    }
}