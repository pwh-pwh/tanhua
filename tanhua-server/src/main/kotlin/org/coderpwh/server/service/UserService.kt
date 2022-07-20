package org.coderpwh.server.service

import org.coderpwh.pojo.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

/**
 * @author coderpwh
 * @date 2022-07-19 17:26
 * @version 1.0.0 v
 */
@Service
class UserService {
    @Autowired
    lateinit var restTemplate: RestTemplate
    @Value("\${tanhua.sso.url}")
    lateinit var ssoUrl:String

    /**
     * 调用sso系统查询
     */
    fun queryByToken(token: String): User? {
        return restTemplate.getForObject("$ssoUrl/user/$token", User::class.java)
    }

}