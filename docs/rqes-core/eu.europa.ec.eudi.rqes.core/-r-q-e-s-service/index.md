//[rqes-core](../../../index.md)/[eu.europa.ec.eudi.rqes.core](../index.md)/[RQESService](index.md)

# RQESService

interface [RQESService](index.md)

The RQES (Remote Qualified Electronic Signature) service interface.

This interface provides methods to interact with the RQES service for document signing. The service workflow is divided into two parts:

- 
   The authorization phase, which authenticates and authorizes the service to access user credentials
- 
   The credential phase, which handles document signing with authorized credentials

#### Inheritors

| |
|---|
| [RQESServiceImpl](../-r-q-e-s-service-impl/index.md) |

## Types

| Name | Summary |
|---|---|
| [Authorized](-authorized/index.md) | [androidJvm]<br>interface [Authorized](-authorized/index.md)<br>Interface for interacting with the RQES service after successful authorization. |
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |
| [CredentialAuthorized](-credential-authorized/index.md) | [androidJvm]<br>interface [CredentialAuthorized](-credential-authorized/index.md)<br>Interface for signing documents with an authorized credential. |

## Properties

| Name | Summary |
|---|---|
| [hashAlgorithm](hash-algorithm.md) | [androidJvm]<br>abstract val [hashAlgorithm](hash-algorithm.md): HashAlgorithmOID<br>The algorithm OID used for hashing documents during the signing process. |

## Functions

| Name | Summary |
|---|---|
| [authorizeService](authorize-service.md) | [androidJvm]<br>abstract suspend fun [authorizeService](authorize-service.md)(authorizationCode: AuthorizationCode): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-result/index.html)&lt;[RQESService.Authorized](-authorized/index.md)&gt;<br>Completes the authorization process with the service. |
| [getRSSPMetadata](get-r-s-s-p-metadata.md) | [androidJvm]<br>abstract suspend fun [getRSSPMetadata](get-r-s-s-p-metadata.md)(): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-result/index.html)&lt;RSSPMetadata&gt;<br>Retrieves the Remote Signature Service Provider (RSSP) metadata. |
| [getServiceAuthorizationUrl](get-service-authorization-url.md) | [androidJvm]<br>abstract suspend fun [getServiceAuthorizationUrl](get-service-authorization-url.md)(): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-result/index.html)&lt;HttpsUrl&gt;<br>Retrieves the service authorization URL. |
