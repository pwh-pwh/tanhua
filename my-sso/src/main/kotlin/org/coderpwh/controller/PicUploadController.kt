package org.coderpwh.controller

import org.coderpwh.service.PicUploadService
import org.coderpwh.vo.PicUploadResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

/**
 * @author coderpwh
 * @date 2022-06-08 11:45
 * @version 1.0.0 v
 */
@RestController
@RequestMapping("pic/upload")
class PicUploadController {
    @Autowired
    private lateinit var picUploadService: PicUploadService
    @PostMapping
    fun upload(
        @RequestParam("file")
        multipartFile: MultipartFile
    ):PicUploadResult {
        return picUploadService.upload(multipartFile)
    }
}