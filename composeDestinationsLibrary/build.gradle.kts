plugins {
    with(libs.plugins) {
        alias(composekit.android.library)
        alias(composekit.android.library.compose)
        alias(composekit.publishing)
    }
    alias(libs.plugins.ksp)
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