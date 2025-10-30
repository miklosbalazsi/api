rootProject.name = "monorepo-spring-gradle-kotlin"
include("backend")

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}
