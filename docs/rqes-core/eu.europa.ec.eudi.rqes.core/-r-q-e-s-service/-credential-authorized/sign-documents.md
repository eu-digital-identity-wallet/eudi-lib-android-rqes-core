//[rqes-core](../../../../index.md)/[eu.europa.ec.eudi.rqes.core](../../index.md)/[RQESService](../index.md)/[CredentialAuthorized](index.md)/[signDocuments](sign-documents.md)

# signDocuments

[androidJvm]\
abstract suspend fun [signDocuments](sign-documents.md)(): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-result/index.html)&lt;[SignedDocuments](../../-signed-documents/index.md)&gt;

Signs the previously specified documents using the authorized credential.

This method performs the actual document signing operation with the credential that was authorized through the [RQESService.Authorized.authorizeCredential](../-authorized/authorize-credential.md) process. The documents to be signed are those that were provided to [RQESService.Authorized.getCredentialAuthorizationUrl](../-authorized/get-credential-authorization-url.md).

#### Return

A [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-result/index.html) containing the [SignedDocuments](../../-signed-documents/index.md) if signing is successful,     or an error if the signing operation failed.
