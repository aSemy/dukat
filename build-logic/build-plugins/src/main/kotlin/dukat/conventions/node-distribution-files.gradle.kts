package dukat.conventions

import dukat.utils.asConsumer
import dukat.utils.asProvider

plugins {
    id("dukat.conventions.base")
}

val nodeDistributionAttribute: Attribute<Usage> =
    Attribute.of("dukat.nodeDistribution", Usage::class.java)

val nodeDistribution by configurations.registering {
    asConsumer()
    attributes {
        attribute(nodeDistributionAttribute, objects.named("dukat.nodeDistribution"))
    }
}

val nodeDistributionElements by configurations.registering {
    asProvider()
    attributes {
        attribute(nodeDistributionAttribute, objects.named("dukat.nodeDistribution"))
    }
}
