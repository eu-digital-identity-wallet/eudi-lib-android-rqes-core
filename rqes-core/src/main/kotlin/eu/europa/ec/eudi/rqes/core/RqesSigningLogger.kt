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

/**
 * Gets called whenever a signing operation finishes.
 *
 * Implement it to keep track of signings — for example to save a history, collect statistics, or
 * update your app. You only get information about the signing, never the signed files themselves.
 *
 * Providing it is optional.
 */
fun interface RqesSigningLogger {

    /**
     * Called when a signing operation has finished, whether it worked or failed. Look at
     * [RqesSigningRecord.outcome] to see which one happened.
     *
     * @param record information about the signing that just finished.
     */
    fun onSigningCompleted(record: RqesSigningRecord)
}

/**
 * Information about a signing operation that has finished. Never includes the signed files themselves.
 *
 * @property outcome whether the signing succeeded or failed.
 * @property certificateSerialNumber the serial number of the certificate used to sign, if known.
 * @property documents information about each document that was signed.
 * @property serviceName the display name of the signing service, if known.
 */
data class RqesSigningRecord(
    val outcome: Outcome,
    val certificateSerialNumber: String?,
    val documents: List<SignedDocument>,
    val serviceName: LocalizedName? = null,
) {

    /** Whether a signing succeeded or failed. */
    sealed interface Outcome {
        /** The signing succeeded. */
        data object Completed : Outcome

        /** The signing failed; [reason] explains why, when known. */
        data class Failed(val reason: String?) : Outcome
    }

    /**
     * Information about a single signed document.
     *
     * @property label the name used to identify the document during signing.
     * @property dtbsr the document's digest — the short fingerprint that was actually signed — as a
     *   Base64 string, if known.
     * @property sizeBytes the size of the signed document in bytes, if it could be determined.
     */
    data class SignedDocument(
        val label: String,
        val dtbsr: String?,
        val sizeBytes: Long? = null,
    )

    /**
     * A name together with the language it is written in — for example "ACME Trust Services" in English.
     *
     * @property languageTag the language of the name, for example "en" for English.
     * @property name the name to show.
     */
    data class LocalizedName(val languageTag: String, val name: String)
}