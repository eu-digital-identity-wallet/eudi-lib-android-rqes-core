//[rqes-core](../../../index.md)/[eu.europa.ec.eudi.rqes.core.documentRetrieval](../index.md)/[ResolutionOutcomeImpl](index.md)/[dispatch](dispatch.md)

# dispatch

[androidJvm]\
open suspend override fun [dispatch](dispatch.md)(signedDocuments: [SignedDocuments](../../eu.europa.ec.eudi.rqes.core/-signed-documents/index.md)): DispatchOutcome

Dispatches the signed documents to the Document Retrieval service.

This method converts each signed document to a string representation by reading its bytes and then dispatches them to the Document Retrieval service with a positive consensus.

#### Return

the outcome of the dispatch operation as DispatchOutcome

#### Parameters

androidJvm

| | |
|---|---|
| signedDocuments | the signed documents to be dispatched |
