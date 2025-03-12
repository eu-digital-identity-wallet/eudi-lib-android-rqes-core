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
import java.io.ByteArrayOutputStream

class ResolutionOutcomeImpl(
    override val resolvedDocuments: List<ResolvedDocument>,
    @VisibleForTesting internal val requestObject: ResolvedRequestObject,
    @VisibleForTesting internal val client: DocumentRetrieval,
) : ResolutionOutcome {

    override suspend fun dispatch(signedDocuments: SignedDocuments): DispatchOutcome {
        return client.dispatch(
            request = requestObject,
            Consensus.Positive(
                documentWithSignature = signedDocuments.map { inputStream ->
                    ByteArrayOutputStream().use {
                        inputStream.copyTo(it)
                        it.toByteArray().decodeToString()
                    }
                },
                signatureObject = null
            )
        )
    }
}