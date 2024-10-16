//[rqes-core](../../../index.md)/[eu.europa.ec.eudi.rqes.core](../index.md)/[RQESServiceImpl](index.md)

# RQESServiceImpl

class [RQESServiceImpl](index.md)(serviceEndpointUrl: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), config: CSCClientConfig, scaService: [SCAService](../-s-c-a-service/index.md), clientFactory: () -&gt; HttpClient? = null) : [RQESService](../-r-q-e-s-service/index.md)

The RQES service implementation. This class provides the implementation of the RQES service.

#### Parameters

androidJvm

| | |
|---|---|
| serviceEndpointUrl | The RQES service endpoint URL. |
| config | The RQES service configuration. |
| digestGenerator | The document hash calculator. |
| signatureEmbedder | The document signature embedder. |
| clientFactory | The HTTP client factory. If this property is null, the default HTTP client factory will be used. |

## Constructors

| | |
|---|---|
| [RQESServiceImpl](-r-q-e-s-service-impl.md) | [androidJvm]<br>constructor(serviceEndpointUrl: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), config: CSCClientConfig, scaService: [SCAService](../-s-c-a-service/index.md), clientFactory: () -&gt; HttpClient? = null)<br>Creates a RQES service implementation. |

## Types

| Name | Summary |
|---|---|
| [AuthorizedImpl](-authorized-impl/index.md) | [androidJvm]<br>class [AuthorizedImpl](-authorized-impl/index.md)(serverState: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), client: CSCClient, serviceAccessAuthorized: ServiceAccessAuthorized, scaService: [SCAService](../-s-c-a-service/index.md)) : [RQESService.Authorized](../-r-q-e-s-service/-authorized/index.md) |
| [CredentialAuthorizedImpl](-credential-authorized-impl/index.md) | [androidJvm]<br>class [CredentialAuthorizedImpl](-credential-authorized-impl/index.md)(client: CSCClient, documents: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Document](../-document/index.md)&gt;, credentialAuthorized: CredentialAuthorized.SCAL2, signatureEmbedder: [DocumentSignatureEmbedder](../-document-signature-embedder/index.md)) : [RQESService.CredentialAuthorized](../-r-q-e-s-service/-credential-authorized/index.md) |

## Functions

| Name | Summary |
|---|---|
| [authorizeService](authorize-service.md) | [androidJvm]<br>open suspend override fun [authorizeService](authorize-service.md)(authorizationCode: AuthorizationCode): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;[RQESService.Authorized](../-r-q-e-s-service/-authorized/index.md)&gt;<br>Authorize with the service. This method is used to authorize the service to access the user's credentials. Once the authorizationCode is obtained using the service authorization URL, it can be used to authorize the service. |
| [getRSSPMetadata](get-r-s-s-p-metadata.md) | [androidJvm]<br>open suspend override fun [getRSSPMetadata](get-r-s-s-p-metadata.md)(): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;RSSPMetadata&gt;<br>Get the RSSP metadata. This method is used to get the RSSP metadata. The RSSP metadata contains the information about the RSSP. |
| [getServiceAuthorizationUrl](get-service-authorization-url.md) | [androidJvm]<br>open suspend override fun [getServiceAuthorizationUrl](get-service-authorization-url.md)(): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;HttpsUrl&gt;<br>Get the service authorization URL. This method is used to get the service authorization URL. The service authorization URL is used to authorize the service to access the user's credentials. |
