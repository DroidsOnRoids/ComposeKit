import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import pl.droidsonroids.composekit.configuration.applyAndroidCommons
import pl.droidsonroids.composekit.configuration.configureKotlin

class AndroidLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.library")
            pluginManager.apply("org.jetbrains.kotlin.android")

            configureKotlin()
            configure<LibraryExtension> {
                applyAndroidCommons(extension = this)
            }
        }
    }
}