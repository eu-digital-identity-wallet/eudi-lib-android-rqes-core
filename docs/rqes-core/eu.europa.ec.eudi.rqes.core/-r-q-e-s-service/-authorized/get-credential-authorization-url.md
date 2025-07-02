//[rqes-core](../../../../index.md)/[eu.europa.ec.eudi.rqes.core](../../index.md)/[RQESService](../index.md)/[Authorized](index.md)/[getCredentialAuthorizationUrl](get-credential-authorization-url.md)

# getCredentialAuthorizationUrl

[androidJvm]\
abstract suspend fun [getCredentialAuthorizationUrl](get-credential-authorization-url.md)(credential: CredentialInfo, documents: [UnsignedDocuments](../../-unsigned-documents/index.md), signingAlgorithmOID: SigningAlgorithmOID? = null): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-result/index.html)&lt;HttpsUrl&gt;

Retrieves the credential authorization URL for document signing.

This URL is used to initiate the credential authorization flow, which allows the specified credential to be used for signing the provided documents.

#### Return

A [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-result/index.html) containing an HttpsUrl for credential authorization if successful,     or an error if the operation failed.

#### Parameters

androidJvm

| | |
|---|---|
| credential | The credential to be used for signing. |
| documents | The collection of unsigned documents to be signed. |
| signingAlgorithmOID | Optional algorithm OID for signing the documents. If null, the first supported algorithm of the credential should be used. |
