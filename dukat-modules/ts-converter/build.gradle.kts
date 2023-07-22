import com.github.gradle.node.npm.task.NpmTask

plugins {
    java
    id("dukat.conventions.node")
}

node {
    download.set(true)
    nodeProjectDir.set(layout.buildDirectory.dir("ts-converter-npm"))
}

val prepareNpmPackage by tasks.registering(Copy::class) {
    group = project.name

    val typescriptVersion = libs.versions.typescript.get()
    inputs.property("typescriptVersion", typescriptVersion)
    val protobufVersion = libs.versions.protobufImplementation.get()
    inputs.property("protobufVersion", protobufVersion)

    into(node.nodeProjectDir)
    from("package.template.json") {
        rename { "package.json" }
        expand(
            "TS_VERSION" to typescriptVersion,
            "PROTOBUF_VERSION" to protobufVersion,
        )
    }
    from("package-lock.json")
    from("tsconfig.json")
    from("webpack.config.js")
}

val npmCleanInstall by tasks.registering(NpmTask::class) {
    group = project.name
    args.addAll("clean-install")
}

tasks.withType<com.github.gradle.node.task.BaseTask>().configureEach {
    dependsOn(prepareNpmPackage)
}

val packagePath = "${project.buildDir}/package"
val tsDeclarationsPath = ".tsdeclarations"

//task copyTypescriptDeclarations(type: Copy) {
//  dependsOn = [installNpmDependencies]
//
//  from "${packagePath}/node_modules/typescript/lib"
//  into tsDeclarationsPath
//  include "*.d.ts"
//}
//
//task compileTs(type: Exec) {
//  dependsOn = [execConfig, installNpmDependencies, copyTypescriptDeclarations, ":ts-model-proto:build"]
//  commandLine project(":node-distrib").config.nodePath, file("${packagePath}/node_modules/typescript/bin/tsc"), "-p", file("./tsconfig.json").path
//  workingDir project.projectDir
//
//  inputs.file("./tsconfig.json")
//  inputs.dir("./src")
//  inputs.dir(packagePath)
//  outputs.dir("./build/ts")
//}

val compileTypeScript by tasks.registering(Exec::class) {
    dependsOn(npmCleanInstall)
    workingDir(node.nodeProjectDir)

    val nodePath = tasks.npmSetup.map { it.npmDir.get().file("bin/node").asFile.invariantSeparatorsPath }
    inputs.property("nodePath", nodePath)
    val tsc = node.nodeProjectDir.map { it.file("node_modules/typescript/bin/tsc").asFile.invariantSeparatorsPath }
    inputs.property("tsc", tsc)
    val tsconfigJson = layout.projectDirectory.file("tsconfig.json").asFile.invariantSeparatorsPath
    inputs.property("tsconfigJson", tsconfigJson)
    doFirst {
        commandLine(nodePath.get(), tsc.get(), "-p", tsconfigJson )
    }
    //    compileTs.commandLine project(":node-distrib").config.nodePath, file("${packagePath}/node_modules/typescript/bin/tsc"), "-p", file("./tsconfig.json").path
//    script.set(node.nodeProjectDir.file("node_modules/typescript/bin/tsc"))
//    args.addAll("-p", "tsconfig.json")
//    args.addAll("tsc", "-p", "tsconfig.json")
//    args.addAll("node_modules/typescript/bin/tsc", "-p", "tsconfig.json")
}

val tsModelProtoTSDeclarations by tasks.registering(Sync::class) {
    group = project.name
    from(node.nodeProjectDir) {
        include("**/*.d.ts")
    }
    into(temporaryDir)
}

//configurations.tsModelProtoElements {
//    outgoing {
//        this.artifacts()
//artifact(tsModelProtoTSDeclarations)
//    }
//}

//task webpack(type: Exec) {
//  commandLine project(":node-distrib").config.nodePath, "${project.buildDir}/package/node_modules/webpack/bin/webpack.js"
//  dependsOn = [compileTs, ":ts-model-proto:build", execConfig]
//
//  workingDir project.projectDir
//  def scriptName = "${project.buildDir}/package/node_modules/webpack/bin/webpack.js"
//
//  inputs.file(scriptName)
//  inputs.file("webpack.config.js")
//  inputs.dir("${project(":ts-model-proto").buildDir}/generated/source/proto/main/js")
//  inputs.dir("${project.buildDir}/ts")
//  inputs.dir("${project.buildDir}/package/node_modules")
//  outputs.file("${project.buildDir}/bundle/converter.js")
//}
//
//task createJar(type: Zip) {
//  dependsOn = [webpack]
//  baseName 'dukatts'
//  extension 'jar'
//  destinationDir file("${project.buildDir}/jar")
//
//  from("${packagePath}/node_modules/typescript/lib/tsserverlibrary.js") {
//    into 'ts'
//  }
//
//  from("${packagePath}/node_modules/typescript/lib") {
//    include "**/*.d.ts"
//    into 'ts'
//  }
//
//  from('build/bundle/converter.js') {
//    into 'js'
//  }
//
//}
//
//configurations {
//  dukatTsResources
//}
//
//configurations.default.extendsFrom(configurations.dukatTsResources)
//
//clean {
//  delete tsDeclarationsPath
//}
