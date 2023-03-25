import java.net.URI

val ktor_version: String by project

plugins {
    kotlin("jvm") version "1.8.10"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.0"

    `maven-publish`
}

group = "io.github.slaxnetwork"
version = "0.0.1"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    api("io.ktor:ktor-client-core:$ktor_version")
    api("io.ktor:ktor-client-cio:$ktor_version")
    api("io.ktor:ktor-client-content-negotiation:$ktor_version")
    api("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")

    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.8.10")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = URI.create("https://maven.pkg.github.com/SlaxNetwork/kyouko-kt-wrapper")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }

    publications {
        create<MavenPublication>(project.name.toLowerCase()) {
            groupId = "io.github.slaxnetwork"
            artifactId = "kyouko-wrapper"
            version = "${project.version}"

            from(components["java"])
        }
    }
}