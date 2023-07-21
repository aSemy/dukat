import dukat.utils.asConsumer
import org.gradle.kotlin.dsl.support.serviceOf

plugins {
    id("dukat.conventions.kotlin-jvm")
}

dependencies {
    implementation(projects.dukatModules.tsModelProto)
    implementation(projects.dukatModules.protoKotlinModel)
    implementation(projects.dukatModules.coreStdlibGenerator)

    implementation(projects.dukatModules.astModel)
    implementation(projects.dukatModules.astCommon)

    testImplementation(projects.dukatModules.coreTranslator)
    testImplementation(projects.dukatModules.coreTranslatorString)
    testImplementation("org.jetbrains.kotlin:kotlin-test-common")
    testImplementation("org.jetbrains.kotlin:kotlin-test-annotations-common")
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // without this dependency one won't see "Click to see difference" in IDEA
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")

    testImplementation("org.junit.jupiter:junit-jupiter-params:${libs.versions.junitJupiter.get()}")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${libs.versions.junitJupiter.get()}")
}

val kotlinStdJsCollector by configurations.registering {
    asConsumer()
    withDependencies {
        add(
            project.dependencies.create(
                "org.jetbrains.kotlin:kotlin-stdlib-js:${libs.versions.kotlin.get()}"
            )
        )
    }
}

val kotlinStdlibJsJar: Provider<File> =
    kotlinStdJsCollector.map { deps ->
        deps.incoming
            .artifactView {
                componentFilter { id ->
                    id is ModuleComponentIdentifier && id.module == "kotlin-stdlib-js"
                }
            }
            .artifacts
            .artifactFiles
            .singleFile
    }

val serializeStdLib by tasks.registering(JavaExec::class) {
    description = "Serialize and save Kotlin stdlib"
    group = project.name

    mainClass.set("org.jetbrains.dukat.model.serialization.StdlibSerializerKt")

    classpath(configurations.runtimeClasspath)
    classpath(tasks.jar)

    val kotlinStdlibJsJar = kotlinStdlibJsJar
    inputs.file(kotlinStdlibJsJar).withPropertyName("kotlinStdlibJsJar")

    val outputBinary = layout.buildDirectory.dir("stdlib.dukat").get().asFile.invariantSeparatorsPath
    outputs.file(outputBinary).withPropertyName("outputBinary")

    val kotlinStdlibJsJarFileName = "kotlin-stdlib-js.jar"

    args(
        "$temporaryDir/$kotlinStdlibJsJarFileName",
        outputBinary,
    )

    val fs = serviceOf<FileSystemOperations>()

    doFirst {
        fs.sync {
            from(kotlinStdlibJsJar) {
                rename { kotlinStdlibJsJarFileName }
            }
            into(temporaryDir)
        }
    }
}

tasks.withType<Test>().configureEach {
    val kotlinStdlibJsJar = kotlinStdlibJsJar
    inputs.file(kotlinStdlibJsJar).withPropertyName("kotlinStdlibJsJar")

    inputs.file("./test/resources/code.out.kt")

    dependsOn(serializeStdLib)

    doFirst {
        environment("kotlinStdlibJsJarPath", kotlinStdlibJsJar.get().invariantSeparatorsPath)
    }
}

//task serializeStdLib(type: JavaExec) {
//    dependsOn = [":stdlib-generator:buildDistrib"]
//
//    def inputJar = project(":stdlib-generator").getTasksByName("buildDistrib", true).outputs.files.singleFile[0]
//    inputs.file(inputJar)
//
//    def outputBinary = "${project.buildDir}/stdlib.dukat"
//    outputs.file(outputBinary)
//
//    group = "Execution"
//    description = "Serialize and save kotlin stdlib"
//    classpath = sourceSets.main.runtimeClasspath
//    main = "org.jetbrains.dukat.model.serialization.StdlibSerializerKt"
//    args  = [inputJar, outputBinary]
//}
//
//test.dependsOn(serializeStdLib)
//test.inputs.file("./test/resources/code.out.kt")
