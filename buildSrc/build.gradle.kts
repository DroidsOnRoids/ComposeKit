import org.gradle.api.internal.classpath.ModuleRegistry
import org.gradle.api.internal.project.ProjectInternal

plugins {
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        register("com.dor.compose.playground.android") {
            id = "com.dor.compose.playground.android"
            implementationClass = "configuration.android.ConfigureAndroidModulePlugin"
        }
        register("com.dor.compose.playground.java") {
            id = "com.dor.compose.playground.java"
            implementationClass = "configuration.java.ConfigureJavaModulePlugin"
        }
        register("com.dor.compose.playground.kotlin") {
            id = "com.dor.compose.playground.kotlin"
            implementationClass = "configuration.kotlin.ConfigureKotlinModulePlugin"
        }
        register("com.dor.compose.playground.compose") {
            id = "com.dor.compose.playground.compose"
            implementationClass = "configuration.compose.ConfigureComposeModulePlugin"
        }
        register("com.dor.compose.playground.publishing") {
            id = "com.dor.compose.playground.publishing"
            implementationClass = "configuration.publishing.PublishComposePlaygroundModulePlugin"
        }
    }
}

dependencies {
    implementation(deps.kotlinPoet)
    implementation(deps.gradlePlugins.android)
    implementation(deps.gradlePlugins.kotlin)
    implementation(deps.gradlePlugins.detekt)
    implementation(deps.vanniktech.maven.publish)

    testImplementation(deps.junit)
    testImplementation(deps.assertJ)
    testImplementation(deps.mockk)

    // Fix for failing unit tests
    // https://github.com/gradle/gradle/issues/16774
    val toolingApiBuildersJar = (project as ProjectInternal).services.get(ModuleRegistry::class.java)
        .getModule("gradle-tooling-api-builders")
        .classpath
        .asFiles
        .first()
    testRuntimeOnly(files(toolingApiBuildersJar))
}