import com.mleiva.mylistanime.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/***
 * Project: MyListAnime
 * From:
 * Creted by: Marcelo Leiva on 31-07-2024 at 9:58
 ***/
class DiLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.google.devtools.ksp")

            dependencies {
                add("implementation", libs.findLibrary("hilt.core").get())
                add("ksp", libs.findLibrary("hilt.compiler").get())
            }
        }
    }
}