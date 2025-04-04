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

import eu.europa.ec.eudi.documentretrieval.DispatchOutcome
import eu.europa.ec.eudi.rqes.core.SignedDocuments

/**
 * The outcome of the resolution of the document retrieval.
 * @property resolvedDocuments The resolved documents.
 */
interface ResolutionOutcome {

    val resolvedDocuments: List<ResolvedDocument>

    /**
     * Dispatches the signed documents.
     *
     * @param signedDocuments The signed documents.
     * @return The outcome of the dispatch.
     */
    suspend fun dispatch(signedDocuments: SignedDocuments): DispatchOutcome
}