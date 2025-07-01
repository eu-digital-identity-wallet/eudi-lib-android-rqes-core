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

import eu.europa.ec.eudi.documentretrieval.Consensus
import eu.europa.ec.eudi.documentretrieval.DispatchOutcome
import eu.europa.ec.eudi.documentretrieval.DocumentRetrieval
import eu.europa.ec.eudi.documentretrieval.ResolvedRequestObject
import eu.europa.ec.eudi.rqes.core.SignedDocuments
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.net.URI
import kotlin.io.path.createTempFile
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ResolutionOutcomeImplTest {

    private val resolvedDocuments = listOf<ResolvedDocument>()
    private val requestObject = mockk<ResolvedRequestObject>()
    private val client = mockk<DocumentRetrieval>()
    private val resolutionOutcome = ResolutionOutcomeImpl(resolvedDocuments, requestObject, client)

    @Test
    fun dispatch_acceptedDispatch() = runTest {
        val signedDocuments = SignedDocuments(
            mapOf(
                "file1" to createTempFile().toFile().apply {
                    writeBytes("signed document 1".toByteArray())
                },
                "file2" to createTempFile().toFile().apply {
                    writeBytes("signed document 2".toByteArray())
                },
            )
        )
        val consensus = Consensus.Positive(
            documentWithSignature = listOf(
                "signed document 1",
                "signed document 2"
            ),
            signatureObject = null
        )

        val redirectUri = mockk<URI>()
        val dispatchOutcome = DispatchOutcome.Accepted(redirectURI = redirectUri)


        coEvery { client.dispatch(requestObject, consensus) } returns dispatchOutcome

        val result = resolutionOutcome.dispatch(signedDocuments)

        coVerify(exactly = 1) { client.dispatch(any(), any()) }
        assertEquals(dispatchOutcome, result)
    }

    @Test
    fun dispatch_rejectedDispatch() = runTest {

        val signedDocuments = SignedDocuments(
            mapOf(
                "file1" to createTempFile().toFile(),
                "file2" to createTempFile().toFile(),
            )
        )
        val dispatchOutcome = DispatchOutcome.Rejected
        coEvery { client.dispatch(any(), any()) } returns dispatchOutcome

        val result = resolutionOutcome.dispatch(signedDocuments)
        assertEquals(dispatchOutcome, result)
    }

    @Test
    fun dispatch_failureDispatch() = runTest {
        val signedDocuments = SignedDocuments(
            mapOf(
                "file1" to createTempFile().toFile(),
                "file2" to createTempFile().toFile(),
            )
        )
        val exception = Exception("Dispatch failed")
        coEvery { client.dispatch(any(), any()) } throws exception

        val result = assertFailsWith<Exception> { resolutionOutcome.dispatch(signedDocuments) }
        assertEquals("Dispatch failed", result.message)
    }
}