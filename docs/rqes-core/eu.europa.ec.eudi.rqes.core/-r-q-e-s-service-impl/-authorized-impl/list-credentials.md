//[rqes-core](../../../../index.md)/[eu.europa.ec.eudi.rqes.core](../../index.md)/[RQESServiceImpl](../index.md)/[AuthorizedImpl](index.md)/[listCredentials](list-credentials.md)

# listCredentials

[androidJvm]\
open suspend override fun [listCredentials](list-credentials.md)(request: CredentialsListRequest?): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-result/index.html)&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin.collections/-list/index.html)&lt;CredentialInfo&gt;&gt;

Retrieves the list of available credentials for the authorized user.

This method queries the RSSP for credentials associated with the authorized user that can be used for document signing. By default, only valid credentials are returned if no specific request criteria are provided.

Implementation workflow:

1. 
   Uses the authorized service access to list credentials from the RSSP
2. 
   Applies any filtering criteria specified in the request
3. 
   Returns the list of matching credentials

#### Return

A [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-result/index.html) containing a list of CredentialInfo objects if successful,     or an error if the retrieval failed

#### Parameters

androidJvm

| | |
|---|---|
| request | Optional filter criteria for the credentials list. If null, all valid     credentials will be returned. |
