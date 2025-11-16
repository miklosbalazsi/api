plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("java")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
        vendor.set(JvmVendorSpec.ADOPTIUM)
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
     implementation("com.opencsv:opencsv:5.10")

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

tasks.register<Exec>("deployToRaspberryPi") {
    group = "deployment"
    description = "Deploys the JAR file to the Raspberry Pi"

    val jarFile = "${layout.buildDirectory.get()}/libs/${project.name}-${version}.jar"
    val raspberryPiUser = "pi" // Replace with your Raspberry Pi username
    val raspberryPiHost = "192.168.1.100" // Replace with your Raspberry Pi's IP address
    val raspberryPiPath = "/home/pi/app" // Replace with the target directory on your Raspberry Pi

    inputs.file(jarFile)
    outputs.upToDateWhen { false }

    doFirst {
        if (!file(jarFile).exists()) {
            throw GradleException("JAR file not found: $jarFile. Build the project first.")
        }
    }

    commandLine("scp", jarFile, "$raspberryPiUser@$raspberryPiHost:$raspberryPiPath")
}
