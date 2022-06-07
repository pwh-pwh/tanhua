package org.coderpwh.pojo

import com.fasterxml.jackson.annotation.JsonIgnore

/**
 * @author coderpwh
 * @date 2022-06-06 17:34
 * @version 1.0.0 v
 */
data class User(
    var id: Long? = null,
    var mobile: String? = null,
    @JsonIgnore
    var password: String? = null
) : BasePojo()