//[rqes-core](../../../index.md)/[eu.europa.ec.eudi.rqes.core](../index.md)/[RQESServiceImpl](index.md)/[authorizeService](authorize-service.md)

# authorizeService

[androidJvm]\
open suspend override fun [authorizeService](authorize-service.md)(authorizationCode: AuthorizationCode): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-result/index.html)&lt;[RQESService.Authorized](../-r-q-e-s-service/-authorized/index.md)&gt;

Completes the service authorization process by exchanging the authorization code for service access.

This method requires that [getServiceAuthorizationUrl](get-service-authorization-url.md) has been called previously to initialize the server state and prepare the authorization request. It exchanges the authorization code received after user authentication for service access credentials.

Implementation workflow:

1. 
   Verifies that the server state has been initialized through [getServiceAuthorizationUrl](get-service-authorization-url.md)
2. 
   Uses the prepared authorization request to exchange the code for access
3. 
   Creates an [AuthorizedImpl](-authorized-impl/index.md) instance with the authorized service access

#### Return

A [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-result/index.html) containing the [RQESService.Authorized](../-r-q-e-s-service/-authorized/index.md) implementation if successful,     or an error if the authorization process failed

#### Parameters

androidJvm

| | |
|---|---|
| authorizationCode | The authorization code received after user completes the authorization flow |

#### Throws

| | |
|---|---|
| [IllegalStateException](https://developer.android.com/reference/kotlin/java/lang/IllegalStateException.html) | If [getServiceAuthorizationUrl](get-service-authorization-url.md) was not called before this method |
