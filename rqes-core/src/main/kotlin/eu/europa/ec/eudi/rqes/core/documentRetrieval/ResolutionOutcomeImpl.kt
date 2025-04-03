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
import org.jetbrains.annotations.VisibleForTesting

/**
 * Implementation of the ResolutionOutcome interface.
 * @property resolvedDocuments the list of resolved documents
 *
 * @constructor Creates ResolutionOutcomeImpl with the given parameters.
 * @param resolvedDocuments the list of resolved documents
 * @param requestObject the resolved request object
 * @param client the Document Retrieval client
 */
class ResolutionOutcomeImpl(
    override val resolvedDocuments: List<ResolvedDocument>,
    @VisibleForTesting internal val requestObject: ResolvedRequestObject,
    @VisibleForTesting internal val client: DocumentRetrieval,
) : ResolutionOutcome {

    /**
     * Dispatches the signed documents to the Document Retrieval service.
     *
     * This method reads the input stream of each signed document, resets it if supported,
     * and converts it to a string representation before dispatching.
     *
     * @param signedDocuments the signed documents to be dispatched
     * @return the outcome of the dispatch operation
     * @throws java.io.IOException if an I/O error occurs during reading the input stream of the signed documents
     */
    override suspend fun dispatch(signedDocuments: SignedDocuments): DispatchOutcome {
        val documentWithSignature =
            signedDocuments.map { inputStream ->
                // reset inputStream to the beginning if it supports mark/reset
                // otherwise read the bytes from the current position
                // we need to read the bytes again because if the inputStream is consumed
                // we cannot dispatch it
                if (inputStream.markSupported()) {
                    inputStream.reset()
                }
                inputStream.readBytes().decodeToString()
            }

        return client.dispatch(
            request = requestObject,
            consensus = Consensus.Positive(
                documentWithSignature = documentWithSignature,
                signatureObject = null
            )
        )
    }
}