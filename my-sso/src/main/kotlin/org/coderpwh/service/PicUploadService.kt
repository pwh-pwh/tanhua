package org.coderpwh.service

import com.aliyun.oss.OSS
import com.aliyun.oss.OSSClient
import org.apache.commons.lang3.StringUtils
import org.coderpwh.config.AliyunConfig
import org.coderpwh.vo.PicUploadResult
import org.joda.time.DateTime
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import kotlin.random.Random

/**
 * @author coderpwh
 * @date 2022-06-08 11:23
 * @version 1.0.0 v
 */
@Service
class PicUploadService {
    val IMAGE_TYPE: Array<String> = arrayOf(
        ".bmp", ".jpg", ".jpeg", ".gif", ".png"
    )

    @Autowired
    private lateinit var ossClient: OSS

    @Autowired
    private lateinit var aliyunConfig: AliyunConfig
    fun upload(uploadFile: MultipartFile): PicUploadResult {
        var picUploadResult = PicUploadResult()
        var isLegal = false
        for (s in IMAGE_TYPE) {
            if (StringUtils.endsWithIgnoreCase(
                    uploadFile.originalFilename,
                    s
                )
            ) {
                isLegal = true
                break
            }
        }
        if (!isLegal) {
            picUploadResult.status = "error"
            return picUploadResult
        }
        var fileName = uploadFile.originalFilename
        var filePath = getFilePath(fileName)
        try {
            ossClient.putObject(
                aliyunConfig.bucketName,
                filePath, uploadFile.inputStream
            )
        } catch (e: Exception) {
            e.printStackTrace()
            picUploadResult.status = "error"
            return picUploadResult
        }
        picUploadResult.status = "done"
        picUploadResult.name = aliyunConfig.urlPrefix + filePath
        picUploadResult.uid = System.currentTimeMillis().toString()
        return picUploadResult
    }

    fun getFilePath(sourceFileName: String): String {
        var dateTime = DateTime()
        return """
            |images/${dateTime.toString("yyyy")}/${dateTime.toString("MM")}/${dateTime.toString("dd")}/${System.currentTimeMillis()}${Random.nextInt(100, 9999)}.${StringUtils.substringAfterLast(sourceFileName, ".")}
        """.trimMargin("|")
    }


}


