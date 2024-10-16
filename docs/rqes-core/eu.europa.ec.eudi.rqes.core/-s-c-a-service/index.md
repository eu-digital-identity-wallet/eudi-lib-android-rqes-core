//[rqes-core](../../../index.md)/[eu.europa.ec.eudi.rqes.core](../index.md)/[SCAService](index.md)

# SCAService

interface [SCAService](index.md) : [DocumentHashCalculator](../-document-hash-calculator/index.md), [DocumentSignatureEmbedder](../-document-signature-embedder/index.md)

Interface for the Signature Creation Application service.

This service provides the functionality to calculate the hash of a document and to embed a signature into a document.

#### See also

| |
|---|
| [DocumentHashCalculator](../-document-hash-calculator/index.md) |
| [DocumentSignatureEmbedder](../-document-signature-embedder/index.md) |

#### Inheritors

| |
|---|
| [SCAServiceImpl](../-s-c-a-service-impl/index.md) |

## Functions

| Name | Summary |
|---|---|
| [calculateHash](../-document-hash-calculator/calculate-hash.md) | [androidJvm]<br>abstract fun [calculateHash](../-document-hash-calculator/calculate-hash.md)(document: [Document](../-document/index.md), certificates: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[X509Certificate](https://developer.android.com/reference/kotlin/java/security/cert/X509Certificate.html)&gt;?): Digest<br>Calculates the hash of a document. |
| [embedSignature](../-document-signature-embedder/embed-signature.md) | [androidJvm]<br>abstract fun [embedSignature](../-document-signature-embedder/embed-signature.md)(document: [Document](../-document/index.md), signature: Signature, certificates: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[X509Certificate](https://developer.android.com/reference/kotlin/java/security/cert/X509Certificate.html)&gt;?): [Document](../-document/index.md)<br>Embeds a signature into a document. |
