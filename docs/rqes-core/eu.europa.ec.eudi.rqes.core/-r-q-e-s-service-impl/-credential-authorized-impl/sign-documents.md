//[rqes-core](../../../../index.md)/[eu.europa.ec.eudi.rqes.core](../../index.md)/[RQESServiceImpl](../index.md)/[CredentialAuthorizedImpl](index.md)/[signDocuments](sign-documents.md)

# signDocuments

[androidJvm]\
open suspend override fun [signDocuments](sign-documents.md)(): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;[SignedDocuments](../../-signed-documents/index.md)&gt;

Sign the documents. This method is used to sign the documents. The documents are the list of documents that were passed to the [RQESService.Authorized.getCredentialAuthorizationUrl](../../-r-q-e-s-service/-authorized/get-credential-authorization-url.md) method. The documents are signed using the authorized credential.

#### Return

The list of signed documents as a [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html) of [SignedDocuments](../../-signed-documents/index.md). The signed documents are the documents that were signed.
