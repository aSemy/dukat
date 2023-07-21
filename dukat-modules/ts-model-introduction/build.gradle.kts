plugins {
    id("dukat.conventions.kotlin-jvm")
    kotlin("plugin.serialization")
}

dependencies {
    implementation(libs.kotlinxSerialization.core)

    implementation(projects.dukatModules.astCommon)
    implementation(projects.dukatModules.astModel)
    implementation(projects.dukatModules.coreLogging)
    implementation(projects.dukatModules.moduleNameResolver)
    implementation(projects.dukatModules.coreOwnerContext)
    implementation(projects.dukatModules.corePanic)
    implementation(projects.dukatModules.coreStdlib)
    implementation(projects.dukatModules.coreTranslatorString)
    implementation(projects.dukatModules.tsAstDeclarations)
    implementation(projects.dukatModules.tsLowerings)
    implementation(projects.dukatModules.tsModel)
    implementation(projects.dukatModules.tsNodes)
}
