//[rqes-core](../../../index.md)/[eu.europa.ec.eudi.rqes.core.documentRetrieval](../index.md)/[DocumentRetrievalService](index.md)/[resolveDocument](resolve-document.md)

# resolveDocument

[androidJvm]\
abstract suspend fun [resolveDocument](resolve-document.md)(requestUri: [Uri](https://developer.android.com/reference/kotlin/android/net/Uri.html)): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-result/index.html)&lt;[ResolutionOutcome](../-resolution-outcome/index.md)&gt;

Retrieves the documents from the given URI.

#### Return

The outcome of the resolution.

#### Parameters

androidJvm

| | |
|---|---|
| requestUri | The URI of the document to be retrieved. |
