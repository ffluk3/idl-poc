/*
 * This file was generated by the Gradle 'init' task.
 *
 * This project uses @Incubating APIs which are subject to change.
 */

version = "0.1.0"

plugins {
    // Apply the common convention plugin for shared build configuration between library and application projects.
    id("idl.poc.java-common-conventions")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("com.google.cloud.tools.jib")
}

val libs = project.extensions.getByType<VersionCatalogsExtension>().named("libs")


dependencies {
    libs.findLibrary("springboot.web").orElseThrow().apply {
        implementation(this)
    }

    libs.findLibrary("springboot.actuator").orElseThrow().apply {
        implementation(this)
    }
}

tasks {
    build {
        dependsOn(tasks.jib)
    }
}

jib {
    from {
        image = "openjdk:11-jre-slim"
    }
    to {
        image = "idl-poc-app"
        tags = setOf("latest", project.version.toString())
    }
    container {
        appRoot = "/simpleApp"
        jvmFlags = listOf("-Xms360m", "-Xmx360m")
        ports = listOf("8080")
        workingDirectory = "/simpleApp"
    }
}