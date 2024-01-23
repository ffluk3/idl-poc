
plugins {
    `maven-publish`
}

publishing {
    publications {
        create<MavenPublication>("bufconfig") {
            groupId = "build.buf"
            artifactId = "shared-buf-configuration"
            version = "0.1.0"
            artifact(file("buf.yaml"))
        }
    }
}