package org.coderpwh.enums

import com.baomidou.mybatisplus.annotation.IEnum

/**
 * @author coderpwh
 * @date 2022-06-06 17:26
 * @version 1.0.0 v
 */
enum class SexEnum(val value:Int,val desc:String):IEnum<Int> {
    MAN(1,"男"),
    WOMAN(2,"女"),
    UNKNOWN(3,"未知")
    ;

    override fun getValue(): Int {
        return this.value
    }

    override fun toString(): String {
        return this.desc
    }

}