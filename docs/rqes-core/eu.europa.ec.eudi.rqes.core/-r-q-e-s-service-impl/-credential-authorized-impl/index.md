//[rqes-core](../../../../index.md)/[eu.europa.ec.eudi.rqes.core](../../index.md)/[RQESServiceImpl](../index.md)/[CredentialAuthorizedImpl](index.md)

# CredentialAuthorizedImpl

class [CredentialAuthorizedImpl](index.md)(client: CSCClient, documentsToSign: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;DocumentToSign&gt;, documentDigestList: DocumentDigestList, credentialAuthorized: CredentialAuthorized, val signingAlgorithm: SigningAlgorithmOID) : [RQESService.CredentialAuthorized](../../-r-q-e-s-service/-credential-authorized/index.md)

The credential authorized implementation.

#### Parameters

androidJvm

| | |
|---|---|
| client | The client. |
| documentsToSign | The documents to sign. |
| documentDigestList | The document digest list. |
| credentialAuthorized | The credential authorized. |
| signingAlgorithm | The algorithm OID, for signing the documents. |

## Constructors

| | |
|---|---|
| [CredentialAuthorizedImpl](-credential-authorized-impl.md) | [androidJvm]<br>constructor(client: CSCClient, documentsToSign: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;DocumentToSign&gt;, documentDigestList: DocumentDigestList, credentialAuthorized: CredentialAuthorized, signingAlgorithm: SigningAlgorithmOID)<br>Creates a credential authorized implementation. |

## Properties

| Name | Summary |
|---|---|
| [signingAlgorithm](signing-algorithm.md) | [androidJvm]<br>val [signingAlgorithm](signing-algorithm.md): SigningAlgorithmOID<br>The algorithm OID, for signing the documents. |

## Functions

| Name | Summary |
|---|---|
| [signDocuments](sign-documents.md) | [androidJvm]<br>open suspend override fun [signDocuments](sign-documents.md)(): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;[SignedDocuments](../../-signed-documents/index.md)&gt;<br>Sign the documents. This method is used to sign the documents. The documents are the list of documents that were passed to the [RQESService.Authorized.getCredentialAuthorizationUrl](../../-r-q-e-s-service/-authorized/get-credential-authorization-url.md) method. The documents are signed using the authorized credential. |
