package pl.droidsonroids.composekit.configuration

import com.vanniktech.maven.publish.MavenPublishBaseExtension
import com.vanniktech.maven.publish.SonatypeHost
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import pl.droidsonroids.composekit.publishing.PublishingDefaults

@Suppress("UnstableApiUsage")
internal fun Project.configureMavenPublish() {
    val pomArtifactId = findProperty(PublishingDefaults.ArtifactIdPropertyName) as? String ?: name
    val pomDescription = findProperty(PublishingDefaults.DescriptionPropertyName) as? String ?: ""

    configure<MavenPublishBaseExtension> {
        coordinates(PublishingDefaults.BaseGroup, pomArtifactId, PublishingDefaults.Version)
        setPom(pomDescription)
        publishToMavenCentral(
            host = SonatypeHost.DEFAULT,
            automaticRelease = true
        )
        signAllPublications()
    }
}

@Suppress("UnstableApiUsage")
private fun MavenPublishBaseExtension.setPom(pomDescription: String) {
    pom {
        name.set(PublishingDefaults.Name)
        description.set(pomDescription)
        inceptionYear.set(PublishingDefaults.Year)
        url.set(PublishingDefaults.Url)
        licenses {
            license {
                name.set(PublishingDefaults.LicenseName)
                url.set(PublishingDefaults.LicenseUrl)
            }
        }
        scm {
            url.set(PublishingDefaults.ScmUrl)
            connection.set(PublishingDefaults.ScmConnection)
            developerConnection.set(PublishingDefaults.ScmDevConnection)
        }
    }
}
