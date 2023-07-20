plugins {
    java
    id("dukat.conventions.kotlin-jvm")
    id( "com.google.protobuf") version "0.9.4"
}

val targetMainDir = "${project.buildDir}/generated/source/proto/main"

dependencies {
    implementation("com.google.protobuf:protobuf-java:${libs.versions.protobufImplementation.get()}")
    compileOnly(projects.dukatModules.tsModelProto)

    testImplementation(projects.dukatModules.astModel)
    testImplementation(projects.dukatModules.astCommon)
    testImplementation(projects.dukatModules.coreTranslator)
    testImplementation(projects.dukatModules.coreTranslatorString)
    testImplementation(projects.dukatModules.coreStdlibGenerator)
    testImplementation("org.jetbrains.kotlin:kotlin-test-common")
    testImplementation("org.jetbrains.kotlin:kotlin-test-annotations-common")
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // without this dependency one won't see "Click to see difference" in IDEA
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")

    testImplementation("org.junit.jupiter:junit-jupiter-params:${libs.versions.junitJupiter.get()}")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${libs.versions.junitJupiter.get()}")
}

//sourceSets {
//    generated {
//        java.srcDir("$targetMainDir/java")
//    }
//
//    main {
//        proto {
//            srcDirs = ["src"]
//        }
//
//        java {
//            srcDirs = ["$targetMainDir/java"]
//        }
//    }
//}
//
//protobuf {
//    protoc {
//        artifact = "com.google.protobuf:protoc:${libs.versions.protobufImplementation.get()}"
//    }
//}
