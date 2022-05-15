plugins {
    kotlin("jvm") version "1.6.21"
}

subprojects {
    apply(plugin = "kotlin")

    repositories {
        mavenCentral()
    }

    tasks {
        sourceSets {
            main {
                java.srcDirs("src")
            }
        }
    }
}

