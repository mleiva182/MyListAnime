plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    id("mleiva.di.library")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies{
    implementation(project(":domain"))
    implementation(libs.kotlinx.coroutines.core)
}