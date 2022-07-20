package org.coderpwh.server

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * @author coderpwh
 * @date 2022-07-19 17:03
 * @version 1.0.0 v
 */
@MapperScan("org.coderpwh.server.mapper")
@SpringBootApplication
class ServerApplication

fun main() {
    runApplication<ServerApplication>()
}
