package pl.droidsonroids.composekit.configuration

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import pl.droidsonroids.composekit.utils.libs

internal fun Project.configureCompose(
    extension: CommonExtension<*, *, *, *, *>,
) {
    extension.apply {
        buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = libs.findVersion("androidXComposeCompiler").get().requiredVersion
        }
    }

    dependencies {
        add("implementation", libs.findLibrary("androidX-compose-bom").get())
        add("implementation", libs.findBundle("compose-basic").get())
        add("debugImplementation", libs.findLibrary("androidX.compose.uiTooling").get())
    }
}
