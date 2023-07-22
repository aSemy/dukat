package dukat.conventions

import dukat.utils.asConsumer
import dukat.utils.asProvider
import dukat.utils.libs
import org.gradle.api.attributes.Usage.USAGE_ATTRIBUTE

plugins {
    id("dukat.conventions.base")
    id("com.github.node-gradle.node")
}

node {
    version.set(libs.versions.node)
}

fun AttributeContainer.tsModelElements() {
    attribute(USAGE_ATTRIBUTE, objects.named("dukat-ts-model-elements"))
}

val tsModelProtoElements by configurations.registering {
    asProvider()
    attributes { tsModelElements() }
}

val tsModelProtoFiles by configurations.registering {
    asConsumer()
    attributes { tsModelElements() }
}
