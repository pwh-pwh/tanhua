package org.coderpwh.service

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper
import com.baomidou.mybatisplus.extension.kotlin.KtQueryChainWrapper
import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.apache.commons.codec.digest.DigestUtils
import org.apache.rocketmq.spring.core.RocketMQTemplate
import org.coderpwh.constant.RedisPrefix
import org.coderpwh.mapper.UserMapper
import org.coderpwh.pojo.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import java.time.Duration
import java.util.*

/**
 * @author coderpwh
 * @date 2022-06-07 16:57
 * @version 1.0.0 v
 */
@Service
class UserService {
    @Autowired
    private lateinit var userMapper: UserMapper

    @Autowired
    private lateinit var redisTemplate: RedisTemplate<String, String>
    private final val MAPPER = ObjectMapper()

    @Autowired
    private lateinit var rocketMQTemplate: RocketMQTemplate

    @Value("\${jwt.secret}")
    private lateinit var secret: String

    /**
     * 校验成功返回token，失败返回null
     */
    fun login(mobile: String, code: String): String? {
        var isNew = false
        //校验验证码是否正确
        var value = redisTemplate.opsForValue().get(RedisPrefix.CHECK_CODE + mobile)
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        if (!value.equals(code)) {
            return null
        }
        //查询是不是新注册的
        var wrapper = KtQueryWrapper(User::class.java)
            .eq(User::mobile, mobile)
        var selectUser = userMapper.selectOne(wrapper)
        if (selectUser == null) {
            var user = User(
                mobile = mobile,
                password = DigestUtils.md5Hex(secret + "_123456")
            )
            userMapper.insert(user)
            selectUser = user
            isNew = true
        }
        var map = mutableMapOf<String, Any>()
        map["id"] = selectUser.id!!
        map["mobile"] = mobile

        var token = Jwts.builder()
            .setClaims(map)
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact()
        val redisTokenKey = RedisPrefix.TOKEN + token
        try {
            redisTemplate.opsForValue().set(
                redisTokenKey,
                MAPPER.writeValueAsString(selectUser), Duration.ofHours(1)
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }

        try {
            var msg = mutableMapOf<String, Any>()
            msg["userId"] = selectUser.id!!
            msg["date"] = Date()
            rocketMQTemplate.convertAndSend("tanhua-sso-login", msg)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return "$isNew|$token"
    }

}