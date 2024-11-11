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

import java.io.File
import kotlin.test.Test

class UnsignedDocumentsTest {

    @Test
    fun `test secondary constructor with varargs`() {

        val file1 = File.createTempFile(UnsignedDocumentsTest::class.simpleName!!, "file1")
        val file2 = File.createTempFile(UnsignedDocumentsTest::class.simpleName!!, "file2")
        val file3 = File.createTempFile(UnsignedDocumentsTest::class.simpleName!!, "file3")
        val unsignedDocuments = UnsignedDocuments(
            UnsignedDocument(file = file1, label = "label1"),
            UnsignedDocument(file = file2, label = "label2"),
            UnsignedDocument(file = file3, label = "label3"),
        )

        assert(unsignedDocuments.size == 3)
        assert(unsignedDocuments[0].file == file1)
        assert(unsignedDocuments[0].label == "label1")
        assert(unsignedDocuments[1].file == file2)
        assert(unsignedDocuments[1].label == "label2")
        assert(unsignedDocuments[2].file == file3)
        assert(unsignedDocuments[2].label == "label3")
    }
}