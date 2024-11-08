//[rqes-core](../../../index.md)/[eu.europa.ec.eudi.rqes.core](../index.md)/[SignedDocuments](index.md)

# SignedDocuments

class [SignedDocuments](index.md)(signedDocuments: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[InputStream](https://developer.android.com/reference/kotlin/java/io/InputStream.html)&gt;) : [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[InputStream](https://developer.android.com/reference/kotlin/java/io/InputStream.html)&gt; 

Represents a list of signed documents.

#### Parameters

androidJvm

| | |
|---|---|
| signedDocuments | The list of [InputStream](https://developer.android.com/reference/kotlin/java/io/InputStream.html) of signed documents' content. |

## Constructors

| | |
|---|---|
| [SignedDocuments](-signed-documents.md) | [androidJvm]<br>constructor(signedDocuments: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[InputStream](https://developer.android.com/reference/kotlin/java/io/InputStream.html)&gt;)<br>Creates a new instance of [SignedDocuments](index.md) with the given list of signed documents. |

## Properties

| Name | Summary |
|---|---|
| [size](../-unsigned-documents/index.md#844915858%2FProperties%2F313640094) | [androidJvm]<br>open override val [size](../-unsigned-documents/index.md#844915858%2FProperties%2F313640094): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |

## Functions

| Name | Summary |
|---|---|
| [contains](index.md#1234209280%2FFunctions%2F313640094) | [androidJvm]<br>open operator override fun [contains](index.md#1234209280%2FFunctions%2F313640094)(element: [InputStream](https://developer.android.com/reference/kotlin/java/io/InputStream.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [containsAll](index.md#350214111%2FFunctions%2F313640094) | [androidJvm]<br>open override fun [containsAll](index.md#350214111%2FFunctions%2F313640094)(elements: [Collection](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-collection/index.html)&lt;[InputStream](https://developer.android.com/reference/kotlin/java/io/InputStream.html)&gt;): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [forEach](index.md#-485130965%2FFunctions%2F313640094) | [androidJvm]<br>open fun [forEach](index.md#-485130965%2FFunctions%2F313640094)(p0: [Consumer](https://developer.android.com/reference/kotlin/java/util/function/Consumer.html)&lt;in [InputStream](https://developer.android.com/reference/kotlin/java/io/InputStream.html)&gt;) |
| [get](../-unsigned-documents/index.md#961975567%2FFunctions%2F313640094) | [androidJvm]<br>open operator override fun [get](../-unsigned-documents/index.md#961975567%2FFunctions%2F313640094)(index: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [InputStream](https://developer.android.com/reference/kotlin/java/io/InputStream.html) |
| [indexOf](index.md#-925884714%2FFunctions%2F313640094) | [androidJvm]<br>open override fun [indexOf](index.md#-925884714%2FFunctions%2F313640094)(element: [InputStream](https://developer.android.com/reference/kotlin/java/io/InputStream.html)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [isEmpty](../-unsigned-documents/index.md#-1000881820%2FFunctions%2F313640094) | [androidJvm]<br>open override fun [isEmpty](../-unsigned-documents/index.md#-1000881820%2FFunctions%2F313640094)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [iterator](../-unsigned-documents/index.md#-1577986619%2FFunctions%2F313640094) | [androidJvm]<br>open operator override fun [iterator](../-unsigned-documents/index.md#-1577986619%2FFunctions%2F313640094)(): [Iterator](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-iterator/index.html)&lt;[InputStream](https://developer.android.com/reference/kotlin/java/io/InputStream.html)&gt; |
| [lastIndexOf](index.md#1874820448%2FFunctions%2F313640094) | [androidJvm]<br>open override fun [lastIndexOf](index.md#1874820448%2FFunctions%2F313640094)(element: [InputStream](https://developer.android.com/reference/kotlin/java/io/InputStream.html)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [listIterator](../-unsigned-documents/index.md#-236165689%2FFunctions%2F313640094) | [androidJvm]<br>open override fun [listIterator](../-unsigned-documents/index.md#-236165689%2FFunctions%2F313640094)(): [ListIterator](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list-iterator/index.html)&lt;[InputStream](https://developer.android.com/reference/kotlin/java/io/InputStream.html)&gt;<br>open override fun [listIterator](../-unsigned-documents/index.md#845091493%2FFunctions%2F313640094)(index: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [ListIterator](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list-iterator/index.html)&lt;[InputStream](https://developer.android.com/reference/kotlin/java/io/InputStream.html)&gt; |
| [parallelStream](../-unsigned-documents/index.md#-1592339412%2FFunctions%2F313640094) | [androidJvm]<br>open fun [parallelStream](../-unsigned-documents/index.md#-1592339412%2FFunctions%2F313640094)(): [Stream](https://developer.android.com/reference/kotlin/java/util/stream/Stream.html)&lt;[InputStream](https://developer.android.com/reference/kotlin/java/io/InputStream.html)&gt; |
| [spliterator](../-unsigned-documents/index.md#703021258%2FFunctions%2F313640094) | [androidJvm]<br>open override fun [spliterator](../-unsigned-documents/index.md#703021258%2FFunctions%2F313640094)(): [Spliterator](https://developer.android.com/reference/kotlin/java/util/Spliterator.html)&lt;[InputStream](https://developer.android.com/reference/kotlin/java/io/InputStream.html)&gt; |
| [stream](../-unsigned-documents/index.md#135225651%2FFunctions%2F313640094) | [androidJvm]<br>open fun [stream](../-unsigned-documents/index.md#135225651%2FFunctions%2F313640094)(): [Stream](https://developer.android.com/reference/kotlin/java/util/stream/Stream.html)&lt;[InputStream](https://developer.android.com/reference/kotlin/java/io/InputStream.html)&gt; |
| [subList](../-unsigned-documents/index.md#423386006%2FFunctions%2F313640094) | [androidJvm]<br>open override fun [subList](../-unsigned-documents/index.md#423386006%2FFunctions%2F313640094)(fromIndex: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), toIndex: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[InputStream](https://developer.android.com/reference/kotlin/java/io/InputStream.html)&gt; |
| [toArray](../-unsigned-documents/index.md#-1215154575%2FFunctions%2F313640094) | [androidJvm]<br>open fun &lt;[T](../-unsigned-documents/index.md#-1215154575%2FFunctions%2F313640094) : [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)&gt; [~~toArray~~](../-unsigned-documents/index.md#-1215154575%2FFunctions%2F313640094)(p0: [IntFunction](https://developer.android.com/reference/kotlin/java/util/function/IntFunction.html)&lt;[Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[T](../-unsigned-documents/index.md#-1215154575%2FFunctions%2F313640094)&gt;&gt;): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[T](../-unsigned-documents/index.md#-1215154575%2FFunctions%2F313640094)&gt; |
