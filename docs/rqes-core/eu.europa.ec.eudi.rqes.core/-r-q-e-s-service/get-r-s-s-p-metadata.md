//[rqes-core](../../../index.md)/[eu.europa.ec.eudi.rqes.core](../index.md)/[RQESService](index.md)/[getRSSPMetadata](get-r-s-s-p-metadata.md)

# getRSSPMetadata

[androidJvm]\
abstract suspend fun [getRSSPMetadata](get-r-s-s-p-metadata.md)(): [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-result/index.html)&lt;RSSPMetadata&gt;

Retrieves the Remote Signature Service Provider (RSSP) metadata.

The metadata contains information about the RSSP service capabilities, supported algorithms, and other service-specific details.

#### Return

A [Result](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin-stdlib/kotlin/-result/index.html) containing RSSPMetadata if successful, or an error if the operation failed.

#### See also

| |
|---|
| RSSPMetadata |
