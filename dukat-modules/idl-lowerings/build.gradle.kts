plugins {
    id("dukat.conventions.kotlin-jvm")
}

dependencies {
    implementation(projects.dukatModules.astCommon)
    implementation(projects.dukatModules.astModel)
    implementation(projects.dukatModules.modelLoweringsCommon)
    implementation(projects.dukatModules.idlDeclarations)
    implementation(projects.dukatModules.coreLogging)
    implementation(projects.dukatModules.coreOwnerContext)
    implementation(projects.dukatModules.corePanic)
    implementation(projects.dukatModules.coreStdlib)
    implementation(projects.dukatModules.coreTranslatorString)
}
