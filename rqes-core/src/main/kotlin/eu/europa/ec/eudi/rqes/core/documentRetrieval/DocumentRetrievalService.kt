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

import android.content.Context
import android.net.Uri
import eu.europa.ec.eudi.documentretrieval.DocumentRetrievalConfig
import eu.europa.ec.eudi.rqes.DefaultHttpClientFactory
import eu.europa.ec.eudi.rqes.KtorHttpClientFactory
import java.io.File

/**
 * Retrieves documents from the given URI. The documents are then are to be signed using
 * [eu.europa.ec.eudi.rqes.core.RQESService]. The signed documents are then dispatched through [ResolutionOutcome.dispatch].
 */
interface DocumentRetrievalService {
    /**
     * Retrieves the documents from the given URI.
     * @param requestUri The URI of the document to be retrieved.
     * @return The outcome of the resolution.
     */
    suspend fun resolveDocument(requestUri: Uri): Result<ResolutionOutcome>

    /**
     * Companion object to create instances of [DocumentRetrievalService].
     */
    companion object {

        /**
         * Creates an instance of [DocumentRetrievalService].
         */
        operator fun invoke(
            downloadTempDir: File,
            config: DocumentRetrievalConfig,
            httpClientFactory: KtorHttpClientFactory = DefaultHttpClientFactory
        ): DocumentRetrievalService =
            DocumentRetrievalServiceImpl(downloadTempDir, config, httpClientFactory)
    }
}

