package org.coderpwh.service

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import org.apache.ibatis.annotations.Mapper
import org.coderpwh.enums.SexEnum
import org.coderpwh.mapper.UserInfoMapper
import org.coderpwh.pojo.UserInfo
import org.coderpwh.vo.UserInfoVO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

/**
 * @author coderpwh
 * @date 2022-06-08 16:57
 * @version 1.0.0 v
 */
@Service
class UserInfoService {
    @Autowired
    private lateinit var userInfoMapper: UserInfoMapper

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var faceEngineService: FaceEngineService

    @Autowired
    private lateinit var picUploadService: PicUploadService

    fun saveUserInfo(userInfoVO: UserInfoVO, token: String): Boolean {
        var user = userService.queryUserByToken(token)
        if (user == null) {
            return false
        }
        var userInfo = UserInfo()
        with(userInfo) {
            userId = user.id
            sex = if (userInfoVO.gender.equals("man")) SexEnum.MAN else SexEnum.WOMAN
            nickName = userInfoVO.nickname
            birthday = userInfoVO.birthday
            city = userInfoVO.city
        }
        userInfoMapper.insert(userInfo)
        return true
    }

    fun saveLogo(file: MultipartFile, token: String): Boolean {
        var user = userService.queryUserByToken(token)
        if (user == null) {
            return false
        }
        /*try {
            var isPortrait = faceEngineService.detectFace(file.bytes)
            if (!isPortrait) {
                return false
            }
        } catch (e: Exception) {
            println("检测人像照片出错")
            return false
        }*/
        var uploadResult = picUploadService.upload(file)
        var userInfo = UserInfo()
        userInfo.logo = uploadResult.name
        var queryWrapper = KtQueryWrapper(UserInfo::class.java)
            .eq(UserInfo::userId, user.id)
        userInfoMapper.update(userInfo, queryWrapper)
        return true
    }
}