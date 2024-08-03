plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication"){
            id = "mleiva.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = "mleiva.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidRoom") {
            id = "mleiva.android.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }
        register("jvmRetrofit") {
            id = "mleiva.jvm.retrofit"
            implementationClass = "JvmRetrofitConventionPlugin"
        }
        register("diLibrary") {
            id = "mleiva.di.library"
            implementationClass = "DiLibraryConventionPlugin"
        }
        register("diLibraryCompose") {
            id = "mleiva.di.library.compose"
            implementationClass = "DiLibraryComposeConventionPlugin"
        }
        register("jvmLibrary") {
            id = "mleiva.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }
    }
}