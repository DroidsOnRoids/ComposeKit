import org.gradle.api.Plugin
import org.gradle.api.Project
import pl.droidsonroids.composekit.configuration.configureMavenPublish

class PublishingConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.vanniktech.maven.publish")

            configureMavenPublish()
        }
    }
}