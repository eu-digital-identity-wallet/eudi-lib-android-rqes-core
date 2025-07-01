//[rqes-core](../../index.md)/[eu.europa.ec.eudi.rqes.core.documentRetrieval](index.md)/[toUnsignedDocument](to-unsigned-document.md)

# toUnsignedDocument

[androidJvm]\
fun [ResolvedDocument](-resolved-document/index.md).[toUnsignedDocument](to-unsigned-document.md)(label: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-string/index.html), signingConfig: [UnsignedDocument.Config](../eu.europa.ec.eudi.rqes.core/-unsigned-document/-config/index.md)? = null): [UnsignedDocument](../eu.europa.ec.eudi.rqes.core/-unsigned-document/index.md)

Convert a [ResolvedDocument](-resolved-document/index.md) to an [UnsignedDocument](../eu.europa.ec.eudi.rqes.core/-unsigned-document/index.md).

Utility function to convert a [ResolvedDocument](-resolved-document/index.md) from [eu.europa.ec.eudi.rqes.core.documentRetrieval.ResolutionOutcome](-resolution-outcome/index.md) after [eu.europa.ec.eudi.rqes.core.documentRetrieval.DocumentRetrievalService.resolveDocument](-document-retrieval-service/resolve-document.md)

#### Return

The [UnsignedDocument](../eu.europa.ec.eudi.rqes.core/-unsigned-document/index.md)

#### Parameters

androidJvm

| | |
|---|---|
| label | The label of the document. |
| signingConfig | The configuration for the signing process. |
