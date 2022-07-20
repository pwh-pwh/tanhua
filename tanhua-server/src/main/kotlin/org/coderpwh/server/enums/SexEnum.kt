package org.coderpwh.server.enums

import com.baomidou.mybatisplus.annotation.EnumValue
import com.baomidou.mybatisplus.annotation.IEnum

/**
 * @author coderpwh
 * @date 2022-06-06 17:26
 * @version 1.0.0 v
 */
enum class SexEnum(
    @EnumValue
    val value:Int,
    val desc:String) {
    MAN(1,"男"),
    WOMAN(2,"女"),
    UNKNOWN(3,"未知")
    ;

}