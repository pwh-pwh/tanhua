package org.coderpwh.server

import org.coderpwh.server.service.TodayBestService
import org.coderpwh.server.vo.RecommendUserQueryParam
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

/**
 * @author coderpwh
 * @date 2022-07-20 14:17
 * @version 1.0.0 v
 */
@SpringBootTest
class TestToday {

    @Autowired
    lateinit var todayBestService: TodayBestService
    @Test
    fun testTd() {
        var todayBest =
            todayBestService.queryTodayBest("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwibW9iaWxlIjoiMTc2MDIwMjY4NjgifQ.AgcbMhqF0llgfcUffjrpAVPjwL-OWwwOibUHZZ_7nbI")
        println(todayBest)
        println("hello")

    }

    @Test
    fun testList() {
        var token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwibW9iaWxlIjoiMTc2MDIwMjY4NjgifQ.AgcbMhqF0llgfcUffjrpAVPjwL-OWwwOibUHZZ_7nbI"
        todayBestService.queryRecommendUserList(RecommendUserQueryParam(1,10),token)?.items?.forEach { println(it) }
    }

}