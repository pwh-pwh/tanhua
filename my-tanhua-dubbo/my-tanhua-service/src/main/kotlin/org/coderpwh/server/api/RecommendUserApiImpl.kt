package org.coderpwh.server.api

import com.alibaba.dubbo.config.annotation.Service
import org.apache.dubbo.config.annotation.DubboService
import org.coderpwh.api.RecommendUserApi
import org.coderpwh.entry.RecommendUser
import org.coderpwh.server.vo.PageInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query

/**
 * @author coderpwh
 * @date 2022-06-27 17:50
 * @version 1.0.0 v
 */
@DubboService
class RecommendUserApiImpl: RecommendUserApi {
    @Autowired
    lateinit var mongoTemplate: MongoTemplate
    override fun queryWithMaxScore(userId: Long): RecommendUser {
        var query = Query.query(Criteria.where("toUserId").`is`(userId)).with(Sort.by(Sort.Order.desc("score"))).limit(1)
        var findOne = mongoTemplate.findOne(query, RecommendUser::class.java)!!
        return findOne
    }

    override fun queryPageInfo(userId: Long, pageNum: Int, pageSize: Int): PageInfo<RecommendUser> {
        val pageRequest = PageRequest.of(pageNum-1,pageSize,Sort.by(Sort.Order.desc("score")))
        var query = Query.query(Criteria.where("toUserId").`is`(userId)).with(pageRequest)
        var recommendUsers = mongoTemplate.find(query, RecommendUser::class.java)
        return PageInfo(0,pageNum,pageSize,recommendUsers)
    }
}