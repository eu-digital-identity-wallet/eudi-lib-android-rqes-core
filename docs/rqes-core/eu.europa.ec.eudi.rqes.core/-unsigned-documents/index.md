//[rqes-core](../../../index.md)/[eu.europa.ec.eudi.rqes.core](../index.md)/[UnsignedDocuments](index.md)

# UnsignedDocuments

class [UnsignedDocuments](index.md)(unsignedDocuments: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[UnsignedDocument](../-unsigned-document/index.md)&gt;, val hashAlgorithmOID: HashAlgorithmOID = HashAlgorithmOID.SHA_256) : [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[UnsignedDocument](../-unsigned-document/index.md)&gt; 

A list of [UnsignedDocument](../-unsigned-document/index.md)s to be signed.

#### Parameters

androidJvm

| | |
|---|---|
| unsignedDocuments | The list of [UnsignedDocument](../-unsigned-document/index.md)s to be signed. |
| hashAlgorithmOID | The hash algorithm to be used to hash the documents. |

## Constructors

| | |
|---|---|
| [UnsignedDocuments](-unsigned-documents.md) | [androidJvm]<br>constructor(unsignedDocuments: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[UnsignedDocument](../-unsigned-document/index.md)&gt;, hashAlgorithmOID: HashAlgorithmOID = HashAlgorithmOID.SHA_256)<br>Creates a new [UnsignedDocuments](index.md) with the given list of [UnsignedDocument](../-unsigned-document/index.md)s and hash algorithm. |

## Properties

| Name | Summary |
|---|---|
| [hashAlgorithmOID](hash-algorithm-o-i-d.md) | [androidJvm]<br>val [hashAlgorithmOID](hash-algorithm-o-i-d.md): HashAlgorithmOID<br>The hash algorithm to be used to hash the documents. |
| [size](index.md#844915858%2FProperties%2F313640094) | [androidJvm]<br>open override val [size](index.md#844915858%2FProperties%2F313640094): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |

## Functions

| Name | Summary |
|---|---|
| [contains](index.md#-1316744901%2FFunctions%2F313640094) | [androidJvm]<br>open operator override fun [contains](index.md#-1316744901%2FFunctions%2F313640094)(element: [UnsignedDocument](../-unsigned-document/index.md)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [containsAll](index.md#-273881200%2FFunctions%2F313640094) | [androidJvm]<br>open override fun [containsAll](index.md#-273881200%2FFunctions%2F313640094)(elements: [Collection](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-collection/index.html)&lt;[UnsignedDocument](../-unsigned-document/index.md)&gt;): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [forEach](index.md#-1238396988%2FFunctions%2F313640094) | [androidJvm]<br>open fun [forEach](index.md#-1238396988%2FFunctions%2F313640094)(p0: [Consumer](https://developer.android.com/reference/kotlin/java/util/function/Consumer.html)&lt;in [UnsignedDocument](../-unsigned-document/index.md)&gt;) |
| [get](index.md#961975567%2FFunctions%2F313640094) | [androidJvm]<br>open operator override fun [get](index.md#961975567%2FFunctions%2F313640094)(index: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [UnsignedDocument](../-unsigned-document/index.md) |
| [indexOf](index.md#-1226933979%2FFunctions%2F313640094) | [androidJvm]<br>open override fun [indexOf](index.md#-1226933979%2FFunctions%2F313640094)(element: [UnsignedDocument](../-unsigned-document/index.md)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [isEmpty](index.md#-1000881820%2FFunctions%2F313640094) | [androidJvm]<br>open override fun [isEmpty](index.md#-1000881820%2FFunctions%2F313640094)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [iterator](index.md#-1577986619%2FFunctions%2F313640094) | [androidJvm]<br>open operator override fun [iterator](index.md#-1577986619%2FFunctions%2F313640094)(): [Iterator](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-iterator/index.html)&lt;[UnsignedDocument](../-unsigned-document/index.md)&gt; |
| [lastIndexOf](index.md#914074075%2FFunctions%2F313640094) | [androidJvm]<br>open override fun [lastIndexOf](index.md#914074075%2FFunctions%2F313640094)(element: [UnsignedDocument](../-unsigned-document/index.md)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [listIterator](index.md#-236165689%2FFunctions%2F313640094) | [androidJvm]<br>open override fun [listIterator](index.md#-236165689%2FFunctions%2F313640094)(): [ListIterator](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list-iterator/index.html)&lt;[UnsignedDocument](../-unsigned-document/index.md)&gt;<br>open override fun [listIterator](index.md#845091493%2FFunctions%2F313640094)(index: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [ListIterator](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list-iterator/index.html)&lt;[UnsignedDocument](../-unsigned-document/index.md)&gt; |
| [parallelStream](index.md#-1592339412%2FFunctions%2F313640094) | [androidJvm]<br>open fun [parallelStream](index.md#-1592339412%2FFunctions%2F313640094)(): [Stream](https://developer.android.com/reference/kotlin/java/util/stream/Stream.html)&lt;[UnsignedDocument](../-unsigned-document/index.md)&gt; |
| [spliterator](index.md#703021258%2FFunctions%2F313640094) | [androidJvm]<br>open override fun [spliterator](index.md#703021258%2FFunctions%2F313640094)(): [Spliterator](https://developer.android.com/reference/kotlin/java/util/Spliterator.html)&lt;[UnsignedDocument](../-unsigned-document/index.md)&gt; |
| [stream](index.md#135225651%2FFunctions%2F313640094) | [androidJvm]<br>open fun [stream](index.md#135225651%2FFunctions%2F313640094)(): [Stream](https://developer.android.com/reference/kotlin/java/util/stream/Stream.html)&lt;[UnsignedDocument](../-unsigned-document/index.md)&gt; |
| [subList](index.md#423386006%2FFunctions%2F313640094) | [androidJvm]<br>open override fun [subList](index.md#423386006%2FFunctions%2F313640094)(fromIndex: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), toIndex: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[UnsignedDocument](../-unsigned-document/index.md)&gt; |
| [toArray](index.md#-1215154575%2FFunctions%2F313640094) | [androidJvm]<br>open fun &lt;[T](index.md#-1215154575%2FFunctions%2F313640094) : [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)&gt; [~~toArray~~](index.md#-1215154575%2FFunctions%2F313640094)(p0: [IntFunction](https://developer.android.com/reference/kotlin/java/util/function/IntFunction.html)&lt;[Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[T](index.md#-1215154575%2FFunctions%2F313640094)&gt;&gt;): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[T](index.md#-1215154575%2FFunctions%2F313640094)&gt; |
