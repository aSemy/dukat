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
//            useModule("org.jetbrains.kotlin:kotlin-gradle-plugin:${gradle.kotlinVersion}")
//          } else if (pluginId == "kotlinx-serialization") {
//            useModule("org.jetbrains.kotlin:kotlin-serialization:${gradle.kotlinVersion}")
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
//  ":ast-common",
//  ":ast-model",
//  ":command-line",
//  ":compiler",
//  ":descriptors",
//  ":version-specific:descriptors-1.4.0",
//  ":idl:idl-declarations",
//  ":idl:idl-lowerings",
//  ":idl:idl-models",
//  ":idl:idl-parser",
//  ":idl:idl-reference-resolver",
//  ":abstract:itertools",
//  ":js-module-statistics",
//  ":javascript:js-translator",
//  ":javascript:js-type-analysis",
//  ":proto:kotlin-model-proto",
//  ":logging",
//  ":model-lowerings",
//  ":model-lowerings-common",
//  ":model-serialization",
//  ":module-name-resolver",
//  ":node-distrib",
//  ":node-package",
//  ":ownerContext",
//  ":panic",
//  ":stdlib",
//  ":stdlib-generator",
//  ":abstract:toposort",
//  ":translator",
//  ":translator-string",
//  ":typescript:ts-ast-declarations",
//  ":typescript:ts-converter",
//  ":typescript:ts-lowerings",
//  ":typescript:ts-model",
//  ":typescript:ts-model-introduction",
//  ":typescript:ts-model-proto",
//  ":typescript:ts-nodes",
//  ":typescript:ts-translator",
)

//project(':descriptors-1.4.0').projectDir = file("$rootDir/version-specific/descriptors-1.4.0")
//
//project(':graphs').projectDir = file("$rootDir/abstract/graphs")
//project(':itertools').projectDir = file("$rootDir/abstract/itertools")
//project(':toposort').projectDir = file("$rootDir/abstract/toposort")
//
//project(':idl-declarations').projectDir = file("$rootDir/idl/idl-declarations")
//project(':idl-lowerings').projectDir = file("$rootDir/idl/idl-lowerings")
//project(':idl-models').projectDir = file("$rootDir/idl/idl-models")
//project(':idl-parser').projectDir = file("$rootDir/idl/idl-parser")
//project(':idl-reference-resolver').projectDir = file("$rootDir/idl/idl-reference-resolver")
//
//project(':js-translator').projectDir = file("$rootDir/javascript/js-translator")
//project(':js-type-analysis').projectDir = file("$rootDir/javascript/js-type-analysis")
//
//project(':kotlin-model-proto').projectDir = file("$rootDir/proto/kotlin-model-proto")
//
//project(':ts-ast-declarations').projectDir = file("$rootDir/typescript/ts-ast-declarations")
//project(':ts-converter').projectDir = file("$rootDir/typescript/ts-converter")
//project(':ts-lowerings').projectDir = file("$rootDir/typescript/ts-lowerings")
//project(':ts-model').projectDir = file("$rootDir/typescript/ts-model")
//project(':ts-model-proto').projectDir = file("$rootDir/typescript/ts-model-proto")
//project(':ts-model-introduction').projectDir = file("$rootDir/typescript/ts-model-introduction")
//project(':ts-translator').projectDir = file("$rootDir/typescript/ts-translator")
//project(':ts-nodes').projectDir = file("$rootDir/typescript/ts-nodes")
