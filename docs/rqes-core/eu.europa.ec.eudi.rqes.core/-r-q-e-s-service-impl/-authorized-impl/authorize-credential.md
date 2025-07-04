//[rqes-core](../../../../index.md)/[eu.europa.ec.eudi.rqes.core](../../index.md)/[RQESServiceImpl](../index.md)/[AuthorizedImpl](index.md)/[authorizeCredential](authorize-credential.md)

# authorizeCredential

[androidJvm]\
open suspend override fun [authorizeCredential](authorize-credential.md)(authorizationCode: AuthorizationCode): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-result/index.html)&lt;[RQESService.CredentialAuthorized](../../-r-q-e-s-service/-credential-authorized/index.md)&gt;

Completes the credential authorization process for document signing.

This method requires that [getCredentialAuthorizationUrl](get-credential-authorization-url.md) has been called previously to initialize the document hashes and prepare the credential authorization request. It exchanges the authorization code received after user authentication for credential access credentials.

Implementation workflow:

1. 
   Verifies that all required state has been initialized through [getCredentialAuthorizationUrl](get-credential-authorization-url.md)
2. 
   Uses the prepared request to exchange the code for credential access
3. 
   Creates a [CredentialAuthorizedImpl](../-credential-authorized-impl/index.md) instance with the authorized credential and document info

This is the final authorization step before actual document signing can occur.

#### Return

A [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-result/index.html) containing a [RQESService.CredentialAuthorized](../../-r-q-e-s-service/-credential-authorized/index.md) implementation if successful,     or an error if the authorization process failed

#### Parameters

androidJvm

| | |
|---|---|
| authorizationCode | The authorization code received after user completes the credential authorization flow |

#### Throws

| | |
|---|---|
| [IllegalStateException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-illegal-state-exception/index.html) | If [getCredentialAuthorizationUrl](get-credential-authorization-url.md) was not called before this method     or if required state is missing |
