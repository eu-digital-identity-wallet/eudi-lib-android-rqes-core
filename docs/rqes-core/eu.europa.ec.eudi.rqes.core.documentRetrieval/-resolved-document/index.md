//[rqes-core](../../../index.md)/[eu.europa.ec.eudi.rqes.core.documentRetrieval](../index.md)/[ResolvedDocument](index.md)

# ResolvedDocument

[androidJvm]\
data class [ResolvedDocument](index.md)(val location: DocumentLocation, val digest: DocumentDigest, val file: [File](https://developer.android.com/reference/kotlin/java/io/File.html))

Represents a resolved document.

## Constructors

| | |
|---|---|
| [ResolvedDocument](-resolved-document.md) | [androidJvm]<br>constructor(location: DocumentLocation, digest: DocumentDigest, file: [File](https://developer.android.com/reference/kotlin/java/io/File.html)) |

## Properties

| Name | Summary |
|---|---|
| [digest](digest.md) | [androidJvm]<br>val [digest](digest.md): DocumentDigest<br>The digest of the document. |
| [file](file.md) | [androidJvm]<br>val [file](file.md): [File](https://developer.android.com/reference/kotlin/java/io/File.html)<br>The downloaded file of the document. |
| [location](location.md) | [androidJvm]<br>val [location](location.md): DocumentLocation<br>The location of the document. |

## Functions

| Name | Summary |
|---|---|
| [toUnsignedDocument](../to-unsigned-document.md) | [androidJvm]<br>fun [ResolvedDocument](index.md).[toUnsignedDocument](../to-unsigned-document.md)(label: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), signingConfig: [UnsignedDocument.Config](../../eu.europa.ec.eudi.rqes.core/-unsigned-document/-config/index.md)? = null): [UnsignedDocument](../../eu.europa.ec.eudi.rqes.core/-unsigned-document/index.md)<br>Convert a [ResolvedDocument](index.md) to an [UnsignedDocument](../../eu.europa.ec.eudi.rqes.core/-unsigned-document/index.md). |
