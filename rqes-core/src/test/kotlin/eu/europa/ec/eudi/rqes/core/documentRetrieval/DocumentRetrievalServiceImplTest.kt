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
import eu.europa.ec.eudi.documentretrieval.AccessMethod
import eu.europa.ec.eudi.documentretrieval.AuthorizationRequestException
import eu.europa.ec.eudi.documentretrieval.DocumentDigest
import eu.europa.ec.eudi.documentretrieval.DocumentLocation
import eu.europa.ec.eudi.documentretrieval.DocumentRetrieval
import eu.europa.ec.eudi.documentretrieval.RequestValidationError
import eu.europa.ec.eudi.documentretrieval.Resolution
import eu.europa.ec.eudi.documentretrieval.ResolvedRequestObject
import eu.europa.ec.eudi.rqes.HashAlgorithmOID
import eu.europa.ec.eudi.rqes.KtorHttpClientFactory
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpStatusCode
import io.ktor.util.encodeBase64
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkConstructor
import io.mockk.unmockkAll
import kotlinx.coroutines.test.runTest
import java.net.URL
import java.nio.file.Files
import java.security.MessageDigest
import java.util.Base64
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class DocumentRetrievalServiceImplTest {

    private lateinit var client: DocumentRetrieval
    private lateinit var httpClientFactory: KtorHttpClientFactory
    private lateinit var service: DocumentRetrievalServiceImpl
    private lateinit var requestUri: Uri
    private val documentContent = "document content"


    @BeforeTest
    fun setUp() {
        requestUri = mockk<Uri>(relaxed = true)
        every { requestUri.toString() } returns """
                    mdoc-openid4vp://https//walletcentric.signer.eudiw.dev/rp?request_uri=
                    https://walletcentric.signer.eudiw.dev/rp/wallet/sd/LAIfYMo0H4O3L8Ua9AQutH7IWPQxrXpaTb3IfoQNze0
                    &client_id=walletcentric.signer.eudiw.dev
                    """.trimIndent().replace("\n", "")

        client = mockk()
        httpClientFactory = {
            HttpClient(MockEngine) {
                engine {
                    addHandler { request ->
                        if (request.url.encodedPath == "/document") {
                            respond(documentContent, HttpStatusCode.OK)
                        } else {
                            respond("not found", HttpStatusCode.NotFound)
                        }
                    }
                }
            }
        }
        service = DocumentRetrievalServiceImpl(
            downloadTempDir = Files.createTempDirectory(this::class.java.name).toFile(),
            client = client,
            httpClientFactory = httpClientFactory
        )
    }

    @AfterTest
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun resolveDocument_successfulResolution() = runTest {

        coEvery { client.resolveRequestUri(any()) } returns Resolution.Success(
            requestObject = mockk<ResolvedRequestObject> {
                every { documentLocations } returns listOf(
                    DocumentLocation(
                        uri = URL("http://example.com/document"),
                        method = AccessMethod.Public
                    )
                )
                every { documentDigests } returns listOf(
                    DocumentDigest(
                        hash = MessageDigest.getInstance("SHA-256")
                            .digest(documentContent.toByteArray())
                            .encodeBase64(),
                        label = "document",
                    )
                )
                every { hashAlgorithmOID } returns HashAlgorithmOID.SHA_256
            }
        )

        val documentHash = Base64.getEncoder()
            .encodeToString(
                MessageDigest.getInstance("SHA-256").digest(documentContent.toByteArray())
            )

        mockkConstructor(DocumentRetrieval::class)
        coEvery { anyConstructed<DocumentRetrieval>().resolveRequestUri(any()) } returns Resolution.Success(
            requestObject = mockk {
                every { documentLocations } returns listOf(mockk { every { uri } returns URL("http://example.com/document") })
                every { documentDigests } returns listOf(mockk { every { hash } returns documentHash })
                every { hashAlgorithmOID } returns HashAlgorithmOID("SHA-256")
            }
        )

        val result = service.resolveDocument(requestUri)
        assertTrue(result.isSuccess)
        assertEquals(1, result.getOrThrow().resolvedDocuments.size)
    }

    @Test
    fun resolveDocument_invalidResolution() = runTest {
        coEvery { client.resolveRequestUri(any()) } returns Resolution.Invalid(error = RequestValidationError.MissingDocumentDigests)

        val error = AuthorizationRequestException(RequestValidationError.MissingDocumentDigests)


        val result = service.resolveDocument(requestUri)
        assertTrue(result.isFailure)
        assertEquals(error, result.exceptionOrNull())
    }

    @Test
    fun resolveDocument_documentRetrievalFailure() = runTest {
        coEvery { client.resolveRequestUri(any()) } returns Resolution.Success(
            requestObject = mockk<ResolvedRequestObject> {
                every { documentLocations } returns listOf(
                    DocumentLocation(
                        uri = URL("http://example.com/not_found_path"),
                        method = AccessMethod.Public
                    )
                )
                every { documentDigests } returns listOf(
                    DocumentDigest(
                        hash = MessageDigest.getInstance("SHA-256")
                            .digest(documentContent.toByteArray())
                            .encodeBase64(),
                        label = "document",
                    )
                )
                every { hashAlgorithmOID } returns HashAlgorithmOID.SHA_256
            }
        )


        val result = service.resolveDocument(requestUri)
        assertTrue(result.isFailure)
        assertFailsWith<IllegalStateException>("Document retrieval failed with status 404") { result.getOrThrow() }
    }

    @Test
    fun resolveDocument_documentHashCheckFailure() = runTest {

        coEvery { client.resolveRequestUri(any()) } returns Resolution.Success(
            requestObject = mockk<ResolvedRequestObject> {
                every { documentLocations } returns listOf(
                    DocumentLocation(
                        uri = URL("http://example.com/document"),
                        method = AccessMethod.Public
                    )
                )
                every { documentDigests } returns listOf(
                    DocumentDigest(
                        hash = MessageDigest.getInstance("SHA-256")
                            .digest("different content".toByteArray())
                            .encodeBase64(),
                        label = "document",
                    )
                )
                every { hashAlgorithmOID } returns HashAlgorithmOID.SHA_256
            }
        )

        val result = service.resolveDocument(requestUri)
        assertTrue(result.isFailure)
        assertFailsWith<IllegalStateException>("Document hash check failed") { result.getOrThrow() }
    }
}