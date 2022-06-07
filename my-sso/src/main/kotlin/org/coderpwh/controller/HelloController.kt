package org.coderpwh.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author coderpwh
 * @date 2022-06-07 15:54
 * @version 1.0.0 v
 */
@RestController
class HelloController {
    @GetMapping("/hi")
    fun sayHi() = "hi"
}