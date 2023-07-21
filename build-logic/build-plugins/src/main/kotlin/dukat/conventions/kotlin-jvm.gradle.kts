package dukat.conventions

//import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("dukat.conventions.base")
    kotlin("jvm")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

sourceSets {
    main {
        kotlin.setSrcDirs(listOf("src"))
        resources.setSrcDirs(listOf("resources"))
        java.setSrcDirs(emptyList<File>())
    }
    test {
        kotlin.setSrcDirs(listOf("test/src"))
        resources.setSrcDirs(listOf("test/data"))
        java.setSrcDirs(emptyList<File>())
    }
}


tasks.withType<KotlinCompile>().configureEach {
    compilerOptions {
//        jvmTarget.set(JvmTarget.JVM_1_8)
        allWarningsAsErrors.set(false)
        freeCompilerArgs.addAll(
//            "-Xuse-experimental=kotlin.Experimental",
        )
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}
