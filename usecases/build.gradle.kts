plugins {
    id("java-library")
    id("mleiva.jvm.library")
    id("mleiva.di.library")
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(libs.kotlinx.coroutines.core)
    testImplementation(project(":test:unit"))
}