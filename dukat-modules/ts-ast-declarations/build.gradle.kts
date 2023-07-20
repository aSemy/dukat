plugins {
    id("dukat.conventions.kotlin-jvm")
}

dependencies {
    implementation(projects.dukatModules.astCommon)
    implementation(projects.dukatModules.coreOwnerContext)
    implementation(projects.dukatModules.corePanic)
    implementation(projects.dukatModules.tsModel)
    implementation(projects.dukatModules.tsNodes)
}
