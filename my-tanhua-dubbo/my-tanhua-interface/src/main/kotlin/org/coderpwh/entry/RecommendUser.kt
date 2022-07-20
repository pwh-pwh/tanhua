package org.coderpwh.entry

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

/**
 * @author coderpwh
 * @date 2022-06-27 17:18
 * @version 1.0.0 v
 */
@Document(collection = "recommend_user")
data class RecommendUser(
    @Id
    var id:ObjectId?=null,
    @Indexed
    var userId: Long? = null,
    var toUserId: Long? = null,
    @Indexed
    var score: Double? = null,
    var date: String? = null
):java.io.Serializable
