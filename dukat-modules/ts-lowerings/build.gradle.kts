plugins {
    id("dukat.conventions.kotlin-jvm")
}

dependencies {
    implementation(projects.dukatModules.astCommon)
    implementation(projects.dukatModules.abstractItertools)
    implementation(projects.dukatModules.coreLogging)
    implementation(projects.dukatModules.coreOwnerContext)
    implementation(projects.dukatModules.tsModel)
    implementation(projects.dukatModules.abstractToposort)
    implementation(projects.dukatModules.corePanic)
    implementation(projects.dukatModules.coreStdlib)
    implementation(projects.dukatModules.modelLoweringsCommon)
}
