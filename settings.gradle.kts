import dukat.settings.utils.*

rootProject.name = "dukat"

pluginManagement {
    includeBuild("build-logic/build-plugins")
    includeBuild("build-logic/settings-plugins")
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("dukat.settings.conventions.base")
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)

    repositories {
        mavenCentral()

        // Declare the Node.js & Yarn download repositories
        nodeJsDistribution()
        yarnDistribution()

        kotlinNativeBinaries()
        mavenCentralSnapshots()
        gitHubReleases()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
enableFeaturePreview("STABLE_CONFIGURATION_CACHE")

include(
    ":dukat-modules:abstract-graphs",
    ":dukat-modules:abstract-itertools",
    ":dukat-modules:abstract-toposort",

    ":dukat-modules:ast-common",
    ":dukat-modules:ast-model",

    ":dukat-modules:core-compiler",
    ":dukat-modules:core-descriptors",
    ":dukat-modules:core-descriptors-v1-4-0",
    ":dukat-modules:core-logging",
    ":dukat-modules:core-owner-context",
    ":dukat-modules:core-panic",
    ":dukat-modules:core-stdlib",
    ":dukat-modules:core-stdlib-generator",
    ":dukat-modules:core-translator",
    ":dukat-modules:core-translator-string",

    ":dukat-modules:dukat-cli",

    ":dukat-modules:idl-declarations",
    ":dukat-modules:idl-lowerings",
    ":dukat-modules:idl-models",
    ":dukat-modules:idl-parser",
    ":dukat-modules:idl-reference-resolver",

    ":dukat-modules:js-translator",
    ":dukat-modules:js-type-analysis",

    ":dukat-modules:model-lowerings",
    ":dukat-modules:model-lowerings-common",
    ":dukat-modules:model-serialization",

    ":dukat-modules:module-name-resolver",

    ":dukat-modules:node-distribution",
    ":dukat-modules:node-package",

    ":dukat-modules:proto-kotlin-model",

    ":dukat-modules:ts-ast-declarations",
    ":dukat-modules:ts-converter",
    ":dukat-modules:ts-lowerings",
    ":dukat-modules:ts-model",
    ":dukat-modules:ts-model-introduction",
    ":dukat-modules:ts-model-proto",
    ":dukat-modules:ts-nodes",
    ":dukat-modules:ts-translator",
)
