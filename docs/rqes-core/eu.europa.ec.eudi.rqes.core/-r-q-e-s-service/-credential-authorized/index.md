//[rqes-core](../../../../index.md)/[eu.europa.ec.eudi.rqes.core](../../index.md)/[RQESService](../index.md)/[CredentialAuthorized](index.md)

# CredentialAuthorized

interface [CredentialAuthorized](index.md)

The credential authorized interface. This interface provides the methods to interact with the authorized credential. The authorized credential is used to sign the documents.

The list of documents that will be signed using the authorized credential are the documents that were passed to the [RQESService.Authorized.getCredentialAuthorizationUrl](../-authorized/get-credential-authorization-url.md) method.

#### Inheritors

| |
|---|
| [CredentialAuthorizedImpl](../../-r-q-e-s-service-impl/-credential-authorized-impl/index.md) |

## Functions

| Name | Summary |
|---|---|
| [signDocuments](sign-documents.md) | [androidJvm]<br>abstract suspend fun [signDocuments](sign-documents.md)(): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;[SignedDocuments](../../-signed-documents/index.md)&gt;<br>Sign the documents. This method is used to sign the documents. The documents are the list of documents that were passed to the [RQESService.Authorized.getCredentialAuthorizationUrl](../-authorized/get-credential-authorization-url.md) method. The documents are signed using the authorized credential. |
