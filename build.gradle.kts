import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    kotlin("jvm") version "2.3.0"
    kotlin("plugin.power-assert") version "2.3.0"
}

allprojects {
    repositories {
        mavenCentral()
    }
}

tasks.register<Copy>("createDay") {
    val year = project.findProperty("year") as String?
        ?: throw InvalidUserDataException("Please provide a year using `-Pyear=xxxx`")
    val day = (project.findProperty("day") as String?)?.padStart(2, '0')
        ?: throw InvalidUserDataException("Please provide a day using `-Pday=xx`")
    from("template/")
    rename("xx", day)
    filter { line -> line.replace("xx", day) }
    destinationDir = file("Advent${year}/day${day}/")
    if(destinationDir.exists()) {
        throw InvalidUserDataException("Year ${year}, day ${day} already exists.")
    }
}
subprojects {
    apply(plugin = "kotlin")
    apply(plugin = "org.jetbrains.kotlin.plugin.power-assert")

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(25)
        }
    }


    if (project.name != "Utilities") {
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

