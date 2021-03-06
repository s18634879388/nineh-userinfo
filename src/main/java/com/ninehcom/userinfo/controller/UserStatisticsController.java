package com.ninehcom.userinfo.controller;

import com.ninehcom.common.util.Result;
import com.ninehcom.userinfo.service.UserStatisticsService;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * UserStatistics 的控制器，用于显示同时查询2个数据库的结果 * @author shenjizhe
 *
 * @version 1.0.0
 */
@RestController
@RequestMapping(value = "/users")
public class UserStatisticsController {

    @Autowired
    UserStatisticsService userStatisticsService;

    /**
     * 取得统计信息
     * @param count
     * @return
     */
    @ApiOperation(value = "取得统计信息（排行榜）",notes = "取得统计信息（排行榜）")
    @RequestMapping(value = "/statisticslist/{count}", method = RequestMethod.GET)
    @ResponseBody
    public Result getUserStatisticsList(@PathVariable("count") int count,@RequestHeader(value = "appId") String appId) {
        return userStatisticsService.getUserStatisticsList(count);
    }

    /**
     * 取得个人统计信息
     * @param userId
     * @return 
     */
    @ApiOperation(value = "取得个人统计信息",notes = "取得个人统计信息")
    @RequestMapping(value = "/statistics/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public Result getUserStatisticsList(@PathVariable("userId") String userId,@RequestHeader(value = "appId") String appId) {
        return userStatisticsService.getUserStatistics(userId);
    }

    @ApiOperation(value = "更新用户发帖数量(提供给圈子服务器)",notes = "更新用户发帖数量(提供给圈子服务器)")
    @RequestMapping(value = "/statistics/{userId}",method = RequestMethod.PUT)
    public Result updateUserTopicCount(@PathVariable("userId") String userId,
                                       @RequestHeader(value = "appId") String appId,
                                       @RequestParam(value = "topicCount") int topicCount){
        return userStatisticsService.updateUserTopicCount(userId,topicCount);
    }
}
