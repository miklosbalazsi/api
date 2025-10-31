plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    java
}


java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

repositories { mavenCentral() }

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    //Add springboot actuator
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    // Add lombok here
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
    testCompileOnly("org.projectlombok:lombok:1.18.30")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.30")

}

tasks.withType<Test> {
    useJUnitPlatform()
}
