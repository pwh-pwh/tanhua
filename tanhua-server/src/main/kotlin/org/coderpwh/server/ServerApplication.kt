package org.coderpwh.server

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo
import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration
import org.springframework.boot.runApplication

/**
 * @author coderpwh
 * @date 2022-07-19 17:03
 * @version 1.0.0 v
 */
@MapperScan("org.coderpwh.server.mapper")
@SpringBootApplication(exclude = arrayOf(MongoAutoConfiguration::class,MongoDataAutoConfiguration::class))
@EnableDubbo
class ServerApplication

fun main() {
    runApplication<ServerApplication>()
}
