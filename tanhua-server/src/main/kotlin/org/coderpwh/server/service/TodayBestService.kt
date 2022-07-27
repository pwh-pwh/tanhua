package org.coderpwh.server.service

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import org.apache.commons.lang3.StringUtils
import org.coderpwh.entry.RecommendUser
import org.coderpwh.pojo.UserInfo
import org.coderpwh.server.vo.PageResult
import org.coderpwh.server.vo.RecommendUserQueryParam
import org.coderpwh.server.vo.TodayBest
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

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

    var defaultUserId: Long = 2L

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
            tags = StringUtils.split(userInfo.tags, ',')
        }
        return todayBest
    }

    fun queryRecommendUserList(queryParam: RecommendUserQueryParam, token: String): PageResult? {
        var user = userService.queryByToken(token) ?: return null
        var pageInfo = recommendUserService.queryRecommendUserList(user.id!!, queryParam.page, queryParam.pagesize)
        var wrapper = KtQueryWrapper(UserInfo::class.java)
            .`in`(UserInfo::userId, pageInfo.records.map(RecommendUser::userId))
            .func(queryParam.gender != null, {
                it.eq(UserInfo::sex, if (queryParam.gender!!.equals("man")) 1 else 2)
            })
            .eq(queryParam.city != null, UserInfo::city, queryParam.city)
            .lt(queryParam.age != null, UserInfo::age, queryParam.age)
        var userInfos = userInfoService.queryUserInfoList(wrapper)
        var todayBests = userInfos.map {
            var todayBest = TodayBest()
            BeanUtils.copyProperties(it, todayBest)
            todayBest.nickname = it.nickName
            todayBest.avatar = it.logo
            todayBest.gender = it.sex?.name
            todayBest.id = it.userId
            todayBest.tags = it.tags?.split(',')?.toTypedArray()
            return@map todayBest
        }
        return PageResult(0, queryParam.pagesize, 0, queryParam.page, todayBests)
    }

}