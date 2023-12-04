plugins {
    kotlin("jvm") version "1.9.21"
}

allprojects {
    repositories {
        mavenCentral()
    }
}
subprojects {
    apply(plugin = "kotlin")

    tasks {
        sourceSets {
            main {
                java.srcDirs("src")
                resources.srcDirs("src")
            }
        }
    }
}

