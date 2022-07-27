package org.coderpwh.server.vo

import java.io.Serializable
import java.util.Collections

/**
 * @author coderpwh
 * @date 2022-06-27 17:36
 * @version 1.0.0 v
 */
data class PageInfo<T>(
    var total:Int?=null,
    var pageNum:Int?=null,
    var pageSize:Int?=null,
    var records:List<T> = Collections.emptyList()
):Serializable
