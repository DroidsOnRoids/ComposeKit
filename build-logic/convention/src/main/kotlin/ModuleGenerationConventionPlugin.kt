import org.gradle.api.Plugin
import org.gradle.api.Project
import pl.droidsonroids.composekit.modulegenerator.GenerateComposeModuleTask
import pl.droidsonroids.composekit.modulegenerator.GenerateModuleTask

class ModuleGenerationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            tasks.register(GenerateModuleTask.taskName, GenerateModuleTask::class.java)
            tasks.register(GenerateComposeModuleTask.taskName, GenerateComposeModuleTask::class.java)
        }
    }
}