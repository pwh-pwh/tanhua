repositories {
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-redis
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
//rocketMq
    implementation("org.apache.rocketmq:rocketmq-client:4.9.3")
    implementation("org.apache.rocketmq:rocketmq-spring-boot-starter:2.2.2")
    implementation("org.springframework.boot:spring-boot-starter-web")

    // https://mvnrepository.com/artifact/com.baomidou/mybatis-plus
    implementation("com.baomidou:mybatis-plus-boot-starter:3.5.1")
//    implementation("org.mongodb:mongodb-driver-sync")
    implementation("org.apache.commons:commons-lang3")
    implementation("com.fasterxml.jackson.core:jackson-core")
    implementation("com.fasterxml.jackson.core:jackson-databind")
    implementation("com.alibaba:druid:1.2.10")
    implementation("commons-codec:commons-codec")
    implementation("mysql:mysql-connector-java")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}