plugins {
    antlr
    id("dukat.conventions.kotlin-jvm")
}

dependencies {
    antlr("org.antlr:antlr4:${libs.versions.antlr4.get()}")

    implementation("org.antlr:antlr4-runtime:${libs.versions.antlr4.get()}")

    implementation(projects.dukatModules.idlDeclarations)
    implementation(projects.dukatModules.idlReferenceResolver)
    implementation(projects.dukatModules.astCommon)
}

sourceSets {
    main {
        kotlin.setSrcDirs(
            listOf(
                "src/main/kotlin",
                tasks.generateGrammarSource.map { it.outputDirectory },
            )
        )
        resources.setSrcDirs(listOf("src/main/resources"))
        antlr.setSrcDirs(listOf("src/main/antlr4"))
    }
    test {
        kotlin.setSrcDirs(listOf("src/test/kotlin"))
        resources.setSrcDirs(listOf("src/test/resources"))
        java.setSrcDirs(listOf("src/test/java"))
    }
}

tasks.generateGrammarSource {
    arguments = arguments + listOf(
        "-visitor",
        "-long-messages",
        "-package",
        "org.antlr.webidl",
    )
}
