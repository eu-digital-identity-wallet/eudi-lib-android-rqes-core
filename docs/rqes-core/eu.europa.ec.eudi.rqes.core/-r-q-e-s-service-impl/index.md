//[rqes-core](../../../index.md)/[eu.europa.ec.eudi.rqes.core](../index.md)/[RQESServiceImpl](index.md)

# RQESServiceImpl

class [RQESServiceImpl](index.md)(serviceEndpointUrl: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), config: CSCClientConfig, val hashAlgorithm: HashAlgorithmOID, val signingAlgorithm: SigningAlgorithmOID, clientFactory: () -&gt; HttpClient? = null) : [RQESService](../-r-q-e-s-service/index.md)

The RQES service implementation. This class provides the implementation of the RQES service.

#### Parameters

androidJvm

| | |
|---|---|
| serviceEndpointUrl | The RQES service endpoint URL. |
| config | The RQES service configuration. |
| hashAlgorithm | The algorithm OID, for hashing the documents. |
| signingAlgorithm | The algorithm OID, for signing the documents. |
| clientFactory | The HTTP client factory. If this property is null, the default HTTP client factory will be used. |

## Constructors

| | |
|---|---|
| [RQESServiceImpl](-r-q-e-s-service-impl.md) | [androidJvm]<br>constructor(serviceEndpointUrl: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), config: CSCClientConfig, hashAlgorithm: HashAlgorithmOID, signingAlgorithm: SigningAlgorithmOID, clientFactory: () -&gt; HttpClient? = null)<br>Creates a RQES service implementation. |

## Types

| Name | Summary |
|---|---|
| [AuthorizedImpl](-authorized-impl/index.md) | [androidJvm]<br>class [AuthorizedImpl](-authorized-impl/index.md)(serverState: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), client: CSCClient, serviceAccessAuthorized: ServiceAccessAuthorized, val hashAlgorithm: HashAlgorithmOID, val signingAlgorithm: SigningAlgorithmOID) : [RQESService.Authorized](../-r-q-e-s-service/-authorized/index.md)<br>The authorized service implementation. |
| [CredentialAuthorizedImpl](-credential-authorized-impl/index.md) | [androidJvm]<br>class [CredentialAuthorizedImpl](-credential-authorized-impl/index.md)(client: CSCClient, documentsToSign: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;DocumentToSign&gt;, documentDigestList: DocumentDigestList, credentialAuthorized: CredentialAuthorized, val signingAlgorithm: SigningAlgorithmOID) : [RQESService.CredentialAuthorized](../-r-q-e-s-service/-credential-authorized/index.md)<br>The credential authorized implementation. |

## Properties

| Name | Summary |
|---|---|
| [hashAlgorithm](hash-algorithm.md) | [androidJvm]<br>open override val [hashAlgorithm](hash-algorithm.md): HashAlgorithmOID<br>The algorithm OID, for hashing the documents. |
| [signingAlgorithm](signing-algorithm.md) | [androidJvm]<br>open override val [signingAlgorithm](signing-algorithm.md): SigningAlgorithmOID<br>The algorithm OID, for signing the documents. |

## Functions

| Name | Summary |
|---|---|
| [authorizeService](authorize-service.md) | [androidJvm]<br>open suspend override fun [authorizeService](authorize-service.md)(authorizationCode: AuthorizationCode): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;[RQESService.Authorized](../-r-q-e-s-service/-authorized/index.md)&gt;<br>Authorize with the service. This method is used to authorize the service to access the user's credentials. Once the authorizationCode is obtained using the service authorization URL, it can be used to authorize the service. |
| [getRSSPMetadata](get-r-s-s-p-metadata.md) | [androidJvm]<br>open suspend override fun [getRSSPMetadata](get-r-s-s-p-metadata.md)(): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;RSSPMetadata&gt;<br>Get the RSSP metadata. This method is used to get the RSSP metadata. The RSSP metadata contains the information about the RSSP. |
| [getServiceAuthorizationUrl](get-service-authorization-url.md) | [androidJvm]<br>open suspend override fun [getServiceAuthorizationUrl](get-service-authorization-url.md)(): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;HttpsUrl&gt;<br>Get the service authorization URL. This method is used to get the service authorization URL. The service authorization URL is used to authorize the service to access the user's credentials. |
