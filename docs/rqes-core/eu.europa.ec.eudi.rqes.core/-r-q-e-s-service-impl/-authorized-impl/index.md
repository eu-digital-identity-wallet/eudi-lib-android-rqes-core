//[rqes-core](../../../../index.md)/[eu.europa.ec.eudi.rqes.core](../../index.md)/[RQESServiceImpl](../index.md)/[AuthorizedImpl](index.md)

# AuthorizedImpl

[androidJvm]\
class [AuthorizedImpl](index.md)(serverState: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), client: CSCClient, serviceAccessAuthorized: ServiceAccessAuthorized, outputPathDir: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), val hashAlgorithm: HashAlgorithmOID) : [RQESService.Authorized](../../-r-q-e-s-service/-authorized/index.md)

Implementation of the [RQESService.Authorized](../../-r-q-e-s-service/-authorized/index.md) interface that provides access to user credentials and document signing operations after successful service authorization.

This class maintains state across the credential selection and document signing workflow, storing information about documents to be signed, credential authorization state, and cryptographic parameters needed for the signing operation.

## Constructors

| | |
|---|---|
| [AuthorizedImpl](-authorized-impl.md) | [androidJvm]<br>constructor(serverState: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), client: CSCClient, serviceAccessAuthorized: ServiceAccessAuthorized, outputPathDir: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), hashAlgorithm: HashAlgorithmOID) |

## Properties

| Name | Summary |
|---|---|
| [hashAlgorithm](hash-algorithm.md) | [androidJvm]<br>val [hashAlgorithm](hash-algorithm.md): HashAlgorithmOID<br>The algorithm used for creating document hashes. |

## Functions

| Name | Summary |
|---|---|
| [authorizeCredential](authorize-credential.md) | [androidJvm]<br>open suspend override fun [authorizeCredential](authorize-credential.md)(authorizationCode: AuthorizationCode): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-result/index.html)&lt;[RQESService.CredentialAuthorized](../../-r-q-e-s-service/-credential-authorized/index.md)&gt;<br>Completes the credential authorization process for document signing. |
| [getCredentialAuthorizationUrl](get-credential-authorization-url.md) | [androidJvm]<br>open suspend override fun [getCredentialAuthorizationUrl](get-credential-authorization-url.md)(credential: CredentialInfo, documents: [UnsignedDocuments](../../-unsigned-documents/index.md)): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-result/index.html)&lt;HttpsUrl&gt;<br>Generates a credential authorization URL for document signing. |
| [listCredentials](list-credentials.md) | [androidJvm]<br>open suspend override fun [listCredentials](list-credentials.md)(request: CredentialsListRequest?): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-result/index.html)&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;CredentialInfo&gt;&gt;<br>Retrieves the list of available credentials for the authorized user. |
| [signDocuments](../../sign-documents.md) | [androidJvm]<br>suspend fun [RQESService.Authorized](../../-r-q-e-s-service/-authorized/index.md).[signDocuments](../../sign-documents.md)(authorizationCode: AuthorizationCode): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-result/index.html)&lt;[SignedDocuments](../../-signed-documents/index.md)&gt;<br>Sign the documents with the given authorization code. |
