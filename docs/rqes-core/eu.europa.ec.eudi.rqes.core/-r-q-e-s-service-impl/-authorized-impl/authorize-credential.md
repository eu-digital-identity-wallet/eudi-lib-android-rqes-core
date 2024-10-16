//[rqes-core](../../../../index.md)/[eu.europa.ec.eudi.rqes.core](../../index.md)/[RQESServiceImpl](../index.md)/[AuthorizedImpl](index.md)/[authorizeCredential](authorize-credential.md)

# authorizeCredential

[androidJvm]\
open suspend override fun [authorizeCredential](authorize-credential.md)(authorizationCode: AuthorizationCode): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;[RQESService.CredentialAuthorized](../../-r-q-e-s-service/-credential-authorized/index.md)&gt;

Authorize the credential. This method is used to authorize the credential that will be used to sign the documents. Once the authorizationCode is obtained using the credential authorization URL, it can be used to authorize the credential. The authorized credential can be used to sign the documents.

#### Return

The authorized credential as a [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html) of CredentialAuthorized.

#### Parameters

androidJvm

| | |
|---|---|
| authorizationCode | The authorization code. |
