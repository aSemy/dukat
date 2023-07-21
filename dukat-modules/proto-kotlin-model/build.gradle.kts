plugins {
    id("dukat.conventions.kotlin-jvm")
    id("dukat.conventions.protobuf-java")
}

val targetMainDir = "${project.buildDir}/generated/source/proto/main"

dependencies {
    api(libs.protobuf.java)
//    compileOnly(projects.dukatModules.tsModelProto)

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

    protobuf(projects.dukatModules.tsModelProto)
}

sourceSets {
    main {
        kotlin.setSrcDirs(listOf("src/main/kotlin"))
        resources.setSrcDirs(listOf("src/main/resources"))
        java.setSrcDirs(
            listOf(
                "src/main/java",
                tasks.generateProto.flatMap { it.descriptorOutputPath },
                tasks.generateProto.map { it.outputDirs.values },
//                tasks.generateGrammarSource.map { it.outputDirectory },
            )
        )
    }
    test {
        kotlin.setSrcDirs(listOf("src/test/kotlin"))
        resources.setSrcDirs(listOf("src/test/resources"))
        java.setSrcDirs(listOf("src/test/java"))
    }
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
