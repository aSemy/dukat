plugins {
    id("dukat.conventions.kotlin-jvm")
}

dependencies {
    implementation(projects.dukatModules.astCommon)
    implementation(projects.dukatModules.astModel)
    implementation(projects.dukatModules.coreLogging)
    implementation(projects.dukatModules.corePanic)
    implementation(projects.dukatModules.coreStdlib)
    implementation(projects.dukatModules.coreTranslator)
    implementation(projects.dukatModules.coreTranslatorString)

    implementation(projects.dukatModules.coreDescriptorsV140)

    implementation("org.jetbrains.kotlin:kotlin-reflect:${libs.versions.kotlin.get()}")
    implementation(libs.kotlin.compilerEmbeddable)
    implementation("net.java.dev.jna:jna-platform:5.12.1")
}
