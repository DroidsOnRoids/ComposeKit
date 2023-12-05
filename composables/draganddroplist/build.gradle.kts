plugins {
    id(deps.plugins.library.get().pluginId)
    id(deps.plugins.playgroundAndroid.get().pluginId)
    id(deps.plugins.playgroundKotlin.get().pluginId)
    id(deps.plugins.playgroundJava.get().pluginId)
    id(deps.plugins.playgroundCompose.get().pluginId)
    id(deps.plugins.publishing.get().pluginId)
}

android {
    namespace = "com.dor.compose.playground.composables.draganddroplist"
}

dependencies {
    implementation(project(":composables:utils"))
}
