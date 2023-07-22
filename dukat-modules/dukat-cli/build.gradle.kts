import groovy.json.JsonSlurper

plugins {
    id("dukat.conventions.kotlin-jvm")
    kotlin("plugin.serialization")
    `maven-publish`
    application
}

//configurations {
//  cliRuntime
//}


dependencies {
    implementation(libs.kotlinxSerialization.core)

    implementation(projects.dukatModules.astCommon)
    implementation(projects.dukatModules.astModel)
//  implementation(project(path: ":compiler", configuration: "default"))
    implementation(projects.dukatModules.coreCompiler)
    implementation(projects.dukatModules.coreDescriptors)
    implementation(projects.dukatModules.idlReferenceResolver)
    implementation(projects.dukatModules.jsTranslator)
    implementation(projects.dukatModules.moduleNameResolver)
    implementation(projects.dukatModules.corePanic)
    implementation(projects.dukatModules.coreTranslator)
    implementation(projects.dukatModules.coreTranslatorString)
    implementation(projects.dukatModules.tsAstDeclarations)
    implementation(projects.dukatModules.tsModel)
    implementation(projects.dukatModules.tsTranslator)

//  cliRuntime(projects.dukatModules.coreCompiler)
}

//jar {
//  archiveName = "dukat-cli.jar"
//  manifest {
//    attributes 'Main-Class': 'org.jetbrains.dukat.cli.CliKt'
//  }
//
//  from {
//    configurations.cliRuntime.collect { it.isDirectory() ? it : zipTree(it) }
//  }
//}

//def configFile = file ("${project(":node-package").projectDir}/package.template.json")
//def json = new JsonSlurper().parseText(configFile.text)
//
//
//// TODO: Duplication of logic in node-package/build gradle
//def PROPERTY_PACKAGE = "dukat.npm.packageVersion"
//def PACKAGE_VERSION = hasProperty (PROPERTY_PACKAGE) ? getProperty(PROPERTY_PACKAGE)
//    .replace("__DEFAULT__", gradle.defaultNpmPackageVersion)
//    .replace("__TIMESTAMP__", new Date ().format("yyyyMMdd")) : gradle.defaultNpmPackageVersion
//
//def dukatVersion = json . version == "__PACKAGE_VERSION__" ? PACKAGE_VERSION : json.version

//publishing {
//    publications {
//        mavenJava(MavenPublication) {
//            groupId "org.jetbrains.dukat"
//            artifactId "dukat"
//            version dukatVersion
//                    artifact jar
//        }
//    }
//    repositories {
//        maven {
//            name = "kotlinSpace"
//            url = uri "https://maven.pkg.jetbrains.space/kotlin/p/kotlin/kotlin-dependencies"
//            credentials PasswordCredentials
//        }
//    }
//}
