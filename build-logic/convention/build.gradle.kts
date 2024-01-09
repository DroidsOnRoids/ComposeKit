plugins {
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        register("composekit.android.app") {
            id = "composekit.android.app"
            implementationClass = "AndroidAppConventionPlugin"
        }
        register("composekit.android.app.compose") {
            id = "composekit.android.app.compose"
            implementationClass = "AndroidAppComposeConventionPlugin"
        }
        register("composekit.android.library") {
            id = "composekit.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("composekit.android.library.compose") {
            id = "composekit.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("composekit.detekt") {
            id = "composekit.detekt"
            implementationClass = "DetektConventionPlugin"
        }
        register("composekit.module.generator") {
            id = "composekit.module.generator"
            implementationClass = "ModuleGenerationConventionPlugin"
        }
        register("composekit.publishing") {
            id = "composekit.publishing"
            implementationClass = "PublishingConventionPlugin"
        }
    }
}

dependencies {
    implementation(libs.gradlePlugins.android)
    implementation(libs.gradlePlugins.kotlin)
    implementation(libs.gradlePlugins.detekt)
    implementation(libs.vanniktech.maven.publish)

    testImplementation(libs.junit)
    testImplementation(libs.assertJ)
    testImplementation(libs.mockk)
}