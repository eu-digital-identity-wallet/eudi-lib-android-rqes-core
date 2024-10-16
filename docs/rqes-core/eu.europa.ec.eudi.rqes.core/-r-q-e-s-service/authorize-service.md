//[rqes-core](../../../index.md)/[eu.europa.ec.eudi.rqes.core](../index.md)/[RQESService](index.md)/[authorizeService](authorize-service.md)

# authorizeService

[androidJvm]\
abstract suspend fun [authorizeService](authorize-service.md)(authorizationCode: AuthorizationCode): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;[RQESService.Authorized](-authorized/index.md)&gt;

Authorize with the service. This method is used to authorize the service to access the user's credentials. Once the authorizationCode is obtained using the service authorization URL, it can be used to authorize the service.

#### Return

The authorized service as a [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html) of [Authorized](-authorized/index.md). [Authorized](-authorized/index.md) is the interface to interact with the authorized service.

#### Parameters

androidJvm

| | |
|---|---|
| authorizationCode | The authorization code. |
