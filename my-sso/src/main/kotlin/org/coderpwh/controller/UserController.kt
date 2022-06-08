package org.coderpwh.controller

import org.apache.commons.lang3.StringUtils
import org.coderpwh.pojo.User
import org.coderpwh.service.UserService
import org.coderpwh.vo.ErrorResult
import org.coderpwh.vo.LoginVDataVO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author coderpwh
 * @date 2022-06-07 17:29
 * @version 1.0.0 v
 */
@RestController
@RequestMapping("user")
class UserController {
    @Autowired
    lateinit var userService: UserService

    @PostMapping("loginVerification")
    fun login(
        @RequestBody
        loginVDataVO: LoginVDataVO
    ): ResponseEntity<Any> {
        try {
            var token = userService.login(
                mobile = loginVDataVO.phone,
                code = loginVDataVO.verificationCode
            )
            var result = mutableMapOf<String, Any>()
            if (StringUtils.isNotEmpty(token)) {
                var split = StringUtils.split(token, '|')
                val isNew = split[0]
                val tokenStr = split[1]
                result["isNew"] = isNew
                result["token"] = tokenStr
                return ResponseEntity.ok(result)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        var errorResult = ErrorResult("000000", "验证码错误")
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(errorResult)
    }

    @GetMapping("{token}")
    fun queryUserByToken(
        @PathVariable("token")
        token:String
    ): User? {
        return userService.queryUserByToken(token)
    }

}