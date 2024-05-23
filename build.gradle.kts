plugins {
    kotlin("jvm") version "2.0.0"
}

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "kotlin")

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
}

