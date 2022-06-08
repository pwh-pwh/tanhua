package org.coderpwh

import org.coderpwh.service.FaceEngineService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.io.File

/**
 * @author coderpwh
 * @date 2022-06-08 15:30
 * @version 1.0.0 v
 */
@SpringBootTest
class TestBfFIn {
    @Autowired
    lateinit var faceEngineService: FaceEngineService
    @Test
    fun `test `(){
        var file = File("D:\\tmp\\test.jpg")
/*        var flag = faceEngineService.detectFace(file) 加密
        println(flag)*/
        var age = faceEngineService.getAge(file)
        println(age)
    }
}