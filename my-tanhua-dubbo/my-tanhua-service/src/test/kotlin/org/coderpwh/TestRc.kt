package org.coderpwh


import org.coderpwh.api.RecommendUserApi
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

/**
 * @author coderpwh
 * @date 2022-06-27 18:05
 * @version 1.0.0 v
 */
@SpringBootTest
class TestRc {
    @Autowired
    lateinit var recommendUserApi: RecommendUserApi
    @Test
    fun testQu() {
        var recommendUser = recommendUserApi.queryWithMaxScore(1L)
        println(recommendUser)
        println("aa")
    }

    @Test
    fun testQuList() {
        recommendUserApi.queryPageInfo(1L,1,10).records.forEach(::println)
    }

}