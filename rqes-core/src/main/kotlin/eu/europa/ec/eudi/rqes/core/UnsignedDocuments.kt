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

import eu.europa.ec.eudi.rqes.DocumentToSign

/**
 * A collection of [UnsignedDocument]s that are to be signed.
 *
 * This class provides a convenient way to work with multiple unsigned documents as a single unit.
 * It implements the [List] interface for easy iteration and access to the underlying documents.
 *
 * @property unsignedDocuments The underlying list of [UnsignedDocument]s to be signed.
 * @constructor Creates a new [UnsignedDocuments] with the given list of [UnsignedDocument]s and hash algorithm.
 *
 * @param unsignedDocuments The list of [UnsignedDocument]s to be signed.
 */
class UnsignedDocuments(
    private val unsignedDocuments: List<UnsignedDocument>
) : List<UnsignedDocument> by unsignedDocuments {

    /**
     * Creates a new [UnsignedDocuments] with the given vararg [UnsignedDocument]s.
     *
     * @param unsignedDocuments The vararg [UnsignedDocument]s to be signed.
     */
    constructor(vararg unsignedDocuments: UnsignedDocument) : this(unsignedDocuments.toList())

    /**
     * Converts this [UnsignedDocuments] to a list of [DocumentToSign]s.
     *
     * This internal method transforms each unsigned document into the format required
     * for the actual signing process. It creates output paths for each document in
     * the specified output directory.
     *
     * @param outputPathDir The directory where the signed documents will be stored.
     * @return A list of [DocumentToSign] objects ready for the signing process.
     */
    internal fun asDocumentToSignList(outputPathDir: String): List<DocumentToSign> =
        map { it.asDocumentToSign(outputPathDir) }
}