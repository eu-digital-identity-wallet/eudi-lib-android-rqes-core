//[rqes-core](../../index.md)/[eu.europa.ec.eudi.rqes.core](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [Document](-document/index.md) | [androidJvm]<br>data class [Document](-document/index.md)(val id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val data: [ByteArray](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html))<br>Represents a document. |
| [DocumentHashCalculator](-document-hash-calculator/index.md) | [androidJvm]<br>fun interface [DocumentHashCalculator](-document-hash-calculator/index.md)<br>Calculates the hash of a document. |
| [DocumentSignatureEmbedder](-document-signature-embedder/index.md) | [androidJvm]<br>fun interface [DocumentSignatureEmbedder](-document-signature-embedder/index.md)<br>Embeds a signature into a document. |
| [RQESService](-r-q-e-s-service/index.md) | [androidJvm]<br>interface [RQESService](-r-q-e-s-service/index.md)<br>The RQES service interface. This interface provides the methods to interact with the RQES service. The service is divided into two parts: |
| [RQESServiceImpl](-r-q-e-s-service-impl/index.md) | [androidJvm]<br>class [RQESServiceImpl](-r-q-e-s-service-impl/index.md)(serviceEndpointUrl: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), config: CSCClientConfig, scaService: [SCAService](-s-c-a-service/index.md), clientFactory: () -&gt; HttpClient? = null) : [RQESService](-r-q-e-s-service/index.md)<br>The RQES service implementation. This class provides the implementation of the RQES service. |
| [SCAService](-s-c-a-service/index.md) | [androidJvm]<br>interface [SCAService](-s-c-a-service/index.md) : [DocumentHashCalculator](-document-hash-calculator/index.md), [DocumentSignatureEmbedder](-document-signature-embedder/index.md)<br>Interface for the Signature Creation Application service. |
| [SCAServiceImpl](-s-c-a-service-impl/index.md) | [androidJvm]<br>class [SCAServiceImpl](-s-c-a-service-impl/index.md) : [SCAService](-s-c-a-service/index.md)<br>Implementation of the [SCAService](-s-c-a-service/index.md) interface. |
