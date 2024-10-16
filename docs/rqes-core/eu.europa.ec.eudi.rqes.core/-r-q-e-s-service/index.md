//[rqes-core](../../../index.md)/[eu.europa.ec.eudi.rqes.core](../index.md)/[RQESService](index.md)

# RQESService

interface [RQESService](index.md)

The RQES service interface. This interface provides the methods to interact with the RQES service. The service is divided into two parts:

- 
   The first part is the authorization part, which is used to authorize the service to access the user's credentials.
- 
   The second part is the credential part, which is used to sign the documents.

HTTP client factory should be used. This property is optional can be used to provide a custom Ktor HTTP client factory, that can be used to create the HTTP client.

#### Inheritors

| |
|---|
| [RQESServiceImpl](../-r-q-e-s-service-impl/index.md) |

## Types

| Name | Summary |
|---|---|
| [Authorized](-authorized/index.md) | [androidJvm]<br>interface [Authorized](-authorized/index.md)<br>The authorized service interface. This interface provides the methods to interact with the authorized service. The authorized service is used to access the user's credentials and sign the documents. |
| [CredentialAuthorized](-credential-authorized/index.md) | [androidJvm]<br>interface [CredentialAuthorized](-credential-authorized/index.md)<br>The credential authorized interface. This interface provides the methods to interact with the authorized credential. The authorized credential is used to sign the documents. |

## Functions

| Name | Summary |
|---|---|
| [authorizeService](authorize-service.md) | [androidJvm]<br>abstract suspend fun [authorizeService](authorize-service.md)(authorizationCode: AuthorizationCode): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;[RQESService.Authorized](-authorized/index.md)&gt;<br>Authorize with the service. This method is used to authorize the service to access the user's credentials. Once the authorizationCode is obtained using the service authorization URL, it can be used to authorize the service. |
| [getRSSPMetadata](get-r-s-s-p-metadata.md) | [androidJvm]<br>abstract suspend fun [getRSSPMetadata](get-r-s-s-p-metadata.md)(): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;RSSPMetadata&gt;<br>Get the RSSP metadata. This method is used to get the RSSP metadata. The RSSP metadata contains the information about the RSSP. |
| [getServiceAuthorizationUrl](get-service-authorization-url.md) | [androidJvm]<br>abstract suspend fun [getServiceAuthorizationUrl](get-service-authorization-url.md)(): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;HttpsUrl&gt;<br>Get the service authorization URL. This method is used to get the service authorization URL. The service authorization URL is used to authorize the service to access the user's credentials. |
