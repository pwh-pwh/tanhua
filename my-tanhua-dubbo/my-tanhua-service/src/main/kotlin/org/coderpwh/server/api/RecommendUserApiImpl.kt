package org.coderpwh.server.api

import com.alibaba.dubbo.config.annotation.Service
import org.coderpwh.server.entry.RecommendUser
import org.coderpwh.server.vo.PageInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query

/**
 * @author coderpwh
 * @date 2022-06-27 17:50
 * @version 1.0.0 v
 */
@Service
class RecommendUserApiImpl:RecommendUserApi {
    @Autowired
    lateinit var mongoTemplate: MongoTemplate
    override fun queryWithMaxScore(userId: Long): RecommendUser {
        var query = Query.query(Criteria.where("toUserId").`is`(userId)).with(Sort.by(Sort.Order.desc("score"))).limit(1)
        return mongoTemplate.findOne(query, RecommendUser::class.java)!!
    }

    override fun queryPageInfo(userId: Long, pageNum: Int, pageSize: Int): PageInfo<RecommendUser> {
        TODO("Not yet implemented")
    }
}