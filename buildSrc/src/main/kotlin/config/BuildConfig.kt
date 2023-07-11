package config

import org.gradle.api.JavaVersion

object BuildConfig {

    const val TARGET_SDK = 32
    val JAVA_VERSION = JavaVersion.VERSION_17
    val JAVA_VERSION_NAME = JAVA_VERSION.toString()
}