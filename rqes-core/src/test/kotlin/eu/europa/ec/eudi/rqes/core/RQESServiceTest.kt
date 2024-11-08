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

import eu.europa.ec.eudi.rqes.CSCClientConfig
import eu.europa.ec.eudi.rqes.OAuth2Client
import io.ktor.client.HttpClient
import io.mockk.mockk
import java.net.URI
import java.net.URL
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertNull

class RQESServiceTest {

    @Test
    fun `Companion object construct RQESService`() {

        val config = CSCClientConfig(
            client = OAuth2Client.Confidential.ClientSecretBasic(
                clientId = "client-id",
                clientSecret = "client-secret"
            ),
            authFlowRedirectionURI = URI("rqes:redirection"),
            scaBaseURL = URL("https://example.com"),
        )
        val mockkHttpClientFactory: () -> HttpClient = mockk()
        val rqesService = RQESService(
            serviceEndpointUrl = "https://example.com/csc/v2",
            config = config,
            httpClientFactory = mockkHttpClientFactory

        )

        assertIs<RQESServiceImpl>(rqesService)
        assertEquals("https://example.com/csc/v2", rqesService.serviceEndpointUrl)
        assertEquals(config, rqesService.config)
        assertEquals(mockkHttpClientFactory, rqesService.clientFactory)
    }

    @Test
    fun `Companion object construct RQESService without httpClientFactory`() {

        val config = CSCClientConfig(
            client = OAuth2Client.Confidential.ClientSecretBasic(
                clientId = "client-id",
                clientSecret = "client-secret"
            ),
            authFlowRedirectionURI = URI("rqes:redirection"),
            scaBaseURL = URL("https://example.com"),
        )
        val rqesService = RQESService(
            serviceEndpointUrl = "https://example.com/csc/v2",
            config = config,

        )

        assertIs<RQESServiceImpl>(rqesService)
        assertEquals("https://example.com/csc/v2", rqesService.serviceEndpointUrl)
        assertEquals(config, rqesService.config)
        assertNull(rqesService.clientFactory)
    }

}