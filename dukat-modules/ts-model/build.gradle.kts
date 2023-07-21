plugins {
    id("dukat.conventions.kotlin-jvm")
}

dependencies {
    implementation(projects.dukatModules.astCommon)
    implementation(projects.dukatModules.tsModelProto)
    implementation(projects.dukatModules.corePanic)
}
