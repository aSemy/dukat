plugins {
    id("dukat.conventions.kotlin-jvm")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-compiler-embeddable:${libs.versions.kotlin.get()}")
}
