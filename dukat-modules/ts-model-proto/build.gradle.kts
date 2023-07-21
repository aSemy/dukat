import com.github.gradle.node.npm.task.NpmTask

plugins {
    id("dukat.conventions.protobuf-java")
    id("dukat.conventions.node")
}

val targetMainDir: Provider<Directory> = layout.buildDirectory.dir("generated/src/proto/main")
//val nodeModulesPath: Provider<Directory> = layout.buildDirectory.dir("ts-model-proto-npm")

node {
    nodeProjectDir.set(layout.buildDirectory.dir("ts-model-proto-npm"))
}

val prepareNpmPackage by tasks.registering(Copy::class) {
    group = project.name
    into(node.nodeProjectDir)
    from("package.template.json") {
        rename { it.replace(".template", "") }
    }
    from("package-lock.json")
}

tasks.withType<com.github.gradle.node.task.BaseTask>().configureEach {
    dependsOn(prepareNpmPackage)
}

val npmCleanInstall by tasks.registering(NpmTask::class) {
    group = project.name
    args.addAll("clean-install")
}

protobuf {
    languages {
        register("js") {
            options.addAll(
                "import_style=commonjs",
                "binary",
            )
        }
        register("ts") {
            plugin {
                val protocGenTsPath = node.nodeProjectDir
                    .zip(dukatBuild.currentOS) { nodeDir, currentOS ->
                        val protocTsGenBin = if (currentOS.isWindows) "protoc-gen-ts.cmd" else "protoc-gen-ts"
                        nodeDir.file("node_modules/ts-protoc-gen/bin/$protocTsGenBin").asFile
                    }
                path.set(protocGenTsPath)
            }
        }
    }
    protoc {
        artifact.set(libs.protobuf.protoc.map { it.toString() })
    }
}

dependencies {
    api(libs.protobuf.java)
}

//tasks.generateProto {
//    dependsOn(npmCleanInstall)
//}
//
//val copyGeneratedTs by tasks.registering(Sync::class) {
//    group = project.name
//    into(targetMainDir.map { it.dir("js") })
////    from(targetMainDir.map { it.dir("ts") })
//    from(tasks.generateProto.map { it.outputBaseDir })
//}
//
//dependencies {
//    implementation("com.google.protobuf:protobuf-java:${libs.versions.protobufImplementation.get()}")
//}
//
//sourceSets {
//    main {
//        proto {
////            setSrcDirs(listOf("src"))
//        }
//        java {
//            setSrcDirs(
//                listOf(
////                tasks.generateProto.map { it.outputBaseDir },
//                    targetMainDir.get().dir("java").asFile,
//                )
//            )
////            setSrcDirs(listOf(targetMainDir.get().dir("java").asFile))
//        }
//    }
////    create("generated") {
////        java.setSrcDirs(listOf(targetMainDir.get().dir("java").asFile))
////    }
//}
//
//protobuf {
//    plugins {
//        create("ts") {
//            path = dukatBuild.currentOS.map { currentOS ->
//                val ext = if (currentOS.isWindows) ".cmd" else ""
//                "$buildDir/node_modules/ts-protoc-gen/bin/protoc-gen-ts$ext"
//            }.get()
//
////            val protocGenTsPath = node.nodeProjectDir
////                .map { it.dir("node_modules/ts-protoc-gen/bin/protoc-gen-ts").asFile.invariantSeparatorsPath }
////                .zip(dukatBuild.currentOS) { protocGenTs, currentOS ->
////                    val ext = if (currentOS.isWindows) ".cmd" else ""
////                    protocGenTs + ext
////                }
////
////            path = protocGenTsPath.get()
//            println("set protobuf ts path to $path")
//        }
//    }
//
//    generateProtoTasks.all().configureEach {
//        dependsOn(npmCleanInstall)
//        builtins {
//            create("js") {
//                option("import_style=commonjs")
//                option("binary")
//            }
//        }
//        plugins {
//            create("ts") {
//            }
//        }
//        protoc {
//            artifact = "com.google.protobuf:protoc:${libs.versions.protobufImplementation.get()}"
//        }
//    }
//}
