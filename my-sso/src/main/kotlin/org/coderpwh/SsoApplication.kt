package org.coderpwh

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * @author coderpwh
 * @date 2022-06-06 17:53
 * @version 1.0.0 v
 */
@MapperScan("org.coderpwh.mapper")
@SpringBootApplication
class SsoApplication

fun main(args: Array<String>) {
    runApplication<SsoApplication>(*args)
}