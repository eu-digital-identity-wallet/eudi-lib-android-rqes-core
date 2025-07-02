//[rqes-core](../../../../index.md)/[eu.europa.ec.eudi.rqes.core](../../index.md)/[RQESServiceImpl](../index.md)/[AuthorizedImpl](index.md)/[getCredentialAuthorizationUrl](get-credential-authorization-url.md)

# getCredentialAuthorizationUrl

[androidJvm]\
open suspend override fun [getCredentialAuthorizationUrl](get-credential-authorization-url.md)(credential: CredentialInfo, documents: [UnsignedDocuments](../../-unsigned-documents/index.md), signingAlgorithmOID: SigningAlgorithmOID?): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-result/index.html)&lt;HttpsUrl&gt;

Generates a credential authorization URL for document signing.

This method prepares the documents for signing with the specified credential and creates an authorization URL for the user to approve the signing operation. It:

1. 
   Selects an appropriate signing algorithm from those supported by the credential
2. 
   Prepares the documents for signing and converts them to the appropriate format
3. 
   Calculates cryptographic hashes of the documents using the configured algorithm
4. 
   Prepares a credential authorization request with the document hashes
5. 
   Stores the prepared request and document information for later use

The returned URL should be presented to the user (typically by redirecting them to it), allowing them to authorize the use of their specific credential for signing. After authorization, the user will be redirected back with an authorization code that should be passed to [authorizeCredential](authorize-credential.md).

#### Return

A [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-result/index.html) containing the HttpsUrl for credential authorization if successful,     or an error if preparation failed

#### Parameters

androidJvm

| | |
|---|---|
| credential | The credential to be used for signing the documents |
| documents | The collection of unsigned documents to be signed |
| signingAlgorithmOID | Optional algorithm OID for signing the documents. If null, the first supported algorithm of the credential will be used. |
