//[rqes-core](../../../../index.md)/[eu.europa.ec.eudi.rqes.core](../../index.md)/[RQESService](../index.md)/[Authorized](index.md)/[listCredentials](list-credentials.md)

# listCredentials

[androidJvm]\
abstract suspend fun [listCredentials](list-credentials.md)(request: CredentialsListRequest? = null): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html)&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;CredentialInfo&gt;&gt;

List the credentials. This method is used to list the credentials. The credentials are the user's credentials that can be used to sign the documents.

Method accepts CredentialsListRequest as a parameter to filter the credentials. If the request is null, all the valid credentials should be returned.

#### Return

The list of credentials as a [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html) of [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html) of CredentialInfo.

#### Parameters

androidJvm

| | |
|---|---|
| request | The credentials list request. |
