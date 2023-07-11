package modulegenerator

import org.gradle.api.Plugin
import org.gradle.api.Project

class ModuleGenerationPlugin : Plugin<Project> {

    override fun apply(project: Project): Unit = project.run {
        tasks.register(GenerateModuleTask.taskName, GenerateModuleTask::class.java)
        tasks.register(GenerateComposeModuleTask.taskName, GenerateComposeModuleTask::class.java)
    }
}