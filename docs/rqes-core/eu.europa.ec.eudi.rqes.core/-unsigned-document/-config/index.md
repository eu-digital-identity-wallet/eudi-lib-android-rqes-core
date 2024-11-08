//[rqes-core](../../../../index.md)/[eu.europa.ec.eudi.rqes.core](../../index.md)/[UnsignedDocument](../index.md)/[Config](index.md)

# Config

data class [Config](index.md)(val signatureFormat: SignatureFormat, val conformanceLevel: ConformanceLevel, val signingAlgorithm: SigningAlgorithmOID, val signedEnvelopeProperty: SignedEnvelopeProperty, val asicContainer: ASICContainer)

Configuration for the signing process.

#### Parameters

androidJvm

| | |
|---|---|
| signatureFormat | The signature format. |
| conformanceLevel | The conformance level. |
| signingAlgorithm | The signing algorithm. |
| signedEnvelopeProperty | The signed envelope property. |
| asicContainer | The ASiC container. |

## Constructors

| | |
|---|---|
| [Config](-config.md) | [androidJvm]<br>constructor(signatureFormat: SignatureFormat, conformanceLevel: ConformanceLevel, signingAlgorithm: SigningAlgorithmOID, signedEnvelopeProperty: SignedEnvelopeProperty, asicContainer: ASICContainer) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md)<br>Companion object for the [Config](index.md) class. |

## Properties

| Name | Summary |
|---|---|
| [asicContainer](asic-container.md) | [androidJvm]<br>val [asicContainer](asic-container.md): ASICContainer<br>The ASiC container. |
| [conformanceLevel](conformance-level.md) | [androidJvm]<br>val [conformanceLevel](conformance-level.md): ConformanceLevel<br>The conformance level. |
| [signatureFormat](signature-format.md) | [androidJvm]<br>val [signatureFormat](signature-format.md): SignatureFormat<br>The signature format. |
| [signedEnvelopeProperty](signed-envelope-property.md) | [androidJvm]<br>val [signedEnvelopeProperty](signed-envelope-property.md): SignedEnvelopeProperty<br>The signed envelope property. |
| [signingAlgorithm](signing-algorithm.md) | [androidJvm]<br>val [signingAlgorithm](signing-algorithm.md): SigningAlgorithmOID<br>The signing algorithm. |
