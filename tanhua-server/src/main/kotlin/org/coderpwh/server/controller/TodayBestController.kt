package org.coderpwh.server.controller

import org.coderpwh.server.service.TodayBestService
import org.coderpwh.server.vo.TodayBest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author coderpwh
 * @date 2022-07-19 17:26
 * @version 1.0.0 v
 */
@RestController
@RequestMapping("tanhua")
class TodayBestController {
    @Autowired
    lateinit var todayBestService: TodayBestService
    @GetMapping("todayBest")
    fun queryTodayBest(@RequestHeader("Authorization") token:String) = todayBestService.queryTodayBest(token)
}