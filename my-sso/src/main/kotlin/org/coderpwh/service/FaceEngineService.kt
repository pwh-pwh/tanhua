package org.coderpwh.service

import com.arcsoft.face.ActiveFileInfo
import com.arcsoft.face.AgeInfo
import com.arcsoft.face.EngineConfiguration
import com.arcsoft.face.FaceEngine
import com.arcsoft.face.FaceInfo
import com.arcsoft.face.FunctionConfiguration
import com.arcsoft.face.enums.DetectMode
import com.arcsoft.face.enums.DetectOrient
import com.arcsoft.face.enums.ErrorInfo
import com.arcsoft.face.toolkit.ImageInfo
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct
import com.arcsoft.face.toolkit.ImageFactory.*
import java.io.File

/**
 * @author coderpwh
 * @date 2022-06-08 15:23
 * @version 1.0.0 v
 */
@Service
class FaceEngineService {
    @Value("\${arcsoft.appid}")
    lateinit var appid: String

    @Value("\${arcsoft.sdkKey}")
    lateinit var sdkKey: String

    @Value("\${arcsoft.libPath}")
    lateinit var libPath: String

    lateinit var faceEngine: FaceEngine

    @PostConstruct
    fun initEngine() {
        faceEngine = FaceEngine(libPath)
        var errorCode = faceEngine.activeOnline(appid, sdkKey)
        if (errorCode != ErrorInfo.MOK.getValue() && errorCode != ErrorInfo.MERR_ASF_ALREADY_ACTIVATED.getValue()) {
            println("引擎激活失败");
            throw RuntimeException("引擎激活失败")
        }
        var activeFileInfo = ActiveFileInfo()
        errorCode = faceEngine.getActiveFileInfo(activeFileInfo)
        if (errorCode != ErrorInfo.MOK.getValue() && errorCode != ErrorInfo.MERR_ASF_ALREADY_ACTIVATED.getValue()) {
            println("获取激活文件信息失败");
            throw RuntimeException("获取激活文件信息失败")
        }
        //引擎配置
        var engineConfiguration = EngineConfiguration()
        engineConfiguration.detectMode = DetectMode.ASF_DETECT_MODE_IMAGE
        engineConfiguration.detectFaceOrientPriority = DetectOrient.ASF_OP_ALL_OUT
        engineConfiguration.detectFaceMaxNum = 10
        engineConfiguration.detectFaceScaleVal = 16
        //功能配置
        var functionConfiguration = FunctionConfiguration()
        with(functionConfiguration) {
            this.isSupportAge = true
            this.isSupportFace3dAngle = true
            this.isSupportFaceDetect = true
            this.isSupportFaceRecognition = true
            this.isSupportGender = true
            this.isSupportIRLiveness = true
            this.isSupportLiveness = true
        }
        engineConfiguration.functionConfiguration = functionConfiguration
        errorCode = faceEngine.init(engineConfiguration)
        if (errorCode != ErrorInfo.MOK.getValue()) {
            println("初始化引擎失败");
            throw RuntimeException("初始化引擎失败")
        }
    }

    fun getAge(file:File):Int {
        processFaceInfo(file)
        var ageInfos = mutableListOf<AgeInfo>()
        faceEngine.getAge(ageInfos)
        return if (ageInfos.size==0) -1 else ageInfos[0].age
    }

    fun processFaceInfo(file:File):MutableList<FaceInfo>{
        var faceInfos = getFaceInfoList(file)
        var imageInfo = getRGBData(file)
        faceEngine.process(
            imageInfo.imageData,
            imageInfo.width,
            imageInfo.height,
            imageInfo.imageFormat,
            faceInfos,
            FunctionConfiguration().apply {
                isSupportAge = true
                isSupportFace3dAngle = true
                isSupportGender = true
                isSupportLiveness = true
            }
        )
        return faceInfos
    }
    fun getFaceInfoList(file:File):MutableList<FaceInfo> {
        var imageInfo = getRGBData(file)
        var faceInfoList = mutableListOf<FaceInfo>()
        faceEngine.detectFaces(
            imageInfo.imageData,
            imageInfo.width,
            imageInfo.height,
            imageInfo.imageFormat,
            faceInfoList
        )
        return faceInfoList
    }

    fun detectFace(imageInfo: ImageInfo): Boolean {
        var faceInfoList = mutableListOf<FaceInfo>()
        faceEngine.detectFaces(
            imageInfo.imageData,
            imageInfo.width,
            imageInfo.height,
            imageInfo.imageFormat,
            faceInfoList
        )
        return faceInfoList.size != 0
    }

    fun detectFace(byteArray: ByteArray): Boolean {
        return detectFace(getRGBData(byteArray))
    }

    fun detectFace(file: File): Boolean {
        return detectFace(getRGBData(file))
    }


}





