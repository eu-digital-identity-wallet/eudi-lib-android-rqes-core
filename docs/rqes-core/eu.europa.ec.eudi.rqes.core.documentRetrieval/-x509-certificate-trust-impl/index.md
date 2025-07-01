//[rqes-core](../../../index.md)/[eu.europa.ec.eudi.rqes.core.documentRetrieval](../index.md)/[X509CertificateTrustImpl](index.md)

# X509CertificateTrustImpl

[androidJvm]\
class [X509CertificateTrustImpl](index.md)(val trustedCertificates: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[X509Certificate](https://developer.android.com/reference/kotlin/java/security/cert/X509Certificate.html)&gt;, logException: ([Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-throwable/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-unit/index.html)? = null) : X509CertificateTrust

## Constructors

| | |
|---|---|
| [X509CertificateTrustImpl](-x509-certificate-trust-impl.md) | [androidJvm]<br>constructor(trustedCertificates: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[X509Certificate](https://developer.android.com/reference/kotlin/java/security/cert/X509Certificate.html)&gt;, logException: ([Throwable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-throwable/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-unit/index.html)? = null) |

## Properties

| Name | Summary |
|---|---|
| [trustedCertificates](trusted-certificates.md) | [androidJvm]<br>val [trustedCertificates](trusted-certificates.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[X509Certificate](https://developer.android.com/reference/kotlin/java/security/cert/X509Certificate.html)&gt; |

## Functions

| Name | Summary |
|---|---|
| [isTrusted](is-trusted.md) | [androidJvm]<br>open override fun [isTrusted](is-trusted.md)(chain: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[X509Certificate](https://developer.android.com/reference/kotlin/java/security/cert/X509Certificate.html)&gt;): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html) |
