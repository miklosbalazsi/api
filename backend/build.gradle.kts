plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("java")
}

import org.gradle.api.tasks.testing.Test

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
        vendor.set(JvmVendorSpec.ADOPTIUM)
    }
}


dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    
    // Testing dependencies
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.10.0") 

    // Lombok
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
    testCompileOnly("org.projectlombok:lombok:1.18.30")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.30")
}

tasks.test {
    useJUnitPlatform()
}


// --- Integration test source set (src/it) and task configuration ---
sourceSets {
    val it by creating {
        java.srcDir("src/it/java")
        resources.srcDir("src/it/resources")
        // Reuse main outputs and test runtime classpath for running ITs
        compileClasspath += sourceSets["main"].output + configurations["testRuntimeClasspath"]
        runtimeClasspath += output + compileClasspath
    }
}

// Make IT configurations inherit from test ones (gets JUnit, Spring Boot test, etc.)
configurations["itImplementation"].extendsFrom(configurations["testImplementation"])
configurations["itRuntimeOnly"].extendsFrom(configurations["testRuntimeOnly"])

// Register the integration test task and wire it into the lifecycle
val itTest = tasks.register<Test>("itTest") {
    description = "Runs integration tests."
    group = "verification"
    testClassesDirs = sourceSets["it"].output.classesDirs
    classpath = sourceSets["it"].runtimeClasspath
    useJUnitPlatform()
    // Only pick up classes that follow typical IT naming conventions
    include("**/*IT.class", "**/*ITCase.class")
    shouldRunAfter("test")
}

tasks.named("check") {
    dependsOn(itTest)
}

