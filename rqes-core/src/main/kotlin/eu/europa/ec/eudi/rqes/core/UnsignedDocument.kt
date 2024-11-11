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

import eu.europa.ec.eudi.rqes.ASICContainer
import eu.europa.ec.eudi.rqes.ConformanceLevel
import eu.europa.ec.eudi.rqes.Document
import eu.europa.ec.eudi.rqes.DocumentToSign
import eu.europa.ec.eudi.rqes.SignatureFormat
import eu.europa.ec.eudi.rqes.SignedEnvelopeProperty
import eu.europa.ec.eudi.rqes.SigningAlgorithmOID
import java.io.File

/**
 * Represents a document that is to be signed.
 * @param label The label of the document.
 * @param file The file to be signed.
 * @param signingConfig The configuration for the signing process.
 *
 * @property label The label of the document.
 * @property file The file to be signed.
 * @property signingConfig The configuration for the signing process.
 */
data class UnsignedDocument(
    val label: String,
    val file: File,
    val signingConfig: Config = Config.DEFAULT
) {

    /**
     * Configuration for the signing process.
     * @param signatureFormat The signature format.
     * @param conformanceLevel The conformance level.
     * @param signedEnvelopeProperty The signed envelope property.
     * @param asicContainer The ASiC container.
     *
     * @property signatureFormat The signature format.
     * @property conformanceLevel The conformance level.
     * @property signedEnvelopeProperty The signed envelope property.
     * @property asicContainer The ASiC container.
     */
    data class Config(
        val signatureFormat: SignatureFormat,
        val conformanceLevel: ConformanceLevel,
        val signedEnvelopeProperty: SignedEnvelopeProperty,
        val asicContainer: ASICContainer
    ) {
        /**
         * Companion object for the [Config] class.
         *
         * @property DEFAULT The default configuration.
         */
        companion object {
            val DEFAULT = Config(
                signatureFormat = SignatureFormat.P,
                conformanceLevel = ConformanceLevel.ADES_B_B,
                signedEnvelopeProperty = SignedEnvelopeProperty.ENVELOPED,
                asicContainer = ASICContainer.NONE
            )
        }
    }

    /**
     * Converts this [UnsignedDocument] to a [DocumentToSign].
     */
    internal fun asDocumentToSign(signingAlgorithmOID: SigningAlgorithmOID): DocumentToSign =
        DocumentToSign(
            file = Document(file, label),
            signatureFormat = signingConfig.signatureFormat,
            conformanceLevel = signingConfig.conformanceLevel,
            signedEnvelopeProperty = signingConfig.signedEnvelopeProperty,
            asicContainer = signingConfig.asicContainer,
            signAlgo = signingAlgorithmOID,
        )
}