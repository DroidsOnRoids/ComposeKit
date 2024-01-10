import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import pl.droidsonroids.composekit.configuration.applyAndroidCommons
import pl.droidsonroids.composekit.configuration.configureKotlin

class AndroidAppConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.application")
            pluginManager.apply("org.jetbrains.kotlin.android")

            configureKotlin()
            configure<ApplicationExtension> {
                defaultConfig.targetSdk = 34
                applyAndroidCommons(extension = this)
            }
        }
    }
}