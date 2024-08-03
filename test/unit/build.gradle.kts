plugins {
    id("mleiva.jvm.library")
}

dependencies {
    implementation(project(":domain"))
    implementation(libs.junit)
    implementation(libs.kotlinx.coroutines.test)
}