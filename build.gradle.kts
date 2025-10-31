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

tasks.wrapper {
    gradleVersion = "8.7"
}