package org.coderpwh

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * @author coderpwh
 * @date 2022-06-27 19:07
 * @version 1.0.0 v
 */
@SpringBootApplication
@EnableDubbo
class DubboServiceApplication

fun main() {
    EmbeddedZooKeeper(2181, false).start()
    runApplication<DubboServiceApplication>()
}