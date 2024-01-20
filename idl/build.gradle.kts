import com.google.protobuf.gradle.*

repositories {
    gradlePluginPortal()
}

plugins {
    `kotlin-dsl`
    id("com.google.protobuf") version "0.9.1"
}

dependencies {
    implementation(libs.google.protobuf)
    implementation(libs.google.protobuf.util)

}

protobuf {
    protoc {
        // The artifact spec for the Protobuf Compiler
        artifact = "com.google.protobuf:protoc:3.21.5"
    }
    plugins {
        // Optional: an artifact spec for a protoc plugin, with "grpc" as
        // the identifier, which can be referred to in the "plugins"
        // container of the "generateProtoTasks" closure.
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.15.1"
        }
    }
    generateProtoTasks {
        ofSourceSet("main").forEach {
            it.plugins {}
        }
    }
}