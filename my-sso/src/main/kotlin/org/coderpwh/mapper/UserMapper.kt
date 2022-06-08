package org.coderpwh.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper
import org.coderpwh.pojo.User

/**
 * @author coderpwh
 * @date 2022-06-06 17:53
 * @version 1.0.0 v
 */
@Mapper
interface UserMapper: BaseMapper<User>