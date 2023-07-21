package dukat.utils

import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.api.file.ProjectLayout
import org.gradle.kotlin.dsl.getByType
import org.gradle.plugins.ide.idea.model.IdeaModule

/** exclude generated Gradle code, so it doesn't clog up search results */
fun IdeaModule.excludeGeneratedGradleDsl(layout: ProjectLayout) {

    val generatedSrcDirs = listOf(
        "kotlin-dsl-accessors",
        "kotlin-dsl-external-plugin-spec-builders",
        "kotlin-dsl-plugins",
    )

    excludeDirs.addAll(
        layout.projectDirectory.asFile.walk()
            .filter { it.isDirectory }
            .filter { it.parentFile.name in generatedSrcDirs }
            .flatMap { file ->
                file.walk().maxDepth(1).filter { it.isDirectory }.toList()
            }
    )
}


/**
 * Access the version catalog.
 *
 * https://github.com/gradle/gradle/issues/15383#issuecomment-779893192
 */
internal val Project.libs: LibrariesForLibs
    get() = this.extensions.getByType()
