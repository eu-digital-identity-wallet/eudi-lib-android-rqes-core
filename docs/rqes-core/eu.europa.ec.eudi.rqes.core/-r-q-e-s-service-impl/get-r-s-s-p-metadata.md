//[rqes-core](../../../index.md)/[eu.europa.ec.eudi.rqes.core](../index.md)/[RQESServiceImpl](index.md)/[getRSSPMetadata](get-r-s-s-p-metadata.md)

# getRSSPMetadata

[androidJvm]\
open suspend override fun [getRSSPMetadata](get-r-s-s-p-metadata.md)(): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-result/index.html)&lt;RSSPMetadata&gt;

Retrieves metadata from the Remote Signature Service Provider (RSSP).

This method fetches information about the RSSP service capabilities, supported algorithms, identity, and other service-specific details. It's typically used as the initial step to discover service capabilities before starting the authorization workflow.

Implementation details:

1. 
   Ensures the CSC client is initialized via getOrCreateClient
2. 
   Retrieves the metadata from the RSSP
3. 
   Returns the metadata wrapped in a [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-result/index.html)

#### Return

A [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-result/index.html) containing the RSSPMetadata if successful,     or an error if the retrieval failed
