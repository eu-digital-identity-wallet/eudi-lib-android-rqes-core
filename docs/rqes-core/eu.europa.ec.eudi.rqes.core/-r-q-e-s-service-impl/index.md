//[rqes-core](../../../index.md)/[eu.europa.ec.eudi.rqes.core](../index.md)/[RQESServiceImpl](index.md)

# RQESServiceImpl

class [RQESServiceImpl](index.md)(serviceEndpointUrl: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), config: CSCClientConfig, outputPathDir: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), val hashAlgorithm: HashAlgorithmOID, clientFactory: () -&gt; HttpClient? = null) : [RQESService](../-r-q-e-s-service/index.md)

The RQES service implementation. This class provides the implementation of the RQES service.

#### Parameters

androidJvm

| | |
|---|---|
| serviceEndpointUrl | The RQES service endpoint URL. |
| config | The RQES service configuration. |
| outputPathDir | Directory where signed documents will be stored. |
| hashAlgorithm | The algorithm OID, for hashing the documents. |
| clientFactory | The HTTP client factory. If this property is null, the default HTTP client factory will be used. |

## Constructors

| | |
|---|---|
| [RQESServiceImpl](-r-q-e-s-service-impl.md) | [androidJvm]<br>constructor(serviceEndpointUrl: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), config: CSCClientConfig, outputPathDir: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), hashAlgorithm: HashAlgorithmOID, clientFactory: () -&gt; HttpClient? = null) |

## Types

| Name | Summary |
|---|---|
| [AuthorizedImpl](-authorized-impl/index.md) | [androidJvm]<br>class [AuthorizedImpl](-authorized-impl/index.md)(serverState: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), client: CSCClient, serviceAccessAuthorized: ServiceAccessAuthorized, outputPathDir: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), val hashAlgorithm: HashAlgorithmOID) : [RQESService.Authorized](../-r-q-e-s-service/-authorized/index.md)<br>Implementation of the [RQESService.Authorized](../-r-q-e-s-service/-authorized/index.md) interface that provides access to user credentials and document signing operations after successful service authorization. |
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |
| [CredentialAuthorizedImpl](-credential-authorized-impl/index.md) | [androidJvm]<br>class [CredentialAuthorizedImpl](-credential-authorized-impl/index.md)(client: CSCClient, documentsToSign: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;DocumentToSign&gt;, documentDigestList: DocumentDigestList, credentialAuthorized: CredentialAuthorized, val signingAlgorithm: SigningAlgorithmOID) : [RQESService.CredentialAuthorized](../-r-q-e-s-service/-credential-authorized/index.md)<br>Implementation of the [RQESService.CredentialAuthorized](../-r-q-e-s-service/-credential-authorized/index.md) interface that performs document signing operations with an authorized credential. |

## Properties

| Name | Summary |
|---|---|
| [hashAlgorithm](hash-algorithm.md) | [androidJvm]<br>open override val [hashAlgorithm](hash-algorithm.md): HashAlgorithmOID<br>The algorithm OID, for hashing the documents. |

## Functions

| Name | Summary |
|---|---|
| [authorizeService](authorize-service.md) | [androidJvm]<br>open suspend override fun [authorizeService](authorize-service.md)(authorizationCode: AuthorizationCode): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-result/index.html)&lt;[RQESService.Authorized](../-r-q-e-s-service/-authorized/index.md)&gt;<br>Completes the service authorization process by exchanging the authorization code for service access. |
| [getRSSPMetadata](get-r-s-s-p-metadata.md) | [androidJvm]<br>open suspend override fun [getRSSPMetadata](get-r-s-s-p-metadata.md)(): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-result/index.html)&lt;RSSPMetadata&gt;<br>Retrieves metadata from the Remote Signature Service Provider (RSSP). |
| [getServiceAuthorizationUrl](get-service-authorization-url.md) | [androidJvm]<br>open suspend override fun [getServiceAuthorizationUrl](get-service-authorization-url.md)(): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-result/index.html)&lt;HttpsUrl&gt;<br>Generates a service authorization URL for initiating the user authorization flow. |
