package dukat.conventions

import dukat.utils.asConsumer
import dukat.utils.dropDirectories
import org.gradle.api.attributes.Usage.USAGE_ATTRIBUTE
import org.gradle.kotlin.dsl.support.serviceOf

plugins {
    id("dukat.conventions.base")
}

val definitelyTypedSource by configurations.creating<Configuration> {
    asConsumer()
    attributes {
        attribute(USAGE_ATTRIBUTE, objects.named("externals-definitely-typed-src"))
    }
}

val prepareDefinitelyTypedSource by tasks.registering(Sync::class) {
    group = project.name
    description = "Download & unpack DefinitelyTyped source code"

    val archives = serviceOf<ArchiveOperations>()

    from(
        definitelyTypedSource.incoming
            .artifacts
            .resolvedArtifacts
            .map { artifacts ->
                artifacts.map { archives.zipTree(it.file) }
            }
    ) {
        eachFile { relativePath = relativePath.dropDirectories(1) }
    }

    into(temporaryDir)
}
