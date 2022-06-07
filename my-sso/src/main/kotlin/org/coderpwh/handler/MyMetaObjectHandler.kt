package org.coderpwh.handler

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler
import org.apache.ibatis.reflection.MetaObject
import org.springframework.stereotype.Component
import java.time.LocalTime
import java.util.Date

/**
 * @author coderpwh
 * @date 2022-06-06 17:47
 * @version 1.0.0 v
 */
@Component
class MyMetaObjectHandler:MetaObjectHandler {
    override fun insertFill(metaObject: MetaObject) {
        this.fillStrategy(metaObject,"created",Date())
    }

    override fun updateFill(metaObject: MetaObject) {
        this.fillStrategy(metaObject,"updated", Date())
    }
}