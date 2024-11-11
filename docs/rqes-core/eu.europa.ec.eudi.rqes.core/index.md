//[rqes-core](../../index.md)/[eu.europa.ec.eudi.rqes.core](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [RQESService](-r-q-e-s-service/index.md) | [androidJvm]<br>interface [RQESService](-r-q-e-s-service/index.md)<br>The RQES service interface. This interface provides the methods to interact with the RQES service. The service is divided into two parts: |
| [RQESServiceImpl](-r-q-e-s-service-impl/index.md) | [androidJvm]<br>class [RQESServiceImpl](-r-q-e-s-service-impl/index.md)(serviceEndpointUrl: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), config: CSCClientConfig, val hashAlgorithm: HashAlgorithmOID, val signingAlgorithm: SigningAlgorithmOID, clientFactory: () -&gt; HttpClient? = null) : [RQESService](-r-q-e-s-service/index.md)<br>The RQES service implementation. This class provides the implementation of the RQES service. |
| [SignedDocuments](-signed-documents/index.md) | [androidJvm]<br>class [SignedDocuments](-signed-documents/index.md)(signedDocuments: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[InputStream](https://developer.android.com/reference/kotlin/java/io/InputStream.html)&gt;) : [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[InputStream](https://developer.android.com/reference/kotlin/java/io/InputStream.html)&gt; <br>Represents a list of signed documents. |
| [UnsignedDocument](-unsigned-document/index.md) | [androidJvm]<br>data class [UnsignedDocument](-unsigned-document/index.md)(val label: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val file: [File](https://developer.android.com/reference/kotlin/java/io/File.html), val signingConfig: [UnsignedDocument.Config](-unsigned-document/-config/index.md) = Config.DEFAULT)<br>Represents a document that is to be signed. |
| [UnsignedDocuments](-unsigned-documents/index.md) | [androidJvm]<br>class [UnsignedDocuments](-unsigned-documents/index.md)(unsignedDocuments: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[UnsignedDocument](-unsigned-document/index.md)&gt;) : [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[UnsignedDocument](-unsigned-document/index.md)&gt; <br>A list of [UnsignedDocument](-unsigned-document/index.md)s to be signed. |

## Functions

| Name | Summary |
|---|---|
| [signDocuments](sign-documents.md) | [androidJvm]<br>suspend fun [RQESService.Authorized](-r-q-e-s-service/-authorized/index.md).[signDocuments](sign-documents.md)(authorizationCode: AuthorizationCode): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;[SignedDocuments](-signed-documents/index.md)&gt;<br>Sign the documents with the given authorization code. |
