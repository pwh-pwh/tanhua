package org.coderpwh.controller

import org.coderpwh.service.SmsService
import org.coderpwh.vo.ErrorResult
import org.coderpwh.vo.LoginVO
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author coderpwh
 * @date 2022-06-07 14:05
 * @version 1.0.0 v
 */
@RestController
@RequestMapping("user")
class SmsController {
    @Autowired
    lateinit var smsService: SmsService
    final val LOGGER = LoggerFactory.getLogger(SmsController::class.java)
    @PostMapping("/login")
    fun sendCheckCode(
        @RequestBody
        loginVO: LoginVO
    ): ResponseEntity<Any> {
        var resultError = ErrorResult("000000","发送验证码失败")
        try {
            val (phone) = loginVO
            var sendCheckCode = smsService.sendCheckCode(phone)
            var c_code = sendCheckCode["code"] as Int
            if (c_code==3) {
                return ResponseEntity.ok(null)
            } else if (c_code==1) {
                resultError.errCode = "000001"
                resultError.errMessage = sendCheckCode["msg"] as String
            }
        } catch (e:Exception) {
            LOGGER.error("发送验证码失败",e)
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultError)
    }
}