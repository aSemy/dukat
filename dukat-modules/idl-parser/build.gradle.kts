plugins {
    antlr
    id("dukat.conventions.kotlin-jvm")
}

//sourceSets {
//    generated {
//        java.srcDir "${buildDir}/generated-src/antlr/generated"
//    }
//
//    main {
//        compileClasspath += generated.output
//        runtimeClasspath += generated.output
//    }
//
//    test {
//        compileClasspath += generated.output
//        runtimeClasspath += generated.output
//    }
//}

dependencies {
    antlr ("org.antlr:antlr4:${libs.versions.antlr4.get()}")

    implementation ("org.antlr:antlr4-runtime:${libs.versions.antlr4.get()}")

    implementation(projects.dukatModules.idlDeclarations)
    implementation(projects.dukatModules.idlReferenceResolver)
    implementation(projects.dukatModules.astCommon)

//    runtime files(project.sourceSets.generated.output.classesDirs)
}

//project.sourceSets.main.antlr.srcDirs = ["src/main/antlr4"]
//
//task createGeneratedSourcesDir {
//    doFirst {
//        mkdir "${buildDir}/classes/kotlin/generated"
//    }
//}
//
//compileGeneratedJava {
//    dependsOn(createGeneratedSourcesDir)
//    dependsOn(generateGrammarSource)
//    classpath = configurations.compile
//}
//
//compileKotlin {
//    source += sourceSets.generated.java
//}
//
//generateGrammarSource {
//    outputDirectory = file("${buildDir}/generated-src/antlr/generated")
//    arguments += ["-visitor", "-long-messages", "-package", "org.antlr.webidl"]
//}
