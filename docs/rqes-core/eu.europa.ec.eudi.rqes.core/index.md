//[rqes-core](../../index.md)/[eu.europa.ec.eudi.rqes.core](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [DocumentLabel](-document-label/index.md) | [androidJvm]<br>typealias [DocumentLabel](-document-label/index.md) = [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)<br>A document label identifier. Used as the key in the map of signed documents. |
| [RQESService](-r-q-e-s-service/index.md) | [androidJvm]<br>interface [RQESService](-r-q-e-s-service/index.md)<br>The RQES (Remote Qualified Electronic Signature) service interface. |
| [RQESServiceImpl](-r-q-e-s-service-impl/index.md) | [androidJvm]<br>class [RQESServiceImpl](-r-q-e-s-service-impl/index.md)(serviceEndpointUrl: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), config: CSCClientConfig, outputPathDir: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), val hashAlgorithm: HashAlgorithmOID, clientFactory: () -&gt; HttpClient? = null) : [RQESService](-r-q-e-s-service/index.md)<br>The RQES service implementation. This class provides the implementation of the RQES service. |
| [SignedDocuments](-signed-documents/index.md) | [androidJvm]<br>class [SignedDocuments](-signed-documents/index.md) : [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-map/index.html)&lt;[DocumentLabel](-document-label/index.md), [File](https://developer.android.com/reference/kotlin/java/io/File.html)&gt; <br>Represents a collection of signed documents organized as a map. |
| [UnsignedDocument](-unsigned-document/index.md) | [androidJvm]<br>data class [UnsignedDocument](-unsigned-document/index.md)(val label: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), val file: [File](https://developer.android.com/reference/kotlin/java/io/File.html), val signingConfig: [UnsignedDocument.Config](-unsigned-document/-config/index.md) = DEFAULT)<br>Represents a document that is to be signed. |
| [UnsignedDocuments](-unsigned-documents/index.md) | [androidJvm]<br>class [UnsignedDocuments](-unsigned-documents/index.md)(unsignedDocuments: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[UnsignedDocument](-unsigned-document/index.md)&gt;) : [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[UnsignedDocument](-unsigned-document/index.md)&gt; <br>A collection of [UnsignedDocument](-unsigned-document/index.md)s that are to be signed. |

## Functions

| Name | Summary |
|---|---|
| [signDocuments](sign-documents.md) | [androidJvm]<br>suspend fun [RQESService.Authorized](-r-q-e-s-service/-authorized/index.md).[signDocuments](sign-documents.md)(authorizationCode: AuthorizationCode): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-result/index.html)&lt;[SignedDocuments](-signed-documents/index.md)&gt;<br>Sign the documents with the given authorization code. |
