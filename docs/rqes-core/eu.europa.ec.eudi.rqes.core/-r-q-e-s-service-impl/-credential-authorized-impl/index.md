//[rqes-core](../../../../index.md)/[eu.europa.ec.eudi.rqes.core](../../index.md)/[RQESServiceImpl](../index.md)/[CredentialAuthorizedImpl](index.md)

# CredentialAuthorizedImpl

class [CredentialAuthorizedImpl](index.md)(client: CSCClient, documentsToSign: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;DocumentToSign&gt;, documentDigestList: DocumentDigestList, credentialAuthorized: CredentialAuthorized, val signingAlgorithm: SigningAlgorithmOID) : [RQESService.CredentialAuthorized](../../-r-q-e-s-service/-credential-authorized/index.md)

Implementation of the [RQESService.CredentialAuthorized](../../-r-q-e-s-service/-credential-authorized/index.md) interface that performs document signing operations with an authorized credential.

This class holds all the information needed for signing the documents that were specified during the credential authorization process, including the document content, cryptographic digests, and the authorized credential for signing.

#### Parameters

androidJvm

| | |
|---|---|
| client | The CSC client for communicating with the RSSP. |
| documentsToSign | List of documents prepared for signing. |
| documentDigestList | Document digests prepared for the signing request. |
| credentialAuthorized | The authorized credential access for signing. |
| signingAlgorithm | The algorithm to be used for the signing operation. |

## Constructors

| | |
|---|---|
| [CredentialAuthorizedImpl](-credential-authorized-impl.md) | [androidJvm]<br>constructor(client: CSCClient, documentsToSign: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;DocumentToSign&gt;, documentDigestList: DocumentDigestList, credentialAuthorized: CredentialAuthorized, signingAlgorithm: SigningAlgorithmOID) |

## Properties

| Name | Summary |
|---|---|
| [signingAlgorithm](signing-algorithm.md) | [androidJvm]<br>val [signingAlgorithm](signing-algorithm.md): SigningAlgorithmOID<br>The algorithm to be used for the signing operation. |

## Functions

| Name | Summary |
|---|---|
| [signDocuments](sign-documents.md) | [androidJvm]<br>open suspend override fun [signDocuments](sign-documents.md)(): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-result/index.html)&lt;[SignedDocuments](../../-signed-documents/index.md)&gt;<br>Signs the previously specified documents using the authorized credential. |
