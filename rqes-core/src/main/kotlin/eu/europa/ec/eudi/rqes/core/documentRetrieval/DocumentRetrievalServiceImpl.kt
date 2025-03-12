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

import android.net.Uri
import eu.europa.ec.eudi.documentretrieval.DocumentRetrieval
import eu.europa.ec.eudi.documentretrieval.DocumentRetrievalConfig
import eu.europa.ec.eudi.documentretrieval.Resolution
import eu.europa.ec.eudi.documentretrieval.asException
import eu.europa.ec.eudi.rqes.DefaultHttpClientFactory
import eu.europa.ec.eudi.rqes.HashAlgorithmOID
import eu.europa.ec.eudi.rqes.KtorHttpClientFactory
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readRawBytes
import io.ktor.http.isSuccess
import org.jetbrains.annotations.VisibleForTesting
import java.io.File
import java.security.MessageDigest
import java.util.Base64

class DocumentRetrievalServiceImpl internal constructor(
    @VisibleForTesting internal val downloadTempDir: File,
    @VisibleForTesting internal val client: DocumentRetrieval,
    @VisibleForTesting internal val httpClientFactory: KtorHttpClientFactory = DefaultHttpClientFactory,
) : DocumentRetrievalService {

    override suspend fun resolveDocument(requestUri: Uri): Result<ResolutionOutcome> {
        return when (val resolution = client.resolveRequestUri(requestUri.toString())) {
            is Resolution.Invalid -> Result.failure(resolution.error.asException())
            is Resolution.Success -> resolution.requestObject.documentLocations
                .zip(resolution.requestObject.documentDigests) { location, digest ->
                    httpClientFactory().get(location.uri).let { response ->
                        if (response.status.isSuccess().not()) {
                            return Result.failure(
                                IllegalStateException("Document retrieval failed with status ${response.status}")
                            )
                        }

                        val (documentContent, documentHash) = response
                            .readDocument(resolution.requestObject.hashAlgorithmOID)
                        if (digest.hash != documentHash) {
                            return Result.failure(
                                IllegalStateException("Document hash check failed")
                            )
                        }
                        val file = File(downloadTempDir, digest.label).apply {
                            outputStream().use { documentContent.inputStream().copyTo(it) }
                        }
                        ResolvedDocument(
                            location = location,
                            digest = digest,
                            file = file,
                        )
                    }
                }
                .let {
                    Result.success(
                        ResolutionOutcomeImpl(
                            resolvedDocuments = it,
                            requestObject = resolution.requestObject,
                            client = client
                        )
                    )
                }
        }
    }

    companion object {

        operator fun invoke(
            downloadTempDir: File,
            config: DocumentRetrievalConfig,
            httpClientFactory: KtorHttpClientFactory = DefaultHttpClientFactory,
        ): DocumentRetrievalServiceImpl = DocumentRetrievalServiceImpl(
            downloadTempDir = downloadTempDir,
            client = DocumentRetrieval(config, httpClientFactory),
            httpClientFactory = httpClientFactory,
        )

        private suspend fun HttpResponse.readDocument(hashAlgorithm: HashAlgorithmOID): Pair<ByteArray, String> {
            val documentContent = readRawBytes()
            val messageDigest = MessageDigest.getInstance(hashAlgorithm.value)
            val documentHash = messageDigest.digest(documentContent).let {
                Base64.getEncoder().encodeToString(it)
            }
            return documentContent to documentHash
        }
    }
}



