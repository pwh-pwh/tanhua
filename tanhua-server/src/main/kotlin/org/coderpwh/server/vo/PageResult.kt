package org.coderpwh.server.vo

/**
 * @author coderpwh
 * @date 2022-07-25 16:32
 * @version 1.0.0 v
 */
data class PageResult(
    var counts:Int?=null,
    var pagesize:Int?=null,
    var pages:Int?=null,
    var page:Int?=null,
    var items:List<Any>?=null
)
