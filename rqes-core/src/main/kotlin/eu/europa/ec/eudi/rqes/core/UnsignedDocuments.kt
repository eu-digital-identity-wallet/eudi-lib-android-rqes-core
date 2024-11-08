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

import eu.europa.ec.eudi.rqes.DocumentToSign
import eu.europa.ec.eudi.rqes.HashAlgorithmOID

/**
 * A list of [UnsignedDocument]s to be signed.
 * @constructor Creates a new [UnsignedDocuments] with the given list of [UnsignedDocument]s and hash algorithm.
 *
 * @param unsignedDocuments The list of [UnsignedDocument]s to be signed.
 * @param hashAlgorithmOID The hash algorithm to be used to hash the documents.
 *
 * @property hashAlgorithmOID The hash algorithm to be used to hash the documents.
 */
class UnsignedDocuments(
    unsignedDocuments: List<UnsignedDocument>,
    val hashAlgorithmOID: HashAlgorithmOID = HashAlgorithmOID.SHA_256
) : List<UnsignedDocument> by unsignedDocuments {
    /**
     * Converts this [UnsignedDocuments] to a list of [DocumentToSign]s.
     */
    internal val asDocumentToSignList: List<DocumentToSign> = map { it.asDocumentToSign }
}