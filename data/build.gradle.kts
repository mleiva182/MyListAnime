plugins {
    id("java-library")
    id("mleiva.jvm.library")
    id("mleiva.di.library")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies{
    implementation(project(":domain"))
    implementation(libs.kotlinx.coroutines.core)
    testImplementation(project(":test:unit"))
}