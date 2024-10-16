//[rqes-core](../../../index.md)/[eu.europa.ec.eudi.rqes.core](../index.md)/[SCAServiceImpl](index.md)

# SCAServiceImpl

[androidJvm]\
class [SCAServiceImpl](index.md) : [SCAService](../-s-c-a-service/index.md)

Implementation of the [SCAService](../-s-c-a-service/index.md) interface.

## Constructors

| | |
|---|---|
| [SCAServiceImpl](-s-c-a-service-impl.md) | [androidJvm]<br>constructor() |

## Functions

| Name | Summary |
|---|---|
| [calculateHash](calculate-hash.md) | [androidJvm]<br>open override fun [calculateHash](calculate-hash.md)(document: [Document](../-document/index.md), certificates: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[X509Certificate](https://developer.android.com/reference/kotlin/java/security/cert/X509Certificate.html)&gt;?): Digest<br>Calculates the hash of a document. |
| [embedSignature](embed-signature.md) | [androidJvm]<br>open override fun [embedSignature](embed-signature.md)(document: [Document](../-document/index.md), signature: Signature, certificates: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[X509Certificate](https://developer.android.com/reference/kotlin/java/security/cert/X509Certificate.html)&gt;?): [Document](../-document/index.md)<br>Embeds a signature into a document. |
