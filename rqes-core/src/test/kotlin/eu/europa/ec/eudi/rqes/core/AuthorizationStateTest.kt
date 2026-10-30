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

import eu.europa.ec.eudi.rqes.AuthorizationCode
import eu.europa.ec.eudi.rqes.AuthorizationError
import eu.europa.ec.eudi.rqes.CSCClientConfig
import eu.europa.ec.eudi.rqes.HashAlgorithmOID
import eu.europa.ec.eudi.rqes.OAuth2Client
import eu.europa.ec.eudi.rqes.ParUsage
import eu.europa.ec.eudi.rqes.SigningAlgorithmOID
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.engine.mock.respondError
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.Url
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import java.net.URI
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

/**
 * Runs the service authorization with the CSC client, mocking only the HTTP responses, so that the
 * comparison of the `state` received with the authorization code is the one of the CSC client.
 */
class AuthorizationStateTest {

    private val tokenRequests = mutableListOf<Url>()

    private val httpClientFactory: () -> HttpClient = {
        HttpClient(MockEngine) {
            engine {
                addHandler { request ->
                    val json = headersOf(HttpHeaders.ContentType, "application/json")
                    val path = request.url.encodedPath
                    when {
                        path.endsWith("/info") ->
                            respond(RSSP_METADATA, HttpStatusCode.OK, json)

                        path.endsWith("/token") -> {
                            tokenRequests += request.url
                            respond(TOKEN_RESPONSE, HttpStatusCode.OK, json)
                        }

                        else -> respondError(HttpStatusCode.NotFound)
                    }
                }
            }
            install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) }
        }
    }

    private val service = RQESServiceImpl(
        serviceEndpointUrl = "https://rssp.example.com/csc/v2",
        config = CSCClientConfig(
            client = OAuth2Client.Public("client-id"),
            authFlowRedirectionURI = URI("rqes://oauth/callback"),
            parUsage = ParUsage.Never,
        ),
        outputPathDir = "/tmp",
        hashAlgorithm = HashAlgorithmOID.SHA_256,
        signingAlgorithm = RQESService.SigningAlgorithm.Specific(SigningAlgorithmOID.ECDSA_SHA256),
        clientFactory = httpClientFactory,
    )

    @Test
    fun `authorizeService rejects a state that does not match the request without calling the token endpoint`() =
        runTest {
            val authorizationUrl = service.getServiceAuthorizationUrl().getOrThrow()
            val stateOfTheRequest = Url(authorizationUrl.value.toString()).parameters["state"]
            assertNotNull(stateOfTheRequest)

            val forged = service.authorizeService(AuthorizationCode("forged-code"), "forged-state")
            assertIs<AuthorizationError.InvalidAuthorizationState>(forged.exceptionOrNull())
            assertTrue(tokenRequests.isEmpty())

            val genuine = service.authorizeService(AuthorizationCode("code"), stateOfTheRequest)
            assertTrue(genuine.isSuccess)
            assertEquals(1, tokenRequests.size)
        }

    private companion object {
        const val RSSP_METADATA = """
            {
              "specs": "2.2.0.0",
              "name": "ACME Trust Services",
              "logo": "https://rssp.example.com/logo.png",
              "region": "IT",
              "lang": "en-US",
              "description": "A remote signature service",
              "authType": ["oauth2code"],
              "oauth2": "https://auth.example.com",
              "methods": ["info", "credentials/list", "credentials/info", "signatures/signHash"],
              "signAlgorithms": { "algos": ["1.2.840.10045.4.3.2"] },
              "signature_formats": { "formats": ["P"], "envelope_properties": [["Enveloped"]] },
              "conformance_levels": ["Ades-B-B"],
              "supportedHashTypes": ["SHA-256"]
            }
        """

        const val TOKEN_RESPONSE = """{"access_token":"at","token_type":"Bearer","expires_in":3600}"""
    }
}
