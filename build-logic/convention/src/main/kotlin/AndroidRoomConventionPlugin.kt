import com.mleiva.mylistanime.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/***
 * Project: MyListAnime
 * From:
 * Creted by: Marcelo Leiva on 30-07-2024 at 22:16
 ***/
class AndroidRoomConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.google.devtools.ksp")

            dependencies {
                add("implementation", libs.findLibrary("androidx.room.ktx").get())
                add("ksp", libs.findLibrary("androidx.room.compiler").get())
            }
        }
    }
}