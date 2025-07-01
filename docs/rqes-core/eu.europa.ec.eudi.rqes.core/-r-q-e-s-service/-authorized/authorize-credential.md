//[rqes-core](../../../../index.md)/[eu.europa.ec.eudi.rqes.core](../../index.md)/[RQESService](../index.md)/[Authorized](index.md)/[authorizeCredential](authorize-credential.md)

# authorizeCredential

[androidJvm]\
abstract suspend fun [authorizeCredential](authorize-credential.md)(authorizationCode: AuthorizationCode): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-result/index.html)&lt;[RQESService.CredentialAuthorized](../-credential-authorized/index.md)&gt;

Completes the credential authorization process.

After the user completes the credential authorization flow, an authorization code is provided. This method exchanges that code for credential access and returns an interface to sign the previously specified documents.

#### Return

A [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-result/index.html) containing a [CredentialAuthorized](../-credential-authorized/index.md) instance if successful,     or an error if the authorization failed.

#### Parameters

androidJvm

| | |
|---|---|
| authorizationCode | The authorization code received after user credential authorization. |
