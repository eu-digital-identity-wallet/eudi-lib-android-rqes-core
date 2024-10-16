//[rqes-core](../../../../index.md)/[eu.europa.ec.eudi.rqes.core](../../index.md)/[RQESServiceImpl](../index.md)/[CredentialAuthorizedImpl](index.md)

# CredentialAuthorizedImpl

[androidJvm]\
class [CredentialAuthorizedImpl](index.md)(client: CSCClient, documents: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Document](../../-document/index.md)&gt;, credentialAuthorized: CredentialAuthorized.SCAL2, signatureEmbedder: [DocumentSignatureEmbedder](../../-document-signature-embedder/index.md)) : [RQESService.CredentialAuthorized](../../-r-q-e-s-service/-credential-authorized/index.md)

## Constructors

| | |
|---|---|
| [CredentialAuthorizedImpl](-credential-authorized-impl.md) | [androidJvm]<br>constructor(client: CSCClient, documents: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Document](../../-document/index.md)&gt;, credentialAuthorized: CredentialAuthorized.SCAL2, signatureEmbedder: [DocumentSignatureEmbedder](../../-document-signature-embedder/index.md)) |

## Functions

| Name | Summary |
|---|---|
| [signDocuments](sign-documents.md) | [androidJvm]<br>open suspend override fun [signDocuments](sign-documents.md)(algorithmOID: AlgorithmOID?, certificates: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[X509Certificate](https://developer.android.com/reference/kotlin/java/security/cert/X509Certificate.html)&gt;?): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Document](../../-document/index.md)&gt;&gt;<br>Sign the documents. This method is used to sign the documents. The documents are the list of documents that were passed to the [RQESService.Authorized.getCredentialAuthorizationUrl](../../-r-q-e-s-service/-authorized/get-credential-authorization-url.md) method. The documents are signed using the authorized credential. |
