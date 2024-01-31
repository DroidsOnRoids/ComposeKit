import org.gradle.api.Plugin
import org.gradle.api.Project
import pl.droidsonroids.composekit.configuration.configureMavenPublish
import pl.droidsonroids.composekit.publishing.PublishingDefaults
import kotlin.jvm.optionals.getOrElse

class PublishingConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        val version = PublishingDefaults.Version.getOrElse {
            return println("Publishing Plugin wasn't applied, because library version is not specified.")
        }

        with(target) {
            pluginManager.apply("com.vanniktech.maven.publish")

            configureMavenPublish(version)
        }
    }
}