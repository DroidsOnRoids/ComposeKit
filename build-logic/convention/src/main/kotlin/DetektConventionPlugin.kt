import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import pl.droidsonroids.composekit.configuration.configureDetekt
import pl.droidsonroids.composekit.utils.libs

class DetektConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("io.gitlab.arturbosch.detekt")

            configureDetekt()

            dependencies {
                add("detektPlugins", libs.findLibrary("gradlePlugins-detekt").get())
                add("detektPlugins", libs.findLibrary("gradlePlugins-detektFormatting").get())
            }
        }
    }
}