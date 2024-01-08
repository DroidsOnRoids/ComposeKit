import configuration.detekt.DetektConfigurationPlugin
import modulegenerator.ModuleGenerationPlugin

plugins {
    id(libs.plugins.detekt.get().pluginId)
}
apply<DetektConfigurationPlugin>()
apply<ModuleGenerationPlugin>()

buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath(libs.gradlePlugins.android)
        classpath(libs.gradlePlugins.kotlin)
        classpath(libs.gradlePlugins.detekt)
    }
}

tasks.register("clean", Delete::class.java) {
    delete(rootProject.buildDir)
}

dependencies {
    "detektPlugins"(libs.gradlePlugins.detekt)
    "detektPlugins"(libs.gradlePlugins.detektFormatting)
}