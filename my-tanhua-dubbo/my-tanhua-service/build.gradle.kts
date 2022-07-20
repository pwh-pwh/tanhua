plugins {
    id("java")
}

group = "org.coderpwh"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

configurations.all {
    exclude(module = "slf4j-log4j12")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}