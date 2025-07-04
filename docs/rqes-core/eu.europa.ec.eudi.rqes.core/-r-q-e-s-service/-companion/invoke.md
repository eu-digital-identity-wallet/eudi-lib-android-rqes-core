//[rqes-core](../../../../index.md)/[eu.europa.ec.eudi.rqes.core](../../index.md)/[RQESService](../index.md)/[Companion](index.md)/[invoke](invoke.md)

# invoke

[androidJvm]\
operator fun [invoke](invoke.md)(serviceEndpointUrl: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), config: CSCClientConfig, outputPathDir: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), hashAlgorithm: HashAlgorithmOID = HashAlgorithmOID.SHA_256, httpClientFactory: () -&gt; HttpClient? = null): [RQESService](../index.md)

Creates an instance of the RQES service.

This factory method constructs and configures an RQES service implementation with the specified parameters.

#### Return

A configured [RQESService](../index.md) implementation.

#### Parameters

androidJvm

| | |
|---|---|
| serviceEndpointUrl | The RSSP service endpoint URL. |
| config | The Cloud Signature Consortium client configuration. |
| outputPathDir | The directory where signed documents will be stored. |
| hashAlgorithm | The algorithm OID to use for document hashing, defaults to SHA-256. |
| httpClientFactory | Optional custom HTTP client factory for network requests. |

#### Throws

| | |
|---|---|
| [IllegalArgumentException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-illegal-argument-exception/index.html) | If [outputPathDir](invoke.md) does not point to a valid directory. |
