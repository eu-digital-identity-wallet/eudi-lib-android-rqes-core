//[rqes-core](../../../index.md)/[eu.europa.ec.eudi.rqes.core](../index.md)/[DocumentHashCalculator](index.md)/[calculateHash](calculate-hash.md)

# calculateHash

[androidJvm]\
abstract fun [calculateHash](calculate-hash.md)(document: [Document](../-document/index.md), certificates: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[X509Certificate](https://developer.android.com/reference/kotlin/java/security/cert/X509Certificate.html)&gt;?): Digest

Calculates the hash of a document.

#### Return

the hash of the document

#### Parameters

androidJvm

| | |
|---|---|
| document | the document to calculate the hash of |
| certificates | the certificates to use when calculating the hash (optional) |
