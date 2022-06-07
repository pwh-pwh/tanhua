package org.coderpwh.pojo

import org.coderpwh.enums.SexEnum

/**
 * @author coderpwh
 * @date 2022-06-06 17:37
 * @version 1.0.0 v
 */
data class UserInfo(
    val id: Long,
    val userId: Long,
    val nickName: String,
    val logo: String,
    val tags: String,
    val sex: SexEnum,
    val age: Int,
    val edu: String,
    val city: String,
    val birthday: String,
    val coverPic: String,
    val industry: String,
    val income: String,
    val marriage: String
) : BasePojo()

