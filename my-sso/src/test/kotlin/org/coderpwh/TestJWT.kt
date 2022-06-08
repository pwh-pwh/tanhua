package org.coderpwh

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest

/**
 * @author coderpwh
 * @date 2022-06-07 16:30
 * @version 1.0.0 v
 */
@SpringBootTest
class TestJWT {
    @Value("\${jwt.secret}")
    lateinit var secret:String

    @Test
    fun `get start for jwt`() {
        var map = mutableMapOf<String, Any>()
        map["mobile"] = "10086"
        map["id"] = "2"
        var jwt = Jwts.builder()
            .setClaims(map)
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact()
        println("build jwt:$jwt")

        var body = Jwts.parser()
            .setSigningKey(secret)
            .parseClaimsJws(jwt)
            .body
        println(body)
    }

}