rootProject.name = "build-plugins"

pluginManagement {
  repositories {
    gradlePluginPortal()
    mavenCentral()
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
    register("lib") {
      from(files("../../gradle/libs.versions.toml"))
    }
  }
}
