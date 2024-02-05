import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.9.22"
    id("java-library")
    id("org.jetbrains.dokka") version "1.9.10"
    id("org.jlleitschuh.gradle.ktlint") version "12.1.0"
    id("jacoco")
    id("idea")

    id("signing")
    id("maven-publish")
    id("io.github.gradle-nexus.publish-plugin") version "1.3.0"
}

apply(from = "unitTest.gradle")
apply(from = "release.gradle")

group = "tech.relaycorp"

repositories {
    mavenCentral()
}

dependencies {
    val junit5Version = "5.10.2"
    testImplementation("org.junit.jupiter:junit-jupiter:$junit5Version")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junit5Version")
    testImplementation("io.kotest:kotest-assertions-core-jvm:5.8.0")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
    testImplementation("org.mockito:mockito-inline:5.2.0")
}

kotlin {
    explicitApi()
    jvmToolchain(17)
}

java {
    withJavadocJar()
    withSourcesJar()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

ktlint {
    version.set("0.48.2")
}

tasks.withType<org.jetbrains.dokka.gradle.DokkaTask>().configureEach {
    dokkaSourceSets.configureEach {
        includes.from(project.files(), "api.md")
        reportUndocumented.set(true)
    }
}
