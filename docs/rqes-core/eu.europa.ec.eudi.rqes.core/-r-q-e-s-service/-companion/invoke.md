//[rqes-core](../../../../index.md)/[eu.europa.ec.eudi.rqes.core](../../index.md)/[RQESService](../index.md)/[Companion](index.md)/[invoke](invoke.md)

# invoke

[androidJvm]\
operator fun [invoke](invoke.md)(serviceEndpointUrl: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), config: CSCClientConfig, hashAlgorithm: HashAlgorithmOID = HashAlgorithmOID.SHA_256, signingAlgorithm: SigningAlgorithmOID = SigningAlgorithmOID.RSA_SHA256, httpClientFactory: () -&gt; HttpClient? = null): [RQESService](../index.md)

Create the RQES service.

#### Return

The RQES service.

#### Parameters

androidJvm

| | |
|---|---|
| serviceEndpointUrl | The service endpoint URL. |
| config | The CSC client configuration. |
| hashAlgorithm | The hash algorithm OID. |
| signingAlgorithm | The signing algorithm OID. |
| httpClientFactory | The HTTP client factory. |
