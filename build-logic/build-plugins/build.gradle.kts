plugins {
  `kotlin-dsl`
}

dependencies {
  val kotlinVer = "1.9.0"
  implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVer")
  implementation("org.jetbrains.kotlin:kotlin-serialization:$kotlinVer")
  implementation("org.jetbrains.kotlin:kotlin-assignment:$kotlinVer")
}
