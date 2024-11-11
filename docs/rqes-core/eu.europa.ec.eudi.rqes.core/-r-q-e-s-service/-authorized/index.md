//[rqes-core](../../../../index.md)/[eu.europa.ec.eudi.rqes.core](../../index.md)/[RQESService](../index.md)/[Authorized](index.md)

# Authorized

interface [Authorized](index.md)

The authorized service interface. This interface provides the methods to interact with the authorized service. The authorized service is used to access the user's credentials and sign the documents.

#### Inheritors

| |
|---|
| [AuthorizedImpl](../../-r-q-e-s-service-impl/-authorized-impl/index.md) |

## Functions

| Name | Summary |
|---|---|
| [authorizeCredential](authorize-credential.md) | [androidJvm]<br>abstract suspend fun [authorizeCredential](authorize-credential.md)(authorizationCode: AuthorizationCode): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;[RQESService.CredentialAuthorized](../-credential-authorized/index.md)&gt;<br>Authorize the credential. This method is used to authorize the credential that will be used to sign the documents. Once the authorizationCode is obtained using the credential authorization URL, it can be used to authorize the credential. The authorized credential can be used to sign the documents. |
| [getCredentialAuthorizationUrl](get-credential-authorization-url.md) | [androidJvm]<br>abstract suspend fun [getCredentialAuthorizationUrl](get-credential-authorization-url.md)(credential: CredentialInfo, documents: [UnsignedDocuments](../../-unsigned-documents/index.md)): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;HttpsUrl&gt;<br>Get the credential authorization URL. This method is used to get the credential authorization URL. |
| [listCredentials](list-credentials.md) | [androidJvm]<br>abstract suspend fun [listCredentials](list-credentials.md)(request: CredentialsListRequest? = null): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;CredentialInfo&gt;&gt;<br>List the credentials. This method is used to list the credentials. The credentials are the user's credentials that can be used to sign the documents. |
| [signDocuments](../../sign-documents.md) | [androidJvm]<br>suspend fun [RQESService.Authorized](index.md).[signDocuments](../../sign-documents.md)(authorizationCode: AuthorizationCode): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;[SignedDocuments](../../-signed-documents/index.md)&gt;<br>Sign the documents with the given authorization code. |
