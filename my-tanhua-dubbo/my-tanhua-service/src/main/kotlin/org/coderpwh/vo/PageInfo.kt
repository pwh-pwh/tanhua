package org.coderpwh.server.vo

import java.util.Collections

/**
 * @author coderpwh
 * @date 2022-06-27 17:36
 * @version 1.0.0 v
 */
data class PageInfo<T>(
    var total:Int,
    var pageNum:Int,
    var pageSize:Int,
    var records:List<T> = Collections.emptyList()
)
