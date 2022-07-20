package org.coderpwh.server.service

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import org.coderpwh.pojo.UserInfo
import org.coderpwh.server.mapper.UserInfoMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author coderpwh
 * @date 2022-07-19 17:27
 * @version 1.0.0 v
 */
@Service
class UserInfoService {
    @Autowired
    lateinit var userInfoMapper: UserInfoMapper

    fun queryById(id:Long):UserInfo {
        return userInfoMapper.selectById(id)
    }

}