plugins {
    id(libs.plugins.library.get().pluginId)
    id(libs.plugins.playgroundAndroid.get().pluginId)
    id(libs.plugins.playgroundKotlin.get().pluginId)
    id(libs.plugins.playgroundJava.get().pluginId)
    id(libs.plugins.playgroundCompose.get().pluginId)
    id(libs.plugins.publishing.get().pluginId)
}

android {
    namespace = "com.example.collapsingtoolbar"
}

dependencies {
    implementation(project(":composables:utils"))
}