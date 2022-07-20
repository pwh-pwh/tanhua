package org.coderpwh.pojo

import com.baomidou.mybatisplus.annotation.FieldFill
import com.baomidou.mybatisplus.annotation.TableField
import java.util.Date

/**
 * @author coderpwh
 * @date 2022-06-06 17:32
 * @version 1.0.0 v
 */
abstract class BasePojo {
    @TableField(fill = FieldFill.INSERT)
    var created: Date? = null
    @TableField(fill = FieldFill.INSERT_UPDATE)
    var updated: Date? = null
}