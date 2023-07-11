import configuration.detekt.DetektConfigurationPlugin
import modulegenerator.ModuleGenerationPlugin

plugins {
    id(deps.plugins.detekt.get().pluginId)
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
        classpath(deps.gradlePlugins.android)
        classpath(deps.gradlePlugins.kotlin)
        classpath(deps.gradlePlugins.detekt)
    }
}

tasks.register("clean", Delete::class.java) {
    delete(rootProject.buildDir)
}

dependencies {
    "detektPlugins"(deps.gradlePlugins.detekt)
    "detektPlugins"(deps.gradlePlugins.detektFormatting)
}