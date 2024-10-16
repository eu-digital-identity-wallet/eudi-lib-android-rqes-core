//[rqes-core](../../../index.md)/[eu.europa.ec.eudi.rqes.core](../index.md)/[SCAServiceImpl](index.md)/[embedSignature](embed-signature.md)

# embedSignature

[androidJvm]\
open override fun [embedSignature](embed-signature.md)(document: [Document](../-document/index.md), signature: Signature, certificates: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[X509Certificate](https://developer.android.com/reference/kotlin/java/security/cert/X509Certificate.html)&gt;?): [Document](../-document/index.md)

Embeds a signature into a document.

#### Return

the document with the embedded signature

#### Parameters

androidJvm

| | |
|---|---|
| document | the document to embed the signature into |
| signature | the signature to embed |
| certificates | the certificates to use when embedding the signature (optional) |
