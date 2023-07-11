package configuration.detekt

import org.gradle.api.Plugin
import org.gradle.api.Project

class DetektConfigurationPlugin : Plugin<Project> {

    override fun apply(project: Project) = with(project) {
        project.configureDetekt()
    }
}