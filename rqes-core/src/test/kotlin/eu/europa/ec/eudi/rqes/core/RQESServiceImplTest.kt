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

import eu.europa.ec.eudi.rqes.AuthorizationCode
import eu.europa.ec.eudi.rqes.AuthorizationRequestPrepared
import eu.europa.ec.eudi.rqes.CSCClient
import eu.europa.ec.eudi.rqes.CSCClientConfig
import eu.europa.ec.eudi.rqes.HashAlgorithmOID
import eu.europa.ec.eudi.rqes.HttpsUrl
import eu.europa.ec.eudi.rqes.OAuth2Client
import eu.europa.ec.eudi.rqes.ServiceAccessAuthorized
import eu.europa.ec.eudi.rqes.ServiceAuthorizationRequestPrepared
import eu.europa.ec.eudi.rqes.SigningAlgorithmOID
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.slot
import io.mockk.spyk
import io.mockk.unmockkAll
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertThrows
import org.junit.Test
import java.net.URI
import java.net.URL
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertNull
import kotlin.test.assertTrue

class RQESServiceImplTest {

    val mockClient = mockk<CSCClient>(relaxed = true)
    lateinit var service: RQESServiceImpl

    @BeforeTest
    fun setUp() {
        service = RQESServiceImpl(
            serviceEndpointUrl = "https://example.com",
            config = CSCClientConfig(
                client = OAuth2Client.Public("client-id"),
                authFlowRedirectionURI = URI("rqes:redirect"),
                scaBaseURL = URL("https://example.com/sca"),
            ),
            hashAlgorithm = HashAlgorithmOID.SHA_256,
            signingAlgorithm = SigningAlgorithmOID.RSA_SHA256,
        )

        mockkObject(CSCClient.Companion)
        coEvery {
            CSCClient.Companion.oauth2(
                cscClientConfig = any(),
                rsspId = any(),
                ktorHttpClientFactory = any()
            )
        } returns Result.success(mockClient)
    }

    @AfterTest
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `verify getOrCreateClient initializes cscc client and return the same instance on subsequent calls`() =
        runTest {

            assertThrows(UninitializedPropertyAccessException::class.java) {
                service.client
            }

            val client = service.getOrCreateClient()
            val client2 = service.getOrCreateClient()
            assert(client === client2)
        }

    @Test
    fun `verify getRSSPMetadata returns success`() = runTest {
        service.getRSSPMetadata()
        verify(exactly = 1) { mockClient.rsspMetadata }
    }

    @Test
    fun `verify getServiceAuthorizationUrl initializes the serviceAuthRequestPrepared property and return the authorization url `() =
        runTest {
            assertNull(service.serviceAuthRequestPrepared)
            val stateSlot = slot<String>()
            val mockAuthorizationCodeURL = HttpsUrl("https://example.com/auth").getOrThrow()
            val authorizationRequestPrepared = mockk<AuthorizationRequestPrepared> {
                every { authorizationCodeURL } returns mockAuthorizationCodeURL
            }
            val serviceAuthorizationRequestPrepared =
                ServiceAuthorizationRequestPrepared(authorizationRequestPrepared)
            coEvery { mockClient.prepareServiceAuthorizationRequest(capture(stateSlot)) } returns Result.success(
                serviceAuthorizationRequestPrepared
            )

            val authorizationUrl = service.getServiceAuthorizationUrl().getOrThrow()

            assertTrue(stateSlot.isCaptured)
            assertEquals(service.serverState, stateSlot.captured)

            assertEquals(serviceAuthorizationRequestPrepared, service.serviceAuthRequestPrepared)
            assertEquals(mockAuthorizationCodeURL, authorizationUrl)
        }

    @Test
    fun `authorizeService returns failure if getServiceAuthorizationUrl has not been called previously`() =
        runTest {
            val authorizationCode = AuthorizationCode(code = "let me in")

            val result = service.authorizeService(authorizationCode)
            assert(result.isFailure)
            assertIs<IllegalStateException>(result.exceptionOrNull())
        }

    @Test
    fun `authorizeService returns authorized`() =
        runTest {
            val mockAuthorizationCodeURL = HttpsUrl("https://example.com/auth").getOrThrow()
            val authorizationRequestPrepared = mockk<AuthorizationRequestPrepared>() {
                every { authorizationCodeURL } returns mockAuthorizationCodeURL
            }
            val serviceAuthorizationRequestPrepared: ServiceAuthorizationRequestPrepared =
                spyk(ServiceAuthorizationRequestPrepared(authorizationRequestPrepared))

            val serviceAccessAuthorized: ServiceAccessAuthorized = mockk(relaxed = true)

            service.client = mockClient
            service.serviceAuthRequestPrepared = serviceAuthorizationRequestPrepared
            service.serverState = "server-state"

            val authorizationCode = AuthorizationCode(code = "let me in")
            coEvery {
                with(mockClient) {
                    serviceAuthorizationRequestPrepared.authorizeWithAuthorizationCode(
                        authorizationCode,
                        service.serverState
                    )
                }
            } returns Result.success(serviceAccessAuthorized)

            val result = service.authorizeService(authorizationCode)
            assert(result.isSuccess)
        }
}