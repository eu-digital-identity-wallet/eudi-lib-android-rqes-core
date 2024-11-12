//[rqes-core](../../../index.md)/[eu.europa.ec.eudi.rqes.core](../index.md)/[RQESServiceImpl](index.md)/[RQESServiceImpl](-r-q-e-s-service-impl.md)

# RQESServiceImpl

[androidJvm]\
constructor(serviceEndpointUrl: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), config: CSCClientConfig, hashAlgorithm: HashAlgorithmOID, signingAlgorithm: SigningAlgorithmOID, clientFactory: () -&gt; HttpClient? = null)

Creates a RQES service implementation.

#### Parameters

androidJvm

| | |
|---|---|
| serviceEndpointUrl | The RQES service endpoint URL. |
| config | The RQES service configuration. |
| hashAlgorithm | The algorithm OID, for hashing the documents. |
| signingAlgorithm | The algorithm OID, for signing the documents. |
| clientFactory | The HTTP client factory. If this property is null, the default HTTP client factory will be used. |
