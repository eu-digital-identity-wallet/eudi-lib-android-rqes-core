//[rqes-core](../../../index.md)/[eu.europa.ec.eudi.rqes.core](../index.md)/[RQESService](index.md)/[getServiceAuthorizationUrl](get-service-authorization-url.md)

# getServiceAuthorizationUrl

[androidJvm]\
abstract suspend fun [getServiceAuthorizationUrl](get-service-authorization-url.md)(): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-result/index.html)&lt;HttpsUrl&gt;

Retrieves the service authorization URL.

This URL is used to initiate the authorization flow, allowing the service to access the user's credentials. The user should be redirected to this URL to complete the authorization process.

#### Return

A [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-result/index.html) containing an HttpsUrl for authorization if successful,     or an error if the operation failed.
