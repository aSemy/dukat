rootProject.name = "dukat"

pluginManagement {
    includeBuild("build-logic/build-plugins")
    repositories {
        mavenCentral()
        gradlePluginPortal()
//    jcenter()
//    maven("https://dl.bintray.com/kotlin/kotlin-eap")
//    maven("https://cache-redirector.jetbrains.com/jcenter.bintray.com")
    }

//  resolutionStrategy {
//    def kotlin_plugins =["kotlin2js", "kotlin", "kotlin-multiplatform"]
//
//    eachPlugin {
//      def pluginId = requested . id . id
//          if (pluginId in kotlin_plugins) {
//            useModule("org.jetbrains.kotlin:kotlin-gradle-plugin:${libs.versions.kotlin.get()}")
//          } else if (pluginId == "kotlinx-serialization") {
//            useModule("org.jetbrains.kotlin:kotlin-serialization:${libs.versions.kotlin.get()}")
//          } else if (pluginId == 'com.google.protobuf') {
//            useModule("com.google.protobuf:protobuf-gradle-plugin:${gradle.protobufGradlePluginVersion}")
//          }
//    }
//  }
}


@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)

    repositories {
        mavenCentral()

        // Declare the Node.js & Yarn download repositories
        exclusiveContent {
            forRepository {
                ivy("https://nodejs.org/dist/") {
                    name = "Node Distributions at $url"
                    patternLayout { artifact("v[revision]/[artifact](-v[revision]-[classifier]).[ext]") }
                    metadataSources { artifact() }
                    content { includeModule("org.nodejs", "node") }
                }
            }
            filter { includeGroup("org.nodejs") }
        }

        exclusiveContent {
            forRepository {
                ivy("https://github.com/yarnpkg/yarn/releases/download") {
                    name = "Yarn Distributions at $url"
                    patternLayout { artifact("v[revision]/[artifact](-v[revision]).[ext]") }
                    metadataSources { artifact() }
                    content { includeModule("com.yarnpkg", "yarn") }
                }
            }
            filter { includeGroup("com.yarnpkg") }
        }

        // workaround for https://youtrack.jetbrains.com/issue/KT-51379
        exclusiveContent {
            forRepository {
                ivy("https://download.jetbrains.com/kotlin/native/builds") {
                    name = "Kotlin Native"
                    patternLayout {

                        // example download URLs:
                        // https://download.jetbrains.com/kotlin/native/builds/releases/1.7.20/linux-x86_64/kotlin-native-prebuilt-linux-x86_64-1.7.20.tar.gz
                        // https://download.jetbrains.com/kotlin/native/builds/releases/1.7.20/windows-x86_64/kotlin-native-prebuilt-windows-x86_64-1.7.20.zip
                        // https://download.jetbrains.com/kotlin/native/builds/releases/1.7.20/macos-x86_64/kotlin-native-prebuilt-macos-x86_64-1.7.20.tar.gz
                        listOf(
                            "macos-x86_64",
                            "macos-aarch64",
                            "osx-x86_64",
                            "osx-aarch64",
                            "linux-x86_64",
                            "windows-x86_64"
                        ).forEach { os ->
                            listOf("dev", "releases").forEach { stage ->
                                artifact("$stage/[revision]/$os/[artifact]-[revision].[ext]")
                            }
                        }
                    }
                    metadataSources { artifact() }
                }
            }
            filter { includeModuleByRegex(".*", ".*kotlin-native-prebuilt.*") }
        }

        maven("https://s01.oss.sonatype.org/content/repositories/snapshots") {
            name = "SonatypeSnapshots"
            mavenContent {
                snapshotsOnly()
            }
        }
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
enableFeaturePreview("STABLE_CONFIGURATION_CACHE")


//def buildLog (message) {
//  println("[BUILD] ${message}")
//}

//gradle.ext {
//  try {
//    //TODO: for some reason hasProperty is not working for me
//    kotlinVersion = getProperty("dukatKotlinVersion")
//  } catch (e) {
//    kotlinVersion = "1.4.30"
//  }
//
//  jupiterVersion = "5.7.0"
//  nodeVersion = "18.12.1" // LTS
//  defaultTsVersion = "3.9.5"
//  defaultNpmPackageVersion = "0.5.8-rc.5"
//  kotlinSerializationVersion = "1.0.0-RC"
//  antlr4_version = "4.7.1"
//  protobufGradlePluginVersion = "0.8.13"
//  protobufImplementationVersion = "3.20.1"
//}
//buildLog("gradle settings properties: ${gradle.ext.properties}")

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
