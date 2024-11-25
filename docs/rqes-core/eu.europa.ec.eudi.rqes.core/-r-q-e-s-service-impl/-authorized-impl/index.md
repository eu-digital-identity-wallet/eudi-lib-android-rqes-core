//[rqes-core](../../../../index.md)/[eu.europa.ec.eudi.rqes.core](../../index.md)/[RQESServiceImpl](../index.md)/[AuthorizedImpl](index.md)

# AuthorizedImpl

class [AuthorizedImpl](index.md)(serverState: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), client: CSCClient, serviceAccessAuthorized: ServiceAccessAuthorized, val hashAlgorithm: HashAlgorithmOID) : [RQESService.Authorized](../../-r-q-e-s-service/-authorized/index.md)

The authorized service implementation.

#### Parameters

androidJvm

| | |
|---|---|
| serverState | The server state. |
| client | The client. |
| serviceAccessAuthorized | The service access authorized. |
| hashAlgorithm | The algorithm OID, for hashing the documents. |

## Constructors

| | |
|---|---|
| [AuthorizedImpl](-authorized-impl.md) | [androidJvm]<br>constructor(serverState: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), client: CSCClient, serviceAccessAuthorized: ServiceAccessAuthorized, hashAlgorithm: HashAlgorithmOID)<br>Creates an authorized service implementation. |

## Properties

| Name | Summary |
|---|---|
| [hashAlgorithm](hash-algorithm.md) | [androidJvm]<br>val [hashAlgorithm](hash-algorithm.md): HashAlgorithmOID<br>The algorithm OID, for hashing the documents. |

## Functions

| Name | Summary |
|---|---|
| [authorizeCredential](authorize-credential.md) | [androidJvm]<br>open suspend override fun [authorizeCredential](authorize-credential.md)(authorizationCode: AuthorizationCode): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;[RQESService.CredentialAuthorized](../../-r-q-e-s-service/-credential-authorized/index.md)&gt;<br>Authorize the credential. This method is used to authorize the credential that will be used to sign the documents. Once the authorizationCode is obtained using the credential authorization URL, it can be used to authorize the credential. The authorized credential can be used to sign the documents. |
| [getCredentialAuthorizationUrl](get-credential-authorization-url.md) | [androidJvm]<br>open suspend override fun [getCredentialAuthorizationUrl](get-credential-authorization-url.md)(credential: CredentialInfo, documents: [UnsignedDocuments](../../-unsigned-documents/index.md), signingAlgorithmOID: SigningAlgorithmOID?): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;HttpsUrl&gt;<br>Get the credential authorization URL. This method is used to get the credential authorization URL. |
| [listCredentials](list-credentials.md) | [androidJvm]<br>open suspend override fun [listCredentials](list-credentials.md)(request: CredentialsListRequest?): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;CredentialInfo&gt;&gt;<br>List the credentials. This method is used to list the credentials. The credentials are the user's credentials that can be used to sign the documents. |
| [signDocuments](../../sign-documents.md) | [androidJvm]<br>suspend fun [RQESService.Authorized](../../-r-q-e-s-service/-authorized/index.md).[signDocuments](../../sign-documents.md)(authorizationCode: AuthorizationCode): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;[SignedDocuments](../../-signed-documents/index.md)&gt;<br>Sign the documents with the given authorization code. |
