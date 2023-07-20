
plugins {
  id("dukat.conventions.base")
}

group = "org.kotlin.dukat"
version = "0.6.0"

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
//        implementation("org.jetbrains.kotlin:kotlin-stdlib:$gradle.kotlinVersion")
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
