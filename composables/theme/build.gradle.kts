plugins {
    id(deps.plugins.library.get().pluginId)
    id(deps.plugins.playgroundAndroid.get().pluginId)
    id(deps.plugins.playgroundKotlin.get().pluginId)
    id(deps.plugins.playgroundJava.get().pluginId)
}

android {
    namespace = "com.dor.compose.playground.composables.theme"

    buildFeatures.compose = true

    composeOptions {
        kotlinCompilerExtensionVersion = deps.versions.androidXComposeCompiler.get()
    }
}

dependencies {
    implementation(deps.androidX.compose.runtime)
    implementation(deps.androidX.compose.material)
}