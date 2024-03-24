plugins {
    kotlin("jvm") version "1.9.23"
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

