//[rqes-core](../../../../index.md)/[eu.europa.ec.eudi.rqes.core](../../index.md)/[RQESServiceImpl](../index.md)/[CredentialAuthorizedImpl](index.md)/[CredentialAuthorizedImpl](-credential-authorized-impl.md)

# CredentialAuthorizedImpl

[androidJvm]\
constructor(client: CSCClient, documentsToSign: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;DocumentToSign&gt;, documentDigestList: DocumentDigestList, credentialAuthorized: CredentialAuthorized, signingAlgorithm: SigningAlgorithmOID)

#### Parameters

androidJvm

| | |
|---|---|
| client | The CSC client for communicating with the RSSP. |
| documentsToSign | List of documents prepared for signing. |
| documentDigestList | Document digests prepared for the signing request. |
| credentialAuthorized | The authorized credential access for signing. |
| signingAlgorithm | The algorithm to be used for the signing operation. |
