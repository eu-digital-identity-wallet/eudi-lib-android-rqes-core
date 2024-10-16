//[rqes-core](../../../index.md)/[eu.europa.ec.eudi.rqes.core](../index.md)/[DocumentSignatureEmbedder](index.md)

# DocumentSignatureEmbedder

fun interface [DocumentSignatureEmbedder](index.md)

Embeds a signature into a document.

#### Inheritors

| |
|---|
| [SCAService](../-s-c-a-service/index.md) |

## Functions

| Name | Summary |
|---|---|
| [embedSignature](embed-signature.md) | [androidJvm]<br>abstract fun [embedSignature](embed-signature.md)(document: [Document](../-document/index.md), signature: Signature, certificates: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[X509Certificate](https://developer.android.com/reference/kotlin/java/security/cert/X509Certificate.html)&gt;?): [Document](../-document/index.md)<br>Embeds a signature into a document. |
