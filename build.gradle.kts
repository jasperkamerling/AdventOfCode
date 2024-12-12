import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    kotlin("jvm") version "2.1.0"
    kotlin("plugin.power-assert") version "2.1.0"
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
            languageVersion = JavaLanguageVersion.of(23)
        }
    }

    if(project.name != "Utilities") {
        dependencies {
            implementation(project(":Utilities"))
            implementation(kotlin("test"))
        }
    }

    sourceSets {
        main {
            kotlin.srcDirs("src")
            resources.srcDirs("src", "resources")
        }
    }

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    powerAssert {
        functions = listOf("kotlin.require", "kotlin.test.assertEquals", "kotlin.check")
        includedSourceSets = listOf("main")
    }
}

