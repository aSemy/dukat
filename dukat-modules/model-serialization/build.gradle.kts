plugins {
    id("dukat.conventions.kotlin-jvm")
}

dependencies {
    implementation("com.google.protobuf:protobuf-java:${libs.versions.protobufImplementation.get()}")
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

//
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
