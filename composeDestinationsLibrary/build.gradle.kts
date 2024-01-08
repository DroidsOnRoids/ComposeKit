plugins {
    alias(libs.plugins.ksp)
    id(libs.plugins.library.get().pluginId)
    id(libs.plugins.playgroundAndroid.get().pluginId)
    id(libs.plugins.playgroundKotlin.get().pluginId)
    id(libs.plugins.playgroundJava.get().pluginId)
    id(libs.plugins.playgroundCompose.get().pluginId)
    id(libs.plugins.parcelize.get().pluginId)
    id(libs.plugins.kapt.get().pluginId)
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
    implementation(libs.androidX.compose.navigation)

    implementation(libs.composeDestinations.core)
    ksp(libs.composeDestinations.ksp)

    implementation(libs.dagger)
    kapt(libs.daggerCompiler)

    implementation(project(":composables:utils"))
}