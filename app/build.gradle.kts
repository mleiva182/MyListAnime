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

        testInstrumentationRunner = "com.mleiva.mylistanime.di.HiltTestRunner"
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

    //testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.androidx.test.rules)

    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation(project(":test:unit"))
    testImplementation(project(":test:unit"))
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
    androidTestImplementation(libs.hilt.android.testing)
    kspAndroidTest(libs.hilt.compiler)
    debugImplementation(libs.androidx.ui.test.manifest)

    task("testClasses")
}