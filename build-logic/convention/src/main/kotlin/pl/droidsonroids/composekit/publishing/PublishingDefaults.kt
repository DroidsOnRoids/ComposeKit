package pl.droidsonroids.composekit.publishing

import java.util.Optional

internal object PublishingDefaults {
    const val Name = "ComposeKit"
    const val Year = "2024"
    const val Url = "https://github.com/DroidsOnRoids/ComposeKit"
    const val ScmUrl = "https://github.com/DroidsOnRoids/ComposeKit"
    const val ScmConnection = "git@github.com:DroidsOnRoids/ComposeKit.git"
    const val ScmDevConnection = "git@github.com:DroidsOnRoids/ComposeKit.git"
    const val LicenseName = "MIT License"
    const val LicenseUrl = "https://github.com/DroidsOnRoids/ComposeKit/blob/master/LICENSE"
    const val BaseGroup = "pl.droidsonroids.composekit"
    const val ArtifactIdPropertyName = "POM_ARTIFACT_ID"
    const val DescriptionPropertyName = "POM_DESCRIPTION"

    private const val VersionKey = "COMPOSE_KIT_VERSION"
    val Version by lazy { Optional.ofNullable(System.getenv(VersionKey)) }
}
