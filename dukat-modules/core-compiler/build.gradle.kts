import dukat.utils.kotlinStdlibJsJarProvider

plugins {
    id("dukat.conventions.kotlin-jvm")
    id("dukat.conventions.node")
    id("dukat.conventions.download-definitely-typed")
    kotlin("plugin.serialization")
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9")

    implementation(projects.dukatModules.astCommon)
    implementation(projects.dukatModules.astModel)
    implementation(projects.dukatModules.coreDescriptors)
    implementation(projects.dukatModules.idlDeclarations)
    implementation(projects.dukatModules.idlLowerings)
    implementation(projects.dukatModules.idlModels)
    implementation(projects.dukatModules.idlParser)
    implementation(projects.dukatModules.idlReferenceResolver)
    implementation(projects.dukatModules.abstractItertools)
    implementation(projects.dukatModules.jsTypeAnalysis)
    implementation(projects.dukatModules.jsTranslator)
    implementation(projects.dukatModules.coreLogging)
    implementation(projects.dukatModules.modelLowerings)
    implementation(projects.dukatModules.modelLoweringsCommon)
    implementation(projects.dukatModules.moduleNameResolver)
    implementation(projects.dukatModules.coreOwnerContext)
    implementation(projects.dukatModules.corePanic)
    implementation(projects.dukatModules.coreTranslator)
    implementation(projects.dukatModules.coreTranslatorString)
    implementation(projects.dukatModules.tsAstDeclarations)
    implementation(projects.dukatModules.tsLowerings)
    implementation(projects.dukatModules.tsModel)
    implementation(projects.dukatModules.tsModelIntroduction)
    implementation(projects.dukatModules.tsTranslator)

    implementation("org.jetbrains.kotlin:kotlin-reflect")

    testImplementation("org.jetbrains.kotlin:kotlin-test-common")
    testImplementation("org.jetbrains.kotlin:kotlin-test-annotations-common")
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // without this dependency one won't see "Click to see difference" in IDEA
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")

    testImplementation("org.junit.jupiter:junit-jupiter-params:${libs.versions.junitJupiter.get()}")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${libs.versions.junitJupiter.get()}")

    testImplementation("org.jetbrains.kotlin:kotlin-gradle-plugin")
    implementation("org.jetbrains.kotlin:kotlin-compiler-embeddable")

    testImplementation(libs.kotlinxSerialization.core)

//    compile(project(path: ":ts-converter", configuration: 'dukatTsResources'))

    definitelyTypedSource("DefinitelyTyped:DefinitelyTyped:31929c09c7b4490f87766206b412da4a6a581dc3@zip")
}

//def getTsDependency() {
//    return zipTree(project(":ts-converter").getTasksByName("createJar", true).archivePath[0])
//}
//
//processResources {
//    dependsOn = [":ts-converter:createJar"]
//    from(getTsDependency()) {
//        into "/"
//    }
//}
//
//task propagateToIdeaRuntime(type: Copy) {
//    dependsOn = [":ts-converter:createJar"]
//
//    from(getTsDependency())
//    into "out/production/resources/"
//}
//
//compileTestKotlin.dependsOn = ["propagateToIdeaRuntime"]
//
//task download(type: Copy) {
//    from configurations.toDownload
//    into "${project.buildDir}/lib"
//    rename "kotlin-stdlib-js-${libs.versions.kotlin.get()}.jar", "kotlin-stdlib-js.jar"
//}
//
//
//task fetchDefinitelyTyped(dependsOn: downloadDefinitelyTyped, type: Copy) {
//    onlyIf {
//        project.hasProperty("dukat.test.extended")
//    }
//    from zipTree(downloadDefinitelyTyped.dest)
//    into "${rootProject.gradle.gradleUserHomeDir}/definitelyTyped"
//}
//
//task extractKoltinJsLibs(type: Copy) {
//    configurations.kotlinJsLibs.each {
//        from it.absolutePath
//    }
//    into "${project.buildDir}/kotlinHome"
//    rename { String fileName ->
//        return fileName.replace("-${libs.versions.kotlin.get()}.jar", ".jar")
//    }
//}
//
//task prepareTopNPackage(type: Copy) {
//    from "${TOPN_DIR}/package.json"
//    into TOPN_TARGET_DIR
//
//    from "${TOPN_DIR}/package-lock.json"
//    into TOPN_TARGET_DIR
//}
//
//task npmLsConfig {
//    doLast {
//        npmLs.commandLine project(":node-distrib").config.nodePath, project(":node-distrib").config.npmPath, "ls"
//    }
//}
//
//task npmLs(dependsOn: [npmLsConfig], type: Exec) {
//    workingDir TOPN_TARGET_DIR
//}
//
//task installTopNConfig {
//    doLast {
//        installTopN.commandLine project(":node-distrib").config.nodePath, project(":node-distrib").config.npmPath, "ci"
//    }
//}
//task installTopN(dependsOn: [prepareTopNPackage, installTopNConfig], type: Exec) {
//    workingDir TOPN_TARGET_DIR
//    finalizedBy npmLs
//}
//
//task prebuild {
//    dependsOn = ["testClasses", ":node-package:buildDistrib", compileKotlin]
//}
//
//test.dependsOn = [
//        download,
//        fetchDefinitelyTyped,
//        installTopN,
//        extractKoltinJsLibs,
//        ":node-package:nodeEnv",
//        ":node-package:buildDistrib"
//]

val definitelyTypedVersion = "31929c09c7b4490f87766206b412da4a6a581dc3"
//val definitelyTypedDir = "${rootProject.gradle.gradleUserHomeDir}/definitelyTyped/DefinitelyTyped-${definitelyTypedVersion}/types"

val topNDataDir = "${project.projectDir}/test/data/topN"
val topNTargetDir = "${project.buildDir}/tests/topN"

val kotlinStdlibJsJar: Provider<File> = kotlinStdlibJsJarProvider()

val extractKotlinJsLib by tasks.registering(Sync::class) {
    group = project.name
    from(kotlinStdlibJsJar) {
        rename { "kotlin-stdlib-js.jar" }
    }
    into(layout.buildDirectory.dir("kotlinHome"))
}

tasks.test {
    dependsOn(extractKotlinJsLib)
    inputs.file(extractKotlinJsLib)

    listOf(
        "dukat.test.extended",
        "dukat.test.extended.topn",
        "dukat.test.cli",
        "dukat.test.failure.always",
        "dukat.test.emitTsDiagnostics",
        "dukat.test.descriptorCompilation",
        "dukat.test.typescriptDukat"
    ).forEach { prop ->
        systemProperty(prop, project.hasProperty(prop))
    }

    val definitelyTypedDir = tasks.prepareDefinitelyTypedSource.map { it.destinationDir }
    inputs.dir(definitelyTypedDir).withPropertyName("definitelyTypedDir")
    doFirst {
        systemProperty("dukat.test.resources.definitelyTyped", definitelyTypedDir.get().invariantSeparatorsPath)
    }
    systemProperty("dukat.test.resources.topN", topNTargetDir)

    // execute top-level classes in sequentially but their methods in parallel
    // see https://junit.org/junit5/docs/5.5.1/user-guide/index.html#writing-tests-parallel-execution
//    systemProperty("junit.jupiter.execution.parallel.mode.default", "concurrent")
//    systemProperty("junit.jupiter.execution.parallel.mode.classes.default", "concurrent")

    testLogging {
        showStandardStreams = true
    }

    if (project.hasProperty("coreTests")) {
        exclude("**/CoreSetCliTests*")
    }
}

//test {
//    [
//            "dukat.test.extended",
//            "dukat.test.extended.topn",
//            "dukat.test.cli",
//            "dukat.test.failure.always",
//            "dukat.test.emitTsDiagnostics",
//            "dukat.test.descriptorCompilation",
//            "dukat.test.typescriptDukat"
//    ].forEach { String projectProperty ->
//        if (project.hasProperty(projectProperty)) {
//            systemProperty(projectProperty, "true")
//        }
//    }
//
//    [
//            "org.slf4j.simpleLogger.defaultLogLevel",
//            "dukat.test.definitelyTyped.repexp",
//    ].forEach { String projectProperty ->
//        if (project.hasProperty(projectProperty)) {
//            systemProperty(projectProperty, project.getProperty(projectProperty))
//        }
//    }
//
//    systemProperty("dukat.test.resources.definitelyTyped", DEF_TYPE_DIR)
//    systemProperty("dukat.test.resources.topN", TOPN_TARGET_DIR)
//
//    def systemPropPrefix = "system."
//    project.properties.forEach { key, value ->
//        if (key.startsWith(systemPropPrefix)) {
//            println("setting system property ${key} => ${value}")
//            systemProperty(key - systemPropPrefix, value)
//        }
//    }
//
//    /*
//        execute top-level classes in sequentially but their methods in parallel
//        see https://junit.org/junit5/docs/5.5.1/user-guide/index.html#writing-tests-parallel-execution
//    */
//    systemProperty("junit.jupiter.execution.parallel.mode.default", "concurrent")
//    systemProperty("junit.jupiter.execution.parallel.mode.classes.default", "concurrent")
//
//    testLogging {
//        showStandardStreams = true
//    }
//
//    if (project.hasProperty('coreTests')) {
//        exclude "**/CoreSetCliTests*"
//    }
//}
