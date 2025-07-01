//[rqes-core](../../../../index.md)/[eu.europa.ec.eudi.rqes.core](../../index.md)/[RQESService](../index.md)/[Authorized](index.md)

# Authorized

interface [Authorized](index.md)

Interface for interacting with the RQES service after successful authorization.

This interface provides methods to access user credentials and initiate the document signing process after the service has been authorized.

#### Inheritors

| |
|---|
| [AuthorizedImpl](../../-r-q-e-s-service-impl/-authorized-impl/index.md) |

## Functions

| Name | Summary |
|---|---|
| [authorizeCredential](authorize-credential.md) | [androidJvm]<br>abstract suspend fun [authorizeCredential](authorize-credential.md)(authorizationCode: AuthorizationCode): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-result/index.html)&lt;[RQESService.CredentialAuthorized](../-credential-authorized/index.md)&gt;<br>Completes the credential authorization process. |
| [getCredentialAuthorizationUrl](get-credential-authorization-url.md) | [androidJvm]<br>abstract suspend fun [getCredentialAuthorizationUrl](get-credential-authorization-url.md)(credential: CredentialInfo, documents: [UnsignedDocuments](../../-unsigned-documents/index.md)): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-result/index.html)&lt;HttpsUrl&gt;<br>Retrieves the credential authorization URL for document signing. |
| [listCredentials](list-credentials.md) | [androidJvm]<br>abstract suspend fun [listCredentials](list-credentials.md)(request: CredentialsListRequest? = null): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-result/index.html)&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;CredentialInfo&gt;&gt;<br>Retrieves a list of available signing credentials. |
| [signDocuments](../../sign-documents.md) | [androidJvm]<br>suspend fun [RQESService.Authorized](index.md).[signDocuments](../../sign-documents.md)(authorizationCode: AuthorizationCode): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-result/index.html)&lt;[SignedDocuments](../../-signed-documents/index.md)&gt;<br>Sign the documents with the given authorization code. |
