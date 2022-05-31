repositories {
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-redis
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
//rocketMq
    implementation("org.apache.rocketmq:rocketmq-client")
    implementation("org.apache.rocketmq:rocketmq-spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")

    implementation("com.baomidou:mybatis-plus-boot-starter")
    implementation("org.mongodb:mongodb-driver-sync")
    implementation("org.apache.commons:commons-lang3")
    implementation("com.fasterxml.jackson.core:jackson-core")
    implementation("com.fasterxml.jackson.core:jackson-databind")
    implementation("com.alibaba:druid")
    implementation("commons-codec:commons-codec")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}