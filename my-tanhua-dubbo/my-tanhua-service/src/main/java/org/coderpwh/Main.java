package org.coderpwh;

import org.apache.commons.lang3.RandomUtils;

/**
 * @author coderpwh
 * @version 1.0.0 v
 * @date ${YEAR}-${MONTH}-${DAY} ${TIME}
 */
public class Main {
    public static void main(String[] args) {
        for (int i = 2; i < 100; i++) {
            int score = RandomUtils.nextInt(30, 99);
            System.out.println("db.recommend_user.insertOne({\"userId\":" + i +
                    ",\"toUserId\":1,\"score\":"+score+",\"date\":\"2019/1/1\"})");
        }
    }
}