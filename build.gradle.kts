plugins {
    with(libs.plugins) {
        alias(composekit.android.app) apply false
        alias(composekit.android.app.compose) apply false
        alias(composekit.android.library) apply false
        alias(composekit.android.library.compose) apply false
        alias(composekit.publishing) apply false
        alias(composekit.module.generator)
        alias(composekit.detekt)
    }
}

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
        classpath(libs.vanniktech.maven.publish)
    }
}

tasks.register("clean", Delete::class.java) {
    delete(rootProject.buildDir)
}
