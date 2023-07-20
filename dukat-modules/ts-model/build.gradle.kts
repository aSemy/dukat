plugins {
    id("dukat.conventions.kotlin-jvm")
}

dependencies {
    implementation("com.google.protobuf:protobuf-java:${libs.versions.protobufImplementation.get()}")

    implementation(projects.dukatModules.astCommon)
    implementation(projects.dukatModules.tsModelProto)
    implementation(projects.dukatModules.corePanic)
}
