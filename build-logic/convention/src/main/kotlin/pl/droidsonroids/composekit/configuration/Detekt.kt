package pl.droidsonroids.composekit.configuration

import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask
import org.gradle.api.Project
import org.gradle.api.tasks.GradleBuild

internal fun Project.configureDetekt() {
    val detektConfigFilePath = file("$rootDir/detekt-config.yml")
    val detektBaselineFilePath = file("$rootDir/detekt-baseline.yml")

    tasks.register("detektProjectBaseline", DetektCreateBaselineTask::class.java) {
        description = "Overrides current baseline."
        ignoreFailures.set(true)
        parallel.set(true)
        buildUponDefaultConfig.set(true)
        setSource(files(rootDir))
        config.setFrom(detektConfigFilePath)
        baseline.set(detektBaselineFilePath)
        include("**/*.kt")
        include("**/*.kts")
        exclude("**/resources/**")
        exclude("**/build/**")
    }

    tasks.withType(Detekt::class.java) {
        baseline.set(detektBaselineFilePath)
    }

    tasks.withType(Detekt::class.java).configureEach {
        basePath = projectDir.path
        parallel = true
        setSource(files(projectDir))
        include("**/*.kt")
        include("**/*.kts")
        exclude("**/resources/**")
        exclude("**/build/**")
        config.setFrom(detektConfigFilePath)
        autoCorrect = true
        reports {
            xml.required.set(true)
            html.required.set(true)
            txt.required.set(false)
        }
    }

    tasks.register("staticAnalysis", GradleBuild::class.java) {
        dependsOn("detekt")
    }
}
