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

import eu.europa.ec.eudi.documentretrieval.X509CertificateTrust
import java.security.cert.CertPathValidator
import java.security.cert.CertStore
import java.security.cert.CertificateFactory
import java.security.cert.CollectionCertStoreParameters
import java.security.cert.PKIXParameters
import java.security.cert.TrustAnchor
import java.security.cert.X509Certificate
import java.util.Date

fun X509CertificateTrust(
    trustedCertificates: List<X509Certificate>,
    logException: ((Throwable) -> Unit)? = null
): X509CertificateTrust {
    require(trustedCertificates.isNotEmpty()) { "At least one trusted certificate is required" }
    return X509CertificateTrustImpl(trustedCertificates, logException)
}

class X509CertificateTrustImpl(
    val trustedCertificates: List<X509Certificate>,
    private val logException: ((Throwable) -> Unit)? = null
) : X509CertificateTrust {

    private val trustAnchors: Set<TrustAnchor>
        get() = trustedCertificates.map { TrustAnchor(it, null) }.toSet()

    private val store by lazy {
        CertStore.getInstance(
            "Collection",
            CollectionCertStoreParameters(trustedCertificates)
        )
    }

    private val pathParameters by lazy {
        PKIXParameters(trustAnchors).apply {
            isRevocationEnabled = false
            addCertStore(store)
            date = Date()
        }
    }

    private val factory by lazy {
        CertificateFactory.getInstance("X.509")
    }

    private val validator by lazy {
        CertPathValidator.getInstance("PKIX")
    }

    override fun isTrusted(chain: List<X509Certificate>): Boolean {
        return chain.isNotEmpty() && validateChain(chain)
    }

    private fun validateChain(chain: List<X509Certificate>): Boolean {
        try {
            val path = factory.generateCertPath(chain)
            validator.validate(path, pathParameters)
            return true
        } catch (exception: Throwable) {
            logException?.invoke(exception)
            return false
        }
    }
}