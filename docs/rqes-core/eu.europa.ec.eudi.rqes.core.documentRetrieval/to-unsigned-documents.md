//[rqes-core](../../index.md)/[eu.europa.ec.eudi.rqes.core.documentRetrieval](index.md)/[toUnsignedDocuments](to-unsigned-documents.md)

# toUnsignedDocuments

[androidJvm]\
fun [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;[ResolvedDocument](-resolved-document/index.md)&gt;.[toUnsignedDocuments](to-unsigned-documents.md)(signingConfig: [UnsignedDocument.Config](../eu.europa.ec.eudi.rqes.core/-unsigned-document/-config/index.md)? = null): [UnsignedDocuments](../eu.europa.ec.eudi.rqes.core/-unsigned-documents/index.md)

Convert a list of [ResolvedDocument](-resolved-document/index.md) to [UnsignedDocuments](../eu.europa.ec.eudi.rqes.core/-unsigned-documents/index.md).

Utility function to convert a list of [ResolvedDocument](-resolved-document/index.md) from [eu.europa.ec.eudi.rqes.core.documentRetrieval.ResolutionOutcome](-resolution-outcome/index.md) after [eu.europa.ec.eudi.rqes.core.documentRetrieval.DocumentRetrievalService.resolveDocument](-document-retrieval-service/resolve-document.md)

#### Parameters

androidJvm

| | |
|---|---|
| signingConfig | The configuration for the signing process. |
