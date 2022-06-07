package org.coderpwh.service

import org.apache.commons.lang3.StringUtils
import org.coderpwh.constant.RedisPrefix
import org.coderpwh.constant.RedisPrefix.CHECK_CODE
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisKeyValueTemplate
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.time.Duration

/**
 * @author coderpwh
 * @date 2022-06-06 20:21
 * @version 1.0.0 v
 */
@Service
class SmsService {
    private final val LOGGER = LoggerFactory.getLogger(SmsService::class.java)
    @Autowired
    private lateinit var redisTemplate:RedisTemplate<String,String>

    fun sendCheckCode(mobile:String):Map<String,Any> {
        var result = mutableMapOf<String, Any>()
        try {
            var redisKey = CHECK_CODE + mobile
            var value = redisTemplate.opsForValue().get(redisKey)
            if(StringUtils.isNotEmpty(value)) {
                result["code"] = 1
                result["msg"] = "上次验证码未失效"
                return result
            }
            var code = sendSms(mobile)
            if (code==null) {
                result["code"] = 2
                result["msg"] = "发送短信验证码失败"
            }
            result["code"] = 3
            result["msg"] = "ok"
            redisTemplate.opsForValue().set(redisKey,code, Duration.ofMinutes(1))
            return result
        }catch (e:Exception) {
            LOGGER.error("send code msg error")
            result["code"] = 4
            result["msg"] = "send code msg error"
            return result
        }
    }

    fun sendSms(mobile:String):String? {
        if (mobile.length!=11) {
            return null
        }
        return mobile.substring(mobile.length-6,mobile.length)
    }

}