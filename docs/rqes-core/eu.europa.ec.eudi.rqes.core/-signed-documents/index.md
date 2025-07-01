//[rqes-core](../../../index.md)/[eu.europa.ec.eudi.rqes.core](../index.md)/[SignedDocuments](index.md)

# SignedDocuments

class [SignedDocuments](index.md) : [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-map/index.html)&lt;[DocumentLabel](../-document-label/index.md), [File](https://developer.android.com/reference/kotlin/java/io/File.html)&gt; 

Represents a collection of signed documents organized as a map.

This class provides access to signed document files, mapped by their document labels. It implements the [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-map/index.html) interface, allowing direct access to documents using their labels as keys.

#### Parameters

androidJvm

| | |
|---|---|
| signedDocuments | A map where keys are document labels and values are the corresponding signed document files. |

## Properties

| Name | Summary |
|---|---|
| [entries](index.md#43098407%2FProperties%2F313640094) | [androidJvm]<br>open override val [entries](index.md#43098407%2FProperties%2F313640094): [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-set/index.html)&lt;[Map.Entry](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-map/-entry/index.html)&lt;[DocumentLabel](../-document-label/index.md), [File](https://developer.android.com/reference/kotlin/java/io/File.html)&gt;&gt; |
| [keys](index.md#-915758449%2FProperties%2F313640094) | [androidJvm]<br>open override val [keys](index.md#-915758449%2FProperties%2F313640094): [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-set/index.html)&lt;[DocumentLabel](../-document-label/index.md)&gt; |
| [size](index.md#-157521630%2FProperties%2F313640094) | [androidJvm]<br>open override val [size](index.md#-157521630%2FProperties%2F313640094): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html) |
| [values](index.md#1310951841%2FProperties%2F313640094) | [androidJvm]<br>open override val [values](index.md#1310951841%2FProperties%2F313640094): [Collection](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-collection/index.html)&lt;[File](https://developer.android.com/reference/kotlin/java/io/File.html)&gt; |

## Functions

| Name | Summary |
|---|---|
| [containsKey](index.md#182506349%2FFunctions%2F313640094) | [androidJvm]<br>open override fun [containsKey](index.md#182506349%2FFunctions%2F313640094)(key: [DocumentLabel](../-document-label/index.md)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html) |
| [containsValue](index.md#1808941901%2FFunctions%2F313640094) | [androidJvm]<br>open override fun [containsValue](index.md#1808941901%2FFunctions%2F313640094)(value: [File](https://developer.android.com/reference/kotlin/java/io/File.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html) |
| [forEach](index.md#1707709414%2FFunctions%2F313640094) | [androidJvm]<br>open fun [forEach](index.md#1707709414%2FFunctions%2F313640094)(p0: [BiConsumer](https://developer.android.com/reference/kotlin/java/util/function/BiConsumer.html)&lt;in [DocumentLabel](../-document-label/index.md), in [File](https://developer.android.com/reference/kotlin/java/io/File.html)&gt;) |
| [get](index.md#-1885167485%2FFunctions%2F313640094) | [androidJvm]<br>open operator override fun [get](index.md#-1885167485%2FFunctions%2F313640094)(key: [DocumentLabel](../-document-label/index.md)): [File](https://developer.android.com/reference/kotlin/java/io/File.html)? |
| [getOrDefault](index.md#1087765374%2FFunctions%2F313640094) | [androidJvm]<br>open fun [getOrDefault](index.md#1087765374%2FFunctions%2F313640094)(key: [DocumentLabel](../-document-label/index.md), defaultValue: [File](https://developer.android.com/reference/kotlin/java/io/File.html)): [File](https://developer.android.com/reference/kotlin/java/io/File.html) |
| [isEmpty](index.md#-1708477740%2FFunctions%2F313640094) | [androidJvm]<br>open override fun [isEmpty](index.md#-1708477740%2FFunctions%2F313640094)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html) |
