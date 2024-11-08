//[rqes-core](../../../../index.md)/[eu.europa.ec.eudi.rqes.core](../../index.md)/[RQESServiceImpl](../index.md)/[AuthorizedImpl](index.md)/[getCredentialAuthorizationUrl](get-credential-authorization-url.md)

# getCredentialAuthorizationUrl

[androidJvm]\
open suspend override fun [getCredentialAuthorizationUrl](get-credential-authorization-url.md)(credential: CredentialInfo, documents: [UnsignedDocuments](../../-unsigned-documents/index.md)): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;HttpsUrl&gt;

Get the credential authorization URL. This method is used to get the credential authorization URL.

The credential authorization URL is used to authorize the credential that will be used to sign the documents.

#### Return

The credential authorization URL as a [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html) of HttpsUrl.

#### Parameters

androidJvm

| | |
|---|---|
| credential | The credential info. |
| documents | The list of documents to be signed. Implementations should use the default hash algorithm if this parameter is null. Implementations should use the default certificates if this parameter is null. |
