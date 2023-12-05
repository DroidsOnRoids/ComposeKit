package configuration.publishing

import com.vanniktech.maven.publish.MavenPublishBaseExtension
import com.vanniktech.maven.publish.SonatypeHost
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class PublishComposePlaygroundModulePlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        plugins.apply("com.vanniktech.maven.publish")
        configurePublishingExtension()
    }
}

private fun Project.configurePublishingExtension() {
    val pomArtifactId = findProperty(PublishDefaults.ArtifactIdPropertyName) as? String ?: name
    val pomDescription = findProperty(PublishDefaults.DesrciptionPropertyName) as? String ?: ""

    configure<MavenPublishBaseExtension> {
        coordinates(PublishDefaults.BaseGroup, pomArtifactId, PublishDefaults.Version)
        setPom(pomDescription)
        publishToMavenCentral(
            host = SonatypeHost.DEFAULT,
            automaticRelease = true
        )
        signAllPublications()
    }
}

private fun MavenPublishBaseExtension.setPom(pomDescription: String) {
    pom {
        name.set(PublishDefaults.Name)
        description.set(pomDescription)
        inceptionYear.set(PublishDefaults.Year)
        url.set(PublishDefaults.Url)
        licenses {
            license {
                name.set(PublishDefaults.LicenseName)
                url.set(PublishDefaults.LicenseUrl)
            }
        }
        scm {
            url.set(PublishDefaults.ScmUrl)
            connection.set(PublishDefaults.ScmConnection)
            developerConnection.set(PublishDefaults.ScmDevConnection)
        }
    }
}
