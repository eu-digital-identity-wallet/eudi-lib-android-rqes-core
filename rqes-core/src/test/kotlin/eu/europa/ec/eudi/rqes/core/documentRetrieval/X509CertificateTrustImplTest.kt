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
import org.bouncycastle.asn1.x500.X500Name
import org.bouncycastle.asn1.x509.BasicConstraints
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo
import org.bouncycastle.cert.X509v3CertificateBuilder
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter
import org.bouncycastle.jce.provider.BouncyCastleProvider
import org.bouncycastle.operator.ContentSigner
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder
import org.junit.Assert.assertThrows
import org.junit.Test
import java.math.BigInteger
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.PrivateKey
import java.security.SecureRandom
import java.security.Security
import java.security.cert.X509Certificate
import java.util.Date
import kotlin.test.BeforeTest
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class X509CertificateTrustImplTest {

    init {
        Security.addProvider(BouncyCastleProvider())
    }

    @BeforeTest
    fun setup() {
        Security.addProvider(BouncyCastleProvider())
        caKeyPair = generateKeyPair()
        caCertificate = generateCertificate(
            subject = "CN=Test CA",
            keyPair = caKeyPair,
            isCA = true,
            signerCert = null,
            signerKey = null
        )
    }

    private lateinit var caKeyPair: KeyPair
    private lateinit var caCertificate: X509Certificate


    // Helper functions for KeyPair and Certificate Generation
    private fun generateKeyPair(): KeyPair {
        val keyPairGenerator = KeyPairGenerator.getInstance("RSA", "BC")
        keyPairGenerator.initialize(2048, SecureRandom())
        return keyPairGenerator.generateKeyPair()
    }


    private fun generateCertificate(
        subject: String,
        keyPair: KeyPair,
        isCA: Boolean,
        signerCert: X509Certificate?,
        signerKey: PrivateKey?
    ): X509Certificate {

        val issuer = if (signerCert != null && signerKey != null) {
            X500Name(signerCert.subjectDN.name)
        } else {
            X500Name(subject)  // Self-signed if no signer provided
        }

        val serialNumber = BigInteger(128, SecureRandom())
        val now = Date()
        val validityStart = Date(now.time - 24 * 60 * 60 * 1000)  // Yesterday
        val validityEnd = Date(now.time + 365 * 24 * 60 * 60 * 1000) // 1 year from now
        val subjectName = X500Name(subject)
        val subjectPublicKeyInfo = SubjectPublicKeyInfo.getInstance(keyPair.public.encoded)

        val certificateBuilder = X509v3CertificateBuilder(
            issuer, serialNumber, validityStart, validityEnd, subjectName, subjectPublicKeyInfo
        )
        if (isCA) {
            certificateBuilder.addExtension(
                org.bouncycastle.asn1.ASN1ObjectIdentifier("2.5.29.19"),
                true,
                BasicConstraints(true)
            )
        } else {
            certificateBuilder.addExtension(
                org.bouncycastle.asn1.ASN1ObjectIdentifier("2.5.29.19"),
                true,
                BasicConstraints(false)
            )
        }
        val signer: ContentSigner = if (signerCert != null && signerKey != null) {
            JcaContentSignerBuilder("SHA256withRSA").build(signerKey)
        } else {
            JcaContentSignerBuilder("SHA256withRSA").build(keyPair.private)  // Self-sign if no signer provided
        }

        val holder = certificateBuilder.build(signer)
        return JcaX509CertificateConverter().getCertificate(holder)
    }

    // Helper function to create the implementation
    private fun createTrust(
        trustedCerts: List<X509Certificate>,
        logException: ((Throwable) -> Unit)? = { it.printStackTrace(System.err) }
    ): X509CertificateTrust =
        X509CertificateTrust(trustedCerts, logException)


    @Test
    fun `create X509CertificateTrustImpl with X509CertificateTrust function throws when trustedCertificates is empty`() {
        assertThrows(IllegalArgumentException::class.java) {
            createTrust(emptyList())
        }
    }

    @Test
    fun `isTrusted returns false if path does not chain with any of the trust anchors`() {
        val trustedCert = generateCertificate(
            subject = "CN=Test Trusted Cert",
            keyPair = generateKeyPair(),
            isCA = false,
            signerCert = caCertificate,
            signerKey = caKeyPair.private,
        )
        val trust = createTrust(listOf(trustedCert))
        assertFalse(trust.isTrusted(listOf(trustedCert)))
    }

    @Test
    fun `isTrusted returns false for an invalid chain`() {
        val trustedCert = generateCertificate(
            subject = "CN=Trusted Cert",
            keyPair = generateKeyPair(),
            isCA = false,
            signerCert = null,
            signerKey = null
        )  // self-signed
        val untrustedCert = generateCertificate(
            subject = "CN=Untrusted Cert",
            keyPair = generateKeyPair(),
            isCA = false,
            signerCert = null,
            signerKey = null
        ) // self-signed
        val trust = createTrust(listOf(trustedCert))
        assertFalse(trust.isTrusted(listOf(untrustedCert)))
    }

    @Test
    fun `isTrusted returns false for an empty chain`() {
        val trust = createTrust(listOf(caCertificate))
        assertFalse(trust.isTrusted(emptyList()))
    }

    @Test
    fun `isTrusted returns true for a self-signed certificate in the trust list`() {
        val selfSignedCert = generateCertificate(
            subject = "CN=Self-Signed",
            keyPair = generateKeyPair(),
            isCA = false,
            signerCert = null,
            signerKey = null
        )
        val trust = createTrust(listOf(selfSignedCert))
        assertTrue(trust.isTrusted(listOf(selfSignedCert)))
    }

    @Test
    fun `isTrusted returns false if the chain is not trusted`() {
        val untrustedCaKeyPair = generateKeyPair()
        val untrustedCaCert = generateCertificate(
            subject = "CN=Untrusted CA",
            keyPair = untrustedCaKeyPair,
            isCA = true,
            signerCert = null,
            signerKey = null
        )

        val leafKeyPair = generateKeyPair()
        val leafCert = generateCertificate(
            subject = "CN=Untrusted Leaf",
            keyPair = leafKeyPair,
            isCA = false,
            signerCert = untrustedCaCert,
            signerKey = untrustedCaKeyPair.private
        )

        val trustedCert = generateCertificate(
            subject = "CN=Trusted Cert",
            keyPair = generateKeyPair(),
            isCA = false,
            signerCert = null,
            signerKey = null
        ) // Self-signed
        val trust = createTrust(listOf(trustedCert))
        assertFalse(trust.isTrusted(listOf(leafCert, untrustedCaCert)))
    }

    @Test
    fun `isTrusted returns true for a chain with intermediate certificates when properly configured`() {
        val intermediateKeyPair = generateKeyPair()
        val intermediateCert = generateCertificate(
            subject = "CN=Intermediate Cert",
            keyPair = intermediateKeyPair,
            isCA = true,
            signerCert = caCertificate,
            signerKey = caKeyPair.private
        )
        val leafKeyPair = generateKeyPair()
        val leafCert = generateCertificate(
            subject = "CN=Leaf Cert",
            keyPair = leafKeyPair,
            isCA = false,
            signerCert = intermediateCert,
            signerKey = intermediateKeyPair.private
        )

        val chainWithIntermediate = listOf(leafCert, intermediateCert, caCertificate)
        val trust = createTrust(listOf(caCertificate))
        // To properly pass, implement certificate signing and verification.
        // Currently this will fail because the certs are not a real chain.
        assertTrue(trust.isTrusted(chainWithIntermediate))
    }

}