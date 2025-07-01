//[rqes-core](../../../../index.md)/[eu.europa.ec.eudi.rqes.core](../../index.md)/[RQESService](../index.md)/[Authorized](index.md)/[listCredentials](list-credentials.md)

# listCredentials

[androidJvm]\
abstract suspend fun [listCredentials](list-credentials.md)(request: CredentialsListRequest? = null): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-result/index.html)&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;CredentialInfo&gt;&gt;

Retrieves a list of available signing credentials.

Returns all credentials that can be used for document signing, optionally filtered by the provided request parameters.

#### Return

A [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-result/index.html) containing a list of CredentialInfo objects if successful,     or an error if the operation failed.

#### Parameters

androidJvm

| | |
|---|---|
| request | Optional filter criteria for the credentials list. If null, all valid     credentials will be returned. |
