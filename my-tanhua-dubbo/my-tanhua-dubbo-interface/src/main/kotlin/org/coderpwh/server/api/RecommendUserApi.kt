package org.coderpwh.server.api

import org.coderpwh.server.pojo.RecommendUser
import org.coderpwh.server.vo.PageInfo

/**
 * @author coderpwh
 * @date 2022-06-27 17:32
 * @version 1.0.0 v
 */
interface RecommendUserApi {
    fun queryWithMaxScore(userId:Long):RecommendUser
    fun queryPageInfo(userId: Long,pageNum:Int,pageSize:Int):PageInfo<RecommendUser>
}