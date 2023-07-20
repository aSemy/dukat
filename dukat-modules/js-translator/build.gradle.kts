plugins {
    id("dukat.conventions.kotlin-jvm")
}

dependencies {
    implementation(projects.dukatModules.astCommon)
    implementation(projects.dukatModules.astModel)
    implementation(projects.dukatModules.jsTypeAnalysis)
    implementation(projects.dukatModules.coreTranslator)
    implementation(projects.dukatModules.tsLowerings)
    implementation(projects.dukatModules.tsModel)
    implementation(projects.dukatModules.tsTranslator)
    implementation(projects.dukatModules.moduleNameResolver)
}
