plugins {
    alias(deps.plugins.ksp)
    id(deps.plugins.library.get().pluginId)
    id(deps.plugins.playgroundAndroid.get().pluginId)
    id(deps.plugins.playgroundKotlin.get().pluginId)
    id(deps.plugins.playgroundJava.get().pluginId)
    id(deps.plugins.playgroundCompose.get().pluginId)
    id(deps.plugins.parcelize.get().pluginId)
    id(deps.plugins.kapt.get().pluginId)
}

android {
    namespace = "com.dor.compose.playground.composedestinationslibrary"

    libraryVariants.forEach { variant ->
        kotlin.sourceSets {
            getByName(variant.name) {
                kotlin.srcDir("build/generated/ksp/${variant.name}/kotlin")
            }
        }
    }
}

dependencies {
    implementation(deps.androidX.compose.navigation)

    implementation(deps.composeDestinations.core)
    ksp(deps.composeDestinations.ksp)

    implementation(deps.dagger)
    kapt(deps.daggerCompiler)

    implementation(project(":composables:utils"))
}