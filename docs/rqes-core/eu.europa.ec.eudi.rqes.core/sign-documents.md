//[rqes-core](../../index.md)/[eu.europa.ec.eudi.rqes.core](index.md)/[signDocuments](sign-documents.md)

# signDocuments

[androidJvm]\
suspend fun [RQESService.Authorized](-r-q-e-s-service/-authorized/index.md).[signDocuments](sign-documents.md)(authorizationCode: AuthorizationCode): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;[SignedDocuments](-signed-documents/index.md)&gt;

Sign the documents with the given authorization code.

Extension function for [RQESService.Authorized](-r-q-e-s-service/-authorized/index.md) that merges the authorization and signing of the documents.

#### Return

The signed documents

#### Parameters

androidJvm

| | |
|---|---|
| authorizationCode | The authorization code |
