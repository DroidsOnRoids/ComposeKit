plugins {
    with(libs.plugins.composekit) {
        alias(android.library)
        alias(publishing)
    }
}

android {
    namespace = "pl.droidsonroids.composekit.composables"
}

dependencies {
    api(projects.composables.autoresizedtext)
    api(projects.composables.collapsingtoolbar)
    api(projects.composables.draganddroplist)
    api(projects.composables.foldabletoolbar)
}