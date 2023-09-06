package configuration.utils

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

internal val Project.deps: VersionCatalog
    get() = project.extensions.getByType<VersionCatalogsExtension>().named("deps")
