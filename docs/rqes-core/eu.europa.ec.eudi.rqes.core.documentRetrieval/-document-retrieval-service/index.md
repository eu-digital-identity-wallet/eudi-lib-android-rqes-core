//[rqes-core](../../../index.md)/[eu.europa.ec.eudi.rqes.core.documentRetrieval](../index.md)/[DocumentRetrievalService](index.md)

# DocumentRetrievalService

interface [DocumentRetrievalService](index.md)

Retrieves documents from the given URI. The documents are then are to be signed using [eu.europa.ec.eudi.rqes.core.RQESService](../../eu.europa.ec.eudi.rqes.core/-r-q-e-s-service/index.md). The signed documents are then dispatched through [ResolutionOutcome.dispatch](../-resolution-outcome/dispatch.md).

#### Inheritors

| |
|---|
| [DocumentRetrievalServiceImpl](../-document-retrieval-service-impl/index.md) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md)<br>Companion object to create instances of [DocumentRetrievalService](index.md). |

## Functions

| Name | Summary |
|---|---|
| [resolveDocument](resolve-document.md) | [androidJvm]<br>abstract suspend fun [resolveDocument](resolve-document.md)(requestUri: [Uri](https://developer.android.com/reference/kotlin/android/net/Uri.html)): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-result/index.html)&lt;[ResolutionOutcome](../-resolution-outcome/index.md)&gt;<br>Retrieves the documents from the given URI. |
