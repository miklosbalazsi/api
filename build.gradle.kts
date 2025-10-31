plugins {
    // Provide plugin versions for subprojects without applying to root
    id("org.springframework.boot") version "3.2.12" apply false
    id("io.spring.dependency-management") version "1.1.6" apply false
    java
}

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "java")

    repositories {
        mavenCentral()
    }

    dependencies {
        // Available to all subprojects
        testImplementation(platform("org.junit:junit-bom:5.10.0"))
        testImplementation("org.junit.jupiter:junit-jupiter")
    }

    tasks.test {
        useJUnitPlatform()
        testLogging {
            events("passed", "skipped", "failed")
        }
    }

    // Optional: helps VS Code detect modules properly
    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(21))
            vendor.set(JvmVendorSpec.ADOPTIUM)
        }
    }
}

tasks.wrapper {
    gradleVersion = "9.0"
}