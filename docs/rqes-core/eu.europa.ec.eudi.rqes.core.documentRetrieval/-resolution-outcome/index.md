//[rqes-core](../../../index.md)/[eu.europa.ec.eudi.rqes.core.documentRetrieval](../index.md)/[ResolutionOutcome](index.md)

# ResolutionOutcome

interface [ResolutionOutcome](index.md)

The outcome of the resolution of the document retrieval.

#### Inheritors

| |
|---|
| [ResolutionOutcomeImpl](../-resolution-outcome-impl/index.md) |

## Properties

| Name | Summary |
|---|---|
| [resolvedDocuments](resolved-documents.md) | [androidJvm]<br>abstract val [resolvedDocuments](resolved-documents.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[ResolvedDocument](../-resolved-document/index.md)&gt;<br>The resolved documents. |

## Functions

| Name | Summary |
|---|---|
| [dispatch](dispatch.md) | [androidJvm]<br>abstract suspend fun [dispatch](dispatch.md)(signedDocuments: [SignedDocuments](../../eu.europa.ec.eudi.rqes.core/-signed-documents/index.md)): DispatchOutcome<br>Dispatches the signed documents. |
