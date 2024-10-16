//[rqes-core](../../../../index.md)/[eu.europa.ec.eudi.rqes.core](../../index.md)/[RQESServiceImpl](../index.md)/[CredentialAuthorizedImpl](index.md)/[signDocuments](sign-documents.md)

# signDocuments

[androidJvm]\
open suspend override fun [signDocuments](sign-documents.md)(algorithmOID: AlgorithmOID?, certificates: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[X509Certificate](https://developer.android.com/reference/kotlin/java/security/cert/X509Certificate.html)&gt;?): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Document](../../-document/index.md)&gt;&gt;

Sign the documents. This method is used to sign the documents. The documents are the list of documents that were passed to the [RQESService.Authorized.getCredentialAuthorizationUrl](../../-r-q-e-s-service/-authorized/get-credential-authorization-url.md) method. The documents are signed using the authorized credential.

#### Return

The list of signed documents as a [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html) of [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html) of [Document](../../-document/index.md). The signed documents are the documents that were signed.

#### Parameters

androidJvm

| | |
|---|---|
| algorithmOID | The algorithm OID. Implementations should use the default algorithm if this parameter is null. |
| certificates | The list of certificates. Implementations should use the default certificates if this parameter is null. |
