package org.coderpwh.pojo

import org.coderpwh.server.enums.SexEnum

/**
 * @author coderpwh
 * @date 2022-06-06 17:37
 * @version 1.0.0 v
 */
data class UserInfo(
    var id: Long?=null,
    var userId: Long?=null,
    var nickName: String?=null,
    var logo: String?=null,
    var tags: String?=null,
    var sex: SexEnum?=null,
    var age: Int?=null,
    var edu: String?=null,
    var city: String?=null,
    var birthday: String?=null,
    var coverPic: String?=null,
    var industry: String?=null,
    var income: String?=null,
    var marriage: String?=null
) : BasePojo()

