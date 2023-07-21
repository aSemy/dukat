plugins {
    id("dukat.conventions.kotlin-jvm")
}

//configurations {
//    kotlinStdLib
//}

dependencies {
    implementation(projects.dukatModules.astCommon)
    implementation(projects.dukatModules.astModel)
    implementation(projects.dukatModules.coreTranslator)
    implementation(projects.dukatModules.coreTranslatorString)
    implementation(projects.dukatModules.coreStdlib)
    implementation("org.jetbrains.kotlin:kotlin-compiler-embeddable:${libs.versions.kotlin.get()}")
    implementation("com.squareup:kotlinpoet:1.14.2")
//    implementation("com.squareup:kotlinpoet:1.6.0") {
//        exclude group: "org.jetbrains.kotlin", module: "kotlin-stdlib-jdk7"
//        exclude group: "org.jetbrains.kotlin", module: "kotlin-stdlib-jdk8"
//    }

//    kotlinStdLib("org.jetbrains.kotlin:kotlin-stdlib-js:${libs.versions.kotlin.get()}")
}

//task buildDistrib {
//    outputs.file("${project.buildDir}/libs/kotlin-stdlib-js.jar")
//    doLast {
//        copy {
//            from configurations.kotlinStdLib
//            into "${project.buildDir}/libs"
//            rename "kotlin-stdlib-js-${libs.versions.kotlin.get()}.jar", "kotlin-stdlib-js.jar"
//        }
//    }
//}
//
//build.dependsOn = [buildDistrib]
