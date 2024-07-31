import com.mleiva.mylistanime.libs
import org.gradle.api.Plugin
import org.gradle.api.Project

/***
 * Project: MyListAnime
 * From:
 * Creted by: Marcelo Leiva on 31-07-2024 at 9:59
 ***/
class DiLibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("mleiva.di.library")
                apply("dagger.hilt.android.plugin")
            }

            dependencies.add("implementation", libs.findLibrary("hilt.android").get())
            dependencies.add("implementation", libs.findLibrary("androidx.hilt.navigation.compose").get())
        }
    }
}