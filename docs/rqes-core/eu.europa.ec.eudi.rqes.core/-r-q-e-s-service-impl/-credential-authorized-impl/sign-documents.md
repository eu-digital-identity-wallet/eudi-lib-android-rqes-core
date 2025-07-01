//[rqes-core](../../../../index.md)/[eu.europa.ec.eudi.rqes.core](../../index.md)/[RQESServiceImpl](../index.md)/[CredentialAuthorizedImpl](index.md)/[signDocuments](sign-documents.md)

# signDocuments

[androidJvm]\
open suspend override fun [signDocuments](sign-documents.md)(): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-result/index.html)&lt;[SignedDocuments](../../-signed-documents/index.md)&gt;

Signs the previously specified documents using the authorized credential.

This method performs the actual document signing operation by:

1. 
   Using the authorized credential to sign the document hashes
2. 
   Creating the final signed documents with the obtained signatures
3. 
   Returning the collection of signed document files

The signing process adapts based on the credential's Signature Creation Assurance Level (SCAL):

- 
   For SCAL1 credentials, the document hashes are sent with the signing request
- 
   For SCAL2 credentials, the hashes were previously uploaded during authorization

#### Return

A [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-result/index.html) containing [SignedDocuments](../../-signed-documents/index.md) with the paths to the signed files if successful,     or an error if the signing operation failed
