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
//指定版本
configurations.all {
    resolutionStrategy{
        force(
            "mysql:mysql-connector-java:${mysqlVersion}",
            "com.fasterxml.jackson.core:jackson-core:$jacksonVersion",
            "com.fasterxml.jackson.core:jackson-databind:$jacksonVersion",
            "com.alibaba:druid:$druidVersion",
            "commons-codec:commons-codec:1.15",
            "org.apache.commons:commons-lang3:3.12.0",
            "org.mongodb:mongodb-driver-sync:4.6.0",
            "com.baomidou:mybatis-plus-boot-starter:3.5.1",
            "org.apache.rocketmq:rocketmq-client:$rocketmqClientVersion",
            "org.apache.rocketmq:rocketmq-spring-boot-starter:$rocketmqStarterVersion",
            "commons-codec:commons-codec:1.15",
            "org.springframework.boot:spring-boot-starter-data-redis:2.7.0"
        )
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