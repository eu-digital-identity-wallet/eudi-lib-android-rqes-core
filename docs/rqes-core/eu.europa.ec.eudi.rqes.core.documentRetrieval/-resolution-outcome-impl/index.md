//[rqes-core](../../../index.md)/[eu.europa.ec.eudi.rqes.core.documentRetrieval](../index.md)/[ResolutionOutcomeImpl](index.md)

# ResolutionOutcomeImpl

class [ResolutionOutcomeImpl](index.md)(val resolvedDocuments: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[ResolvedDocument](../-resolved-document/index.md)&gt;, requestObject: ResolvedRequestObject, client: DocumentRetrieval) : [ResolutionOutcome](../-resolution-outcome/index.md)

Implementation of the ResolutionOutcome interface.

#### Parameters

androidJvm

| | |
|---|---|
| resolvedDocuments | the list of resolved documents |
| requestObject | the resolved request object |
| client | the Document Retrieval client |

## Constructors

| | |
|---|---|
| [ResolutionOutcomeImpl](-resolution-outcome-impl.md) | [androidJvm]<br>constructor(resolvedDocuments: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[ResolvedDocument](../-resolved-document/index.md)&gt;, requestObject: ResolvedRequestObject, client: DocumentRetrieval)<br>Creates ResolutionOutcomeImpl with the given parameters. |

## Properties

| Name | Summary |
|---|---|
| [resolvedDocuments](resolved-documents.md) | [androidJvm]<br>open override val [resolvedDocuments](resolved-documents.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[ResolvedDocument](../-resolved-document/index.md)&gt;<br>the list of resolved documents |

## Functions

| Name | Summary |
|---|---|
| [dispatch](dispatch.md) | [androidJvm]<br>open suspend override fun [dispatch](dispatch.md)(signedDocuments: [SignedDocuments](../../eu.europa.ec.eudi.rqes.core/-signed-documents/index.md)): DispatchOutcome<br>Dispatches the signed documents to the Document Retrieval service. |
