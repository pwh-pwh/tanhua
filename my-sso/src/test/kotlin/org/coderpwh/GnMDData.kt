package org.coderpwh

import org.junit.jupiter.api.Test
import kotlin.random.Random

/**
 * @author coderpwh
 * @date 2022-06-27 15:59
 * @version 1.0.0 v
 */

class GnMDData {
    @Test
    fun gdata() {
        for (id in 2 until 100) {
            var score = Random(1).nextInt(30, 99)
            var str = """
                db.recommend_user.insert({"userId":$id,"toUserId":1,"score":$score,"date":"2019/1/1"})
            """.trimIndent()
            println(str)
        }
    }
}