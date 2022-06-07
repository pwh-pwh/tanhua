package org.coderpwh

import org.coderpwh.service.SmsService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

/**
 * @author coderpwh
 * @date 2022-06-06 20:47
 * @version 1.0.0 v
 */
@SpringBootTest
class TestSmsService {
    @Autowired
    lateinit var smsService: SmsService

    @Test
    fun testSendSms() {
        var sendSms = smsService.sendSms("13226236738")
        println(sendSms)
    }
}