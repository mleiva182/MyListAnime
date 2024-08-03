import com.mleiva.mylistanime.configureKotlinJvm
import org.gradle.api.Plugin
import org.gradle.api.Project

/***
 * Project: MyListAnime
 * From:
 * Creted by: Marcelo Leiva on 02-08-2024 at 10:29
 ***/
class JvmLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.jvm")
            }
            configureKotlinJvm()
        }
    }
}