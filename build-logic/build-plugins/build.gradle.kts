plugins {
    `kotlin-dsl`
}

dependencies {
    val kotlinVer = libs.versions.kotlin.get()
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVer")
    implementation("org.jetbrains.kotlin:kotlin-serialization:$kotlinVer")
    implementation("org.jetbrains.kotlin:kotlin-assignment:$kotlinVer")

//    implementation("com.google.protobuf:protobuf-gradle-plugin:0.9.4")
    implementation("com.github.node-gradle:gradle-node-plugin:5.0.0")

    // https://github.com/gradle/gradle/issues/15383#issuecomment-779893192
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))

    implementation("org.curioswitch.curiostack:gradle-protobuf-plugin:0.5.0")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}
