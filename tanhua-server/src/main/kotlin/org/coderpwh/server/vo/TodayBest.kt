package org.coderpwh.server.vo

/**
 * @author coderpwh
 * @date 2022-07-19 17:22
 * @version 1.0.0 v
 */
data class TodayBest(
    var id: Long? = null,
    var avatar: String? = null,
    var nickname: String? = null,
    var gender: String? = null,
    var age: Int? = null,
    var tags: Array<String>? = null,
    var fateValue: Long? = null
)
