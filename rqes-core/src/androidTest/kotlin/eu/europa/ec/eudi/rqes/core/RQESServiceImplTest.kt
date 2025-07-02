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

package eu.europa.ec.eudi.rqes.core

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import eu.europa.ec.eudi.rqes.CSCClientConfig
import eu.europa.ec.eudi.rqes.OAuth2Client
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import java.net.URI
import java.net.URL

@RunWith(AndroidJUnit4::class)
class RQESServiceImplTest {

    @Test
    fun build() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
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
            outputPathDir = context.cacheDir.absolutePath,
        )
        assertNotNull(rqesService)
    }
}
