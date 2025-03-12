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
import eu.europa.ec.eudi.rqes.core.UnsignedDocuments
import java.io.File

/**
 * Represents a resolved document.
 * @property location The location of the document.
 * @property digest The digest of the document.
 * @property file The downloaded file of the document.
 */
data class ResolvedDocument(
    val location: DocumentLocation,
    val digest: DocumentDigest,
    val file: File
)

/**
 * Convert a list of [ResolvedDocument] to [UnsignedDocuments].
 *
 * Utility function to convert a list of [ResolvedDocument] from [eu.europa.ec.eudi.rqes.core.documentRetrieval.ResolutionOutcome] after
 * [eu.europa.ec.eudi.rqes.core.documentRetrieval.DocumentRetrievalService.resolveDocument]
 *
 * @param signingConfig The configuration for the signing process.
 */
fun List<ResolvedDocument>.toUnsignedDocuments(signingConfig: UnsignedDocument.Config? = null): UnsignedDocuments {
    return UnsignedDocuments(mapIndexed { index, doc ->
        doc.toUnsignedDocument(
            label = "Document $index",
            signingConfig = signingConfig
        )
    })
}


/**
 * Convert a [ResolvedDocument] to an [UnsignedDocument].
 *
 * Utility function to convert a [ResolvedDocument] from [eu.europa.ec.eudi.rqes.core.documentRetrieval.ResolutionOutcome] after
 * [eu.europa.ec.eudi.rqes.core.documentRetrieval.DocumentRetrievalService.resolveDocument]
 *
 * @param label The label of the document.
 * @param signingConfig The configuration for the signing process.
 * @return The [UnsignedDocument]
 */
fun ResolvedDocument.toUnsignedDocument(
    label: String,
    signingConfig: UnsignedDocument.Config? = null
): UnsignedDocument {
    return UnsignedDocument(
        label = label,
        file = file,
        signingConfig = signingConfig ?: UnsignedDocument.Config.DEFAULT
    )
}