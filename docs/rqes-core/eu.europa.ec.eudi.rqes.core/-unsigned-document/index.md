//[rqes-core](../../../index.md)/[eu.europa.ec.eudi.rqes.core](../index.md)/[UnsignedDocument](index.md)

# UnsignedDocument

[androidJvm]\
data class [UnsignedDocument](index.md)(val label: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), val file: [File](https://developer.android.com/reference/kotlin/java/io/File.html), val signingConfig: [UnsignedDocument.Config](-config/index.md) = DEFAULT)

Represents a document that is to be signed.

This class encapsulates a file along with its identifying label and configuration parameters that determine how the document should be signed.

## Constructors

| | |
|---|---|
| [UnsignedDocument](-unsigned-document.md) | [androidJvm]<br>constructor(label: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), file: [File](https://developer.android.com/reference/kotlin/java/io/File.html), signingConfig: [UnsignedDocument.Config](-config/index.md) = DEFAULT) |

## Types

| Name | Summary |
|---|---|
| [Config](-config/index.md) | [androidJvm]<br>data class [Config](-config/index.md)(val signatureFormat: SignatureFormat, val conformanceLevel: ConformanceLevel, val signedEnvelopeProperty: SignedEnvelopeProperty, val asicContainer: ASICContainer)<br>Configuration for the signing process. |

## Properties

| Name | Summary |
|---|---|
| [file](file.md) | [androidJvm]<br>val [file](file.md): [File](https://developer.android.com/reference/kotlin/java/io/File.html)<br>The file to be signed. |
| [label](label.md) | [androidJvm]<br>val [label](label.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html)<br>The identifying label of the document. |
| [signingConfig](signing-config.md) | [androidJvm]<br>val [signingConfig](signing-config.md): [UnsignedDocument.Config](-config/index.md)<br>The configuration for the signing process. |
