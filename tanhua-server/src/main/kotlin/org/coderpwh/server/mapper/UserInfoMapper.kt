package org.coderpwh.server.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper
import org.coderpwh.pojo.UserInfo

/**
 * @author coderpwh
 * @date 2022-06-08 16:41
 * @version 1.0.0 v
 */
@Mapper
interface UserInfoMapper:BaseMapper<UserInfo>