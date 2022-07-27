package org.coderpwh.server.vo

/**
 * @author coderpwh
 * @date 2022-07-25 16:16
 * @version 1.0.0 v
 */
data class RecommendUserQueryParam(
    var page: Int = 1,
    var pagesize: Int = 10,
    var gender: String? = null,
    var lastLogin: String? = null,
    var age: Int? = null,
    var city: String? = null,
    var education: String? = null
)
