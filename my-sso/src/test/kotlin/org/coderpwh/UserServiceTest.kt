package org.coderpwh

import org.coderpwh.service.SmsService
import org.coderpwh.service.UserService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

/**
 * @author coderpwh
 * @date 2022-06-07 20:28
 * @version 1.0.0 v
 */
@SpringBootTest
class UserServiceTest {
    @Autowired
    lateinit var userService: UserService
    @Autowired
    lateinit var smsService: SmsService
    @Test
    fun testsms() {
        var sendCheckCode = smsService.sendCheckCode("13226236738")
        println(sendCheckCode)
    }

    @Test
    fun test() {
       val result = userService.login(
           "13226236738",
           "236738"
       )
        println(result)
    }
}