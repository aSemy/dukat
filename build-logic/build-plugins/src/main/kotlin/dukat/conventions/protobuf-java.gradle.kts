package dukat.conventions

import dukat.utils.libs
import org.curioswitch.gradle.protobuf.tasks.ExtractProtosTask
import org.curioswitch.gradle.protobuf.tasks.GenerateProtoTask

plugins {
    id("dukat.conventions.base")
    id("org.curioswitch.gradle-protobuf-plugin")
    `java-library`
}

protobuf {
    descriptorSetOptions.path.convention(
        layout.buildDirectory.dir("protobuf/unused-descriptor-set").map { it.asFile }
    )

    protoc {
        artifact.set(libs.protobuf.protoc.map { it.toString() })
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

//sourceSets {
//    main {
//        resources.setSrcDirs(listOf("src/main/resources"))
//        java.setSrcDirs(
//            listOf(
//                "src/main/java",
//                tasks.generateGrammarSource.map { it.outputDirectory },
//            )
//        )
//        antlr.setSrcDirs(listOf("src/main/antlr4"))
//    }
//    test {
//        resources.setSrcDirs(listOf("src/test/resources"))
//        java.setSrcDirs(listOf("src/test/java"))
//    }
//}

tasks.withType<GenerateProtoTask>().configureEach {
    group = "protobuf"
}

tasks.withType<ExtractProtosTask>().configureEach {
    group = "protobuf"
}
