package org.coderpwh.controller

import org.coderpwh.service.UserInfoService
import org.coderpwh.vo.ErrorResult
import org.coderpwh.vo.UserInfoVO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

/**
 * @author coderpwh
 * @date 2022-06-08 17:28
 * @version 1.0.0 v
 */
@RestController
@RequestMapping("user")
class UserInfoController {
    @Autowired
    private lateinit var userInfoService: UserInfoService

    @PostMapping("loginReginfo")
    fun saveUseInfo(
        @RequestBody
        userInfoVO: UserInfoVO,
        @RequestHeader("Authorization")
        token: String
    ): ResponseEntity<Any> {
        var saveUserInfo = userInfoService.saveUserInfo(userInfoVO, token)
        if (saveUserInfo) {
            return ResponseEntity.ok(null)
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ErrorResult("000000", "发生错误"))
    }

    @PostMapping("loginReginfo/head")
    fun saveLogo(
        @RequestParam("headPhoto")
        file: MultipartFile,
        @RequestHeader("Authorization")
        token: String
    ): ResponseEntity<Any> {
        var bool = userInfoService.saveLogo(file, token)
        if (bool) {
            return ResponseEntity.ok(null)
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ErrorResult("000000", "图片非人像，请重新上传"))
    }

}