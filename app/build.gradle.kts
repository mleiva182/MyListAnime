plugins {
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ksp)
    id("mleiva.android.application")
    id("mleiva.android.application.compose")
    id("mleiva.android.room")
    id("mleiva.jvm.retrofit")
    id("mleiva.di.library.compose")
    id("mleiva.di.library")
}

android {
    namespace = "com.mleiva.mylistanime"

    defaultConfig {
        applicationId = "com.mleiva.mylistanime"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":usecases"))

    implementation(libs.activity.compose)
    implementation(libs.androidx.navigation.compose)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.test.manifest)

    task("testClasses")
}