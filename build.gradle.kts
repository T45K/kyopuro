plugins {
    java
    kotlin("jvm") version "2.2.10" apply false
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "org.jetbrains.kotlin.jvm")

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(24))
        }
    }

    repositories {
        mavenCentral()
    }

    sourceSets {
        main {
            java.srcDirs("src")
        }
    }

    tasks.named<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>("compileKotlin") {
        setSource(fileTree("src"))
    }
}
