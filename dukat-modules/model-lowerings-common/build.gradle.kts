plugins {
    id("dukat.conventions.kotlin-jvm")
}

dependencies {
    implementation(projects.dukatModules.astCommon)
    implementation(projects.dukatModules.astModel)
    implementation(projects.dukatModules.abstractGraphs)
    implementation(projects.dukatModules.coreLogging)
    implementation(projects.dukatModules.coreOwnerContext)
    implementation(projects.dukatModules.corePanic)
    implementation(projects.dukatModules.coreStdlib)
    implementation(projects.dukatModules.coreStdlibGenerator)
    implementation(projects.dukatModules.abstractToposort)
}
