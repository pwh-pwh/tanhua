plugins {
    id("java")
}

group = "org.coderpwh"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}



tasks.getByName<Test>("test") {
    useJUnitPlatform()
}