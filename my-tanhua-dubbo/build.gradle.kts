repositories {
    mavenCentral()
}

dependencies {

}
configurations.all {
    exclude(module = "slf4j-log4j12")
}
subprojects {
    tasks.jar {
        enabled = true
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}