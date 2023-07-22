plugins {
    id("dukat.conventions.kotlin-jvm")
    kotlin("plugin.serialization")
}

dependencies {
    implementation(libs.kotlinxSerialization.core)
    implementation(projects.dukatModules.astCommon)
    implementation(projects.dukatModules.tsModel)

    testImplementation("org.jetbrains.kotlin:kotlin-reflect")

    testImplementation("org.jetbrains.kotlin:kotlin-test-common")
    testImplementation("org.jetbrains.kotlin:kotlin-test-annotations-common")
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // without this dependency one won't see "Click to see difference" in IDEA
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")

    testImplementation("org.junit.jupiter:junit-jupiter-params:${libs.versions.junitJupiter.get()}")
    testRuntimeOnly ("org.junit.jupiter:junit-jupiter-engine:${libs.versions.junitJupiter.get()}")

    testImplementation("org.jetbrains.kotlin:kotlin-gradle-plugin")
}
