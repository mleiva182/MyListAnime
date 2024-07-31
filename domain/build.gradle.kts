plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    id("mleiva.jvm.retrofit")
    id("mleiva.di.library")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}