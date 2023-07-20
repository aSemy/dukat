import dukat.utils.excludeGeneratedGradleDsl

plugins {
    id("dukat.conventions.base")
    idea
}

group = "org.kotlin.dukat"
version = "0.6.0"


idea {
    module {
        excludeGeneratedGradleDsl(layout)

        excludeDirs.apply {
            // exclude .gradle, IDE dirs from nested projects (e.g. example & template projects)
            // so IntelliJ project-wide search isn't cluttered with irrelevant files
            val excludedDirs = setOf(
                ".idea",
                ".gradle",
                "build",
                "gradle/wrapper",
            )
            addAll(
                projectDir.walk()
                    .filter { it.isDirectory }
                    .filter { dir ->
                        excludedDirs.any {
                            dir.invariantSeparatorsPath.endsWith(it)
                        }
                    }
            )
        }
    }
}

//allprojects {
//  repositories {
//    gradlePluginPortal()
//    mavenCentral()
//    jcenter()
//    maven {
//      url "https://dl.bintray.com/kotlin/kotlin-eap"
//    }
//    maven {
//      url "https://cache-redirector.jetbrains.com/jcenter.bintray.com"
//    }
//  }
//
//  afterEvaluate { project ->
//    if (project.plugins.hasPlugin("kotlin")) {
//
//      test {
//        useJUnitPlatform()
//      }
//
//      project.sourceSets {
//        main.kotlin.srcDirs = ['src']
//
//        test.kotlin.srcDirs = ['test/src']
//        test.resources.srcDirs = ['test/data']
//      }
//
//      compileKotlin {
//        kotlinOptions {
//          jvmTarget = '1.8'
//          allWarningsAsErrors = true
//          freeCompilerArgs += "-Xuse-experimental=kotlin.Experimental"
//        }
//      }
//
//      compileTestKotlin {
//        kotlinOptions {
//          freeCompilerArgs += "-Xuse-experimental=kotlin.Experimental"
//        }
//      }
//
//      dependencies {
//        implementation("org.jetbrains.kotlin:kotlin-stdlib:${libs.versions.kotlin.get()}")
//      }
//    }
//
//    if (project.plugins.hasPlugin("java")) {
//      compileJava {
//        sourceCompatibility = '1.8'
//        targetCompatibility = '1.8'
//      }
//    }
//  }
//
//}
