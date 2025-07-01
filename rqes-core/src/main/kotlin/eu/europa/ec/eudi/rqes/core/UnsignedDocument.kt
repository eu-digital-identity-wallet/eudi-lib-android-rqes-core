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

import eu.europa.ec.eudi.rqes.ASICContainer
import eu.europa.ec.eudi.rqes.ConformanceLevel
import eu.europa.ec.eudi.rqes.DocumentToSign
import eu.europa.ec.eudi.rqes.SignatureFormat
import eu.europa.ec.eudi.rqes.SignedEnvelopeProperty
import eu.europa.ec.eudi.rqes.core.UnsignedDocument.Config.Companion.DEFAULT
import java.io.File

/**
 * Represents a document that is to be signed.
 *
 * This class encapsulates a file along with its identifying label and configuration
 * parameters that determine how the document should be signed.
 *
 * @property label The identifying label of the document.
 * @property file The file to be signed.
 * @property signingConfig The configuration for the signing process.
 */
data class UnsignedDocument(
    val label: String,
    val file: File,
    val signingConfig: Config = DEFAULT
) {

    init {
        // check that file is not dir and exists
        require(file.isFile) { "File must be a file" }
    }

    /**
     * Configuration for the signing process.
     *
     * This class defines the parameters that control how a document will be signed,
     * including format, compliance level, and container specifications.
     *
     * @property signatureFormat The signature format to be used.
     * @property conformanceLevel The conformance level to be applied to the signature.
     * @property signedEnvelopeProperty The type of envelope property for the signature.
     * @property asicContainer The Associated Signature Container (ASiC) configuration.
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
     * @return The [DocumentToSign] object.
     */
    internal fun asDocumentToSign(outputPathDir: String): DocumentToSign =
        DocumentToSign(
            documentInputPath = file.absolutePath,
            documentOutputPath = File(outputPathDir, "signed_${file.name}").absolutePath,
            label = label,
            signatureFormat = signingConfig.signatureFormat,
            conformanceLevel = signingConfig.conformanceLevel,
            signedEnvelopeProperty = signingConfig.signedEnvelopeProperty,
            asicContainer = signingConfig.asicContainer,
        )
}