//[rqes-core](../../../../index.md)/[eu.europa.ec.eudi.rqes.core](../../index.md)/[UnsignedDocument](../index.md)/[Config](index.md)

# Config

[androidJvm]\
data class [Config](index.md)(val signatureFormat: SignatureFormat, val conformanceLevel: ConformanceLevel, val signedEnvelopeProperty: SignedEnvelopeProperty, val asicContainer: ASICContainer)

Configuration for the signing process.

This class defines the parameters that control how a document will be signed, including format, compliance level, and container specifications.

## Constructors

| | |
|---|---|
| [Config](-config.md) | [androidJvm]<br>constructor(signatureFormat: SignatureFormat, conformanceLevel: ConformanceLevel, signedEnvelopeProperty: SignedEnvelopeProperty, asicContainer: ASICContainer) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md)<br>Companion object for the [Config](index.md) class. |

## Properties

| Name | Summary |
|---|---|
| [asicContainer](asic-container.md) | [androidJvm]<br>val [asicContainer](asic-container.md): ASICContainer<br>The Associated Signature Container (ASiC) configuration. |
| [conformanceLevel](conformance-level.md) | [androidJvm]<br>val [conformanceLevel](conformance-level.md): ConformanceLevel<br>The conformance level to be applied to the signature. |
| [signatureFormat](signature-format.md) | [androidJvm]<br>val [signatureFormat](signature-format.md): SignatureFormat<br>The signature format to be used. |
| [signedEnvelopeProperty](signed-envelope-property.md) | [androidJvm]<br>val [signedEnvelopeProperty](signed-envelope-property.md): SignedEnvelopeProperty<br>The type of envelope property for the signature. |
