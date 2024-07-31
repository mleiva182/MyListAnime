import com.android.build.api.dsl.ApplicationExtension
import com.mleiva.mylistanime.configureKotlinAndroid
import org.gradle.api.Project
import org.gradle.api.Plugin
import org.gradle.kotlin.dsl.configure

/***
 * Project: MyListAnime
 * From:
 * Creted by: Marcelo Leiva on 30-07-2024 at 18:30
 ***/
class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }
            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = 34
            }
        }
    }
}