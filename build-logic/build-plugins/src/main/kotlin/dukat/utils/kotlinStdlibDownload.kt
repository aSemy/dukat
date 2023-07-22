package dukat.utils

import org.gradle.api.Project
import org.gradle.api.artifacts.component.ModuleComponentIdentifier
import org.gradle.api.provider.Provider
import java.io.File

fun Project.kotlinStdlibJsJarProvider(
    kotlinVersion: Provider<String> = libs.versions.kotlin
): Provider<File> {
    val kotlinStdJsCollector = configurations.detachedConfiguration().apply {
        asConsumer()
        withDependencies {
            addLater(
                kotlinVersion.map { version ->
                    project.dependencies.create("org.jetbrains.kotlin:kotlin-stdlib-js:$version")
                }
            )
        }
    }

    return kotlinStdJsCollector
        .incoming
        .artifactView {
            componentFilter { id ->
                id is ModuleComponentIdentifier && id.module == "kotlin-stdlib-js"
            }
        }.artifacts
        .resolvedArtifacts
        .map { artifacts ->
            artifacts
                .map { it.file }
                .firstOrNull()
                ?: error("could not fetch kotlin-stdlib-js")
        }
}
