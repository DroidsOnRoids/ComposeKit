package pl.droidsonroids.composekit.utils

internal object ContinuousIntegration {

    @JvmStatic
    fun isCi() = System.getenv("CI") == "true"
}