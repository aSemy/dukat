plugins {
    id("dukat.conventions.kotlin-jvm")
}

dependencies {
    implementation("com.google.protobuf:protobuf-java:${libs.versions.protobufImplementation.get()}")

    implementation(projects.dukatModules.astCommon)
    implementation(projects.dukatModules.astModel)
    implementation(projects.dukatModules.coreLogging)
    implementation(projects.dukatModules.modelLowerings)
    implementation(projects.dukatModules.modelLoweringsCommon)
    implementation(projects.dukatModules.modelSerialization)
    implementation(projects.dukatModules.moduleNameResolver)
    implementation(projects.dukatModules.coreStdlib)
    implementation(projects.dukatModules.coreTranslator)
    implementation(projects.dukatModules.tsAstDeclarations)
    implementation(projects.dukatModules.tsLowerings)
    implementation(projects.dukatModules.tsModel)
    implementation(projects.dukatModules.tsModelProto)
    implementation(projects.dukatModules.tsNodes)
    implementation(projects.dukatModules.tsModelIntroduction)
}
