//[rqes-core](../../../../index.md)/[eu.europa.ec.eudi.rqes.core](../../index.md)/[RQESService](../index.md)/[CredentialAuthorized](index.md)

# CredentialAuthorized

interface [CredentialAuthorized](index.md)

Interface for signing documents with an authorized credential.

This interface provides access to document signing operations after a specific credential has been authorized. It operates on the documents that were previously specified during the credential authorization process.

#### Inheritors

| |
|---|
| [CredentialAuthorizedImpl](../../-r-q-e-s-service-impl/-credential-authorized-impl/index.md) |

## Functions

| Name | Summary |
|---|---|
| [signDocuments](sign-documents.md) | [androidJvm]<br>abstract suspend fun [signDocuments](sign-documents.md)(): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-result/index.html)&lt;[SignedDocuments](../../-signed-documents/index.md)&gt;<br>Signs the previously specified documents using the authorized credential. |
