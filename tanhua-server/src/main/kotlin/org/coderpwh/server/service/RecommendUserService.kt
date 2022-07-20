package org.coderpwh.server.service

import com.alibaba.dubbo.config.annotation.Reference
import org.apache.dubbo.config.annotation.DubboReference
import org.coderpwh.api.RecommendUserApi
import org.coderpwh.server.vo.TodayBest
import org.springframework.stereotype.Service

/**
 * @author coderpwh
 * @date 2022-07-19 17:26
 * @version 1.0.0 v
 */
@Service
class RecommendUserService {

    @DubboReference
    lateinit var recommendUserApi: RecommendUserApi

    fun queryTodayBest(id: Long): TodayBest? {
        var recommendUser = recommendUserApi.queryWithMaxScore(id)
        return TodayBest(
            fateValue = recommendUser.score!!.toLong(),
            id = recommendUser.userId
        )
    }

}