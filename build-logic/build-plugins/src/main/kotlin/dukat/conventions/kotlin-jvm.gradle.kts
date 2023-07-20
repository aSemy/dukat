package dukat.conventions

//import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("dukat.conventions.base")
    kotlin("jvm")
}

kotlin {
    jvmToolchain(11)
}

sourceSets {
    main {
        kotlin.setSrcDirs(listOf("src"))
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
        allWarningsAsErrors.set(true)
        freeCompilerArgs.addAll(
            "-Xuse-experimental=kotlin.Experimental",
        )
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}
