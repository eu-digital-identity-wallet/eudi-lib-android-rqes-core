//[rqes-core](../../../index.md)/[eu.europa.ec.eudi.rqes.core](../index.md)/[UnsignedDocuments](index.md)

# UnsignedDocuments

class [UnsignedDocuments](index.md)(unsignedDocuments: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[UnsignedDocument](../-unsigned-document/index.md)&gt;) : [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[UnsignedDocument](../-unsigned-document/index.md)&gt; 

A collection of [UnsignedDocument](../-unsigned-document/index.md)s that are to be signed.

This class provides a convenient way to work with multiple unsigned documents as a single unit. It implements the [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html) interface for easy iteration and access to the underlying documents.

#### Parameters

androidJvm

| | |
|---|---|
| unsignedDocuments | The list of [UnsignedDocument](../-unsigned-document/index.md)s to be signed. |

## Constructors

| | |
|---|---|
| [UnsignedDocuments](-unsigned-documents.md) | [androidJvm]<br>constructor(vararg unsignedDocuments: [UnsignedDocument](../-unsigned-document/index.md))<br>Creates a new [UnsignedDocuments](index.md) with the given vararg [UnsignedDocument](../-unsigned-document/index.md)s.<br>constructor(unsignedDocuments: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[UnsignedDocument](../-unsigned-document/index.md)&gt;)<br>Creates a new [UnsignedDocuments](index.md) with the given list of [UnsignedDocument](../-unsigned-document/index.md)s and hash algorithm. |

## Properties

| Name | Summary |
|---|---|
| [size](index.md#844915858%2FProperties%2F313640094) | [androidJvm]<br>open override val [size](index.md#844915858%2FProperties%2F313640094): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html) |

## Functions

| Name | Summary |
|---|---|
| [addFirst](index.md#-1987565877%2FFunctions%2F313640094) | [androidJvm]<br>open override fun [addFirst](index.md#-1987565877%2FFunctions%2F313640094)(p0: [UnsignedDocument](../-unsigned-document/index.md)) |
| [addLast](index.md#-1964895913%2FFunctions%2F313640094) | [androidJvm]<br>open override fun [addLast](index.md#-1964895913%2FFunctions%2F313640094)(p0: [UnsignedDocument](../-unsigned-document/index.md)) |
| [contains](index.md#-1316744901%2FFunctions%2F313640094) | [androidJvm]<br>open operator override fun [contains](index.md#-1316744901%2FFunctions%2F313640094)(element: [UnsignedDocument](../-unsigned-document/index.md)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html) |
| [containsAll](index.md#-273881200%2FFunctions%2F313640094) | [androidJvm]<br>open override fun [containsAll](index.md#-273881200%2FFunctions%2F313640094)(elements: [Collection](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-collection/index.html)&lt;[UnsignedDocument](../-unsigned-document/index.md)&gt;): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html) |
| [forEach](index.md#-1238396988%2FFunctions%2F313640094) | [androidJvm]<br>open fun [forEach](index.md#-1238396988%2FFunctions%2F313640094)(p0: [Consumer](https://developer.android.com/reference/kotlin/java/util/function/Consumer.html)&lt;in [UnsignedDocument](../-unsigned-document/index.md)&gt;) |
| [get](index.md#961975567%2FFunctions%2F313640094) | [androidJvm]<br>open operator override fun [get](index.md#961975567%2FFunctions%2F313640094)(index: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html)): [UnsignedDocument](../-unsigned-document/index.md) |
| [getFirst](index.md#423156409%2FFunctions%2F313640094) | [androidJvm]<br>open override fun [~~getFirst~~](index.md#423156409%2FFunctions%2F313640094)(): [UnsignedDocument](../-unsigned-document/index.md) |
| [getLast](index.md#-53199685%2FFunctions%2F313640094) | [androidJvm]<br>open override fun [~~getLast~~](index.md#-53199685%2FFunctions%2F313640094)(): [UnsignedDocument](../-unsigned-document/index.md) |
| [indexOf](index.md#-1226933979%2FFunctions%2F313640094) | [androidJvm]<br>open override fun [indexOf](index.md#-1226933979%2FFunctions%2F313640094)(element: [UnsignedDocument](../-unsigned-document/index.md)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html) |
| [isEmpty](index.md#-1000881820%2FFunctions%2F313640094) | [androidJvm]<br>open override fun [isEmpty](index.md#-1000881820%2FFunctions%2F313640094)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-boolean/index.html) |
| [iterator](index.md#-1577986619%2FFunctions%2F313640094) | [androidJvm]<br>open operator override fun [iterator](index.md#-1577986619%2FFunctions%2F313640094)(): [Iterator](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-iterator/index.html)&lt;[UnsignedDocument](../-unsigned-document/index.md)&gt; |
| [lastIndexOf](index.md#914074075%2FFunctions%2F313640094) | [androidJvm]<br>open override fun [lastIndexOf](index.md#914074075%2FFunctions%2F313640094)(element: [UnsignedDocument](../-unsigned-document/index.md)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html) |
| [listIterator](index.md#-236165689%2FFunctions%2F313640094) | [androidJvm]<br>open override fun [listIterator](index.md#-236165689%2FFunctions%2F313640094)(): [ListIterator](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list-iterator/index.html)&lt;[UnsignedDocument](../-unsigned-document/index.md)&gt;<br>open override fun [listIterator](index.md#845091493%2FFunctions%2F313640094)(index: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html)): [ListIterator](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list-iterator/index.html)&lt;[UnsignedDocument](../-unsigned-document/index.md)&gt; |
| [parallelStream](index.md#-1592339412%2FFunctions%2F313640094) | [androidJvm]<br>open fun [parallelStream](index.md#-1592339412%2FFunctions%2F313640094)(): [Stream](https://developer.android.com/reference/kotlin/java/util/stream/Stream.html)&lt;[UnsignedDocument](../-unsigned-document/index.md)&gt; |
| [removeFirst](index.md#-1089669573%2FFunctions%2F313640094) | [androidJvm]<br>open override fun [removeFirst](index.md#-1089669573%2FFunctions%2F313640094)(): [UnsignedDocument](../-unsigned-document/index.md) |
| [removeLast](index.md#36546809%2FFunctions%2F313640094) | [androidJvm]<br>open override fun [removeLast](index.md#36546809%2FFunctions%2F313640094)(): [UnsignedDocument](../-unsigned-document/index.md) |
| [reversed](index.md#-455853327%2FFunctions%2F313640094) | [androidJvm]<br>open override fun [reversed](index.md#-455853327%2FFunctions%2F313640094)(): [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-mutable-list/index.html)&lt;[UnsignedDocument](../-unsigned-document/index.md)&gt; |
| [spliterator](index.md#703021258%2FFunctions%2F313640094) | [androidJvm]<br>open override fun [spliterator](index.md#703021258%2FFunctions%2F313640094)(): [Spliterator](https://developer.android.com/reference/kotlin/java/util/Spliterator.html)&lt;[UnsignedDocument](../-unsigned-document/index.md)&gt; |
| [stream](index.md#135225651%2FFunctions%2F313640094) | [androidJvm]<br>open fun [stream](index.md#135225651%2FFunctions%2F313640094)(): [Stream](https://developer.android.com/reference/kotlin/java/util/stream/Stream.html)&lt;[UnsignedDocument](../-unsigned-document/index.md)&gt; |
| [subList](index.md#423386006%2FFunctions%2F313640094) | [androidJvm]<br>open override fun [subList](index.md#423386006%2FFunctions%2F313640094)(fromIndex: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html), toIndex: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-int/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[UnsignedDocument](../-unsigned-document/index.md)&gt; |
| [toArray](index.md#-1215154575%2FFunctions%2F313640094) | [androidJvm]<br>open fun &lt;[T](index.md#-1215154575%2FFunctions%2F313640094) : [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-any/index.html)&gt; [~~toArray~~](index.md#-1215154575%2FFunctions%2F313640094)(p0: [IntFunction](https://developer.android.com/reference/kotlin/java/util/function/IntFunction.html)&lt;[Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-array/index.html)&lt;[T](index.md#-1215154575%2FFunctions%2F313640094)&gt;&gt;): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-array/index.html)&lt;[T](index.md#-1215154575%2FFunctions%2F313640094)&gt; |
| [toUnsignedDocuments](../../eu.europa.ec.eudi.rqes.core.documentRetrieval/to-unsigned-documents.md) | [androidJvm]<br>fun [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[ResolvedDocument](../../eu.europa.ec.eudi.rqes.core.documentRetrieval/-resolved-document/index.md)&gt;.[toUnsignedDocuments](../../eu.europa.ec.eudi.rqes.core.documentRetrieval/to-unsigned-documents.md)(signingConfig: [UnsignedDocument.Config](../-unsigned-document/-config/index.md)? = null): [UnsignedDocuments](index.md)<br>Convert a list of [ResolvedDocument](../../eu.europa.ec.eudi.rqes.core.documentRetrieval/-resolved-document/index.md) to [UnsignedDocuments](index.md). |
