package org.coderpwh.server.service

import org.apache.commons.lang3.StringUtils
import org.coderpwh.pojo.User
import org.coderpwh.server.vo.TodayBest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import kotlin.properties.Delegates

/**
 * @author coderpwh
 * @date 2022-07-19 17:25
 * @version 1.0.0 v
 */
@Service
class TodayBestService {
    @Autowired
    lateinit var userService: UserService
    @Autowired
    lateinit var userInfoService: UserInfoService

    var defaultUserId :Long = 2L

    @Autowired
    lateinit var recommendUserService: RecommendUserService
    fun queryTodayBest(token: String): TodayBest? {
        var user = userService.queryByToken(token) ?: return null
        var todayBest = recommendUserService.queryTodayBest(user.id!!)
        if (todayBest == null) {
            todayBest = TodayBest(
                id = defaultUserId,
                fateValue = 95
            )
        }
        var userInfo = userInfoService.queryById(todayBest.id!!)
        with(todayBest) {
            age = userInfo.age
            avatar = userInfo.logo
            gender = userInfo.sex!!.name
            nickname = userInfo.nickName
            tags = StringUtils.split(userInfo.tags,',')
        }
        return todayBest
    }

}