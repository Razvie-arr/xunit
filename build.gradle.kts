plugins {
    kotlin("jvm") version "2.3.21"
}

group = "xunit"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("reflect"))
}

kotlin {
    jvmToolchain(25)
}