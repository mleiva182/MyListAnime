plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    id("architectcoders.jvm.retrofit")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}