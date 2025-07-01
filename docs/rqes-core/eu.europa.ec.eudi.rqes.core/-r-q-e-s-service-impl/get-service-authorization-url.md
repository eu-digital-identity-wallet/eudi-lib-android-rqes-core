//[rqes-core](../../../index.md)/[eu.europa.ec.eudi.rqes.core](../index.md)/[RQESServiceImpl](index.md)/[getServiceAuthorizationUrl](get-service-authorization-url.md)

# getServiceAuthorizationUrl

[androidJvm]\
open suspend override fun [getServiceAuthorizationUrl](get-service-authorization-url.md)(): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-result/index.html)&lt;HttpsUrl&gt;

Generates a service authorization URL for initiating the user authorization flow.

This method is the first step in the authorization workflow. It:

1. 
   Generates a random server state value for security validation
2. 
   Prepares a service authorization request through the CSC client
3. 
   Stores the prepared request for later use in [authorizeService](authorize-service.md)

The returned URL should be presented to the user (typically by redirecting them to it), allowing them to authorize the application to access the remote signature service. After authorization, the user will be redirected back with an authorization code that should be passed to [authorizeService](authorize-service.md).

#### Return

A [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-result/index.html) containing the HttpsUrl for the authorization endpoint if successful,     or an error if the request preparation failed
