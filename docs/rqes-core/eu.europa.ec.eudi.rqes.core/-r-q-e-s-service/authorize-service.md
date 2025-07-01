//[rqes-core](../../../index.md)/[eu.europa.ec.eudi.rqes.core](../index.md)/[RQESService](index.md)/[authorizeService](authorize-service.md)

# authorizeService

[androidJvm]\
abstract suspend fun [authorizeService](authorize-service.md)(authorizationCode: AuthorizationCode): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-result/index.html)&lt;[RQESService.Authorized](-authorized/index.md)&gt;

Completes the authorization process with the service.

After the user completes the authorization flow at the service authorization URL, an authorization code is provided. This method exchanges that code for service access and returns an authorized service instance.

#### Return

A [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-result/index.html) containing an [Authorized](-authorized/index.md) service instance if successful,     or an error if the authorization failed.

#### Parameters

androidJvm

| | |
|---|---|
| authorizationCode | The authorization code received after user authorization. |
