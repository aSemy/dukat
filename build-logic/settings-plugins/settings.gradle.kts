rootProject.name = "settings-plugins"

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)

    repositories {
        mavenCentral()
        gradlePluginPortal()
    }

    versionCatalogs {
        register("libs") {
            from(files("../../gradle/libs.versions.toml"))
        }
    }
}
