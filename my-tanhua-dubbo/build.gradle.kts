repositories {
    mavenCentral()
}

dependencies {

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