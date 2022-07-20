import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val mysqlVersion = "8.0.28"
val jacksonVersion by extra("2.13.3")
val druidVersion = "1.2.10"
val rocketmqClientVersion = "4.9.3"
val rocketmqStarterVersion = "2.2.2"


plugins {
    kotlin("jvm") version "1.6.20"
    id("org.springframework.boot") version "2.3.7.RELEASE"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    kotlin("plugin.spring") version "1.6.20"
}



subprojects {
    group = "org.coderpwh"
    version = "1.0-SNAPSHOT"

    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "kotlin")
    apply(plugin = "kotlin-spring")
    dependencies {
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        testImplementation("org.springframework.boot:spring-boot-starter-test") {
            exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
        }
    }


}

project("my-tanhua-dubbo:my-tanhua-service") {

    dependencies {

// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter
        implementation("org.springframework.boot:spring-boot-starter")
// https://mvnrepository.com/artifact/org.apache.dubbo/dubbo-spring-boot-starter
        implementation("org.apache.dubbo:dubbo-spring-boot-starter:3.0.9")

// https://mvnrepository.com/artifact/org.apache.dubbo/dubbo-dependencies-zookeeper
        api("org.apache.dubbo:dubbo-dependencies-zookeeper:3.0.9")
// https://mvnrepository.com/artifact/org.apache.dubbo/dubbo
        implementation("org.apache.dubbo:dubbo:3.0.9")


        // https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-mongodb
        api("org.springframework.boot:spring-boot-starter-data-mongodb:2.7.1")
/*    // https://mvnrepository.com/artifact/org.mongodb/mongodb-driver-sync
    implementation("org.mongodb:mongodb-driver-sync:3.9.0")*/
        implementation("org.apache.commons:commons-lang3:3.12.0")
        implementation("joda-time:joda-time:2.9.9")
        // https://mvnrepository.com/artifact/io.netty/netty-all
        implementation("io.netty:netty-all:4.1.32.Final")

        api(project(":my-tanhua-dubbo:my-tanhua-interface"))
    }


}


project("my-tanhua-dubbo:my-tanhua-interface") {
    dependencies {
//        api(project(":my-tanhua-dubbo:my-tanhua-service"))
        // https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-mongodb
        implementation("org.springframework.boot:spring-boot-starter-data-mongodb:2.7.1")
    }
}

group = "org.coderpwh"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}