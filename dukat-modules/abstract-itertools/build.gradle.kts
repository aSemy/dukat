plugins {
    id("dukat.conventions.kotlin-jvm")
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-reflect")

    testImplementation("org.jetbrains.kotlin:kotlin-test-common")
    testImplementation("org.jetbrains.kotlin:kotlin-test-annotations-common")
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    testImplementation("org.junit.jupiter:junit-jupiter-params:${libs.versions.junitJupiter}")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${libs.versions.junitJupiter}")
}
