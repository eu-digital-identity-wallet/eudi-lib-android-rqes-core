//[rqes-core](../../../../index.md)/[eu.europa.ec.eudi.rqes.core](../../index.md)/[RQESService](../index.md)/[Authorized](index.md)/[getCredentialAuthorizationUrl](get-credential-authorization-url.md)

# getCredentialAuthorizationUrl

[androidJvm]\
abstract suspend fun [getCredentialAuthorizationUrl](get-credential-authorization-url.md)(credential: CredentialInfo, documents: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Document](../../-document/index.md)&gt;, hashAlgorithmOID: HashAlgorithmOID? = null, certificates: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[X509Certificate](https://developer.android.com/reference/kotlin/java/security/cert/X509Certificate.html)&gt;? = null): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;HttpsUrl&gt;

Get the credential authorization URL. This method is used to get the credential authorization URL.

The credential authorization URL is used to authorize the credential that will be used to sign the documents.

#### Return

The credential authorization URL as a [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html) of HttpsUrl.

#### Parameters

androidJvm

| | |
|---|---|
| credential | The credential info. |
| documents | The list of documents to be signed. |
| hashAlgorithmOID | The hash algorithm OID. Implementations should use the default hash algorithm if this parameter is null. |
| certificates | The list of certificates. Implementations should use the default certificates if this parameter is null. |
