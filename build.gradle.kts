import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    kotlin("jvm") version "2.0.0"
    kotlin("plugin.power-assert") version "2.0.0"
}

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "kotlin")
    apply(plugin = "org.jetbrains.kotlin.plugin.power-assert")

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(21)
        }
    }

    tasks {
        sourceSets {
            main {
                java.srcDirs("src")
                resources.srcDirs("src")
            }
        }
    }

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    powerAssert {
        functions = listOf("kotlin.require", "kotlin.test.assertEquals", "kotlin.check")
        includedSourceSets = listOf("main")
    }
}

