package com.ninehcom.userinfo.controller;

import com.ninehcom.common.util.Result;
import com.ninehcom.userinfo.entity.UserScore;
import com.ninehcom.userinfo.service.UserScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
//import com.ninehcom.userinfo.jpa.repository.UserScoreRepository;

/**
 * UserScore 的控制器，用于显示同时查询2个数据库的结果 * @author shenjizhe
 *
 * @version 1.0.0
 */
@RestController
@RequestMapping(value = "/users")
public class UserScoreController {

    @Autowired
    UserScoreService userScoreService;

    @RequestMapping(value = "/addscore", method = RequestMethod.POST)
    @ResponseBody
    public Result addScore(@RequestBody UserScore score,@RequestHeader(value = "appId") String appId) {
        return
                userScoreService.addScore(score.getUserid(),
                score.getDate(),
                score.getScore(),
                score.getExperience(),appId);
    }

    @RequestMapping(value = "/subscore", method = RequestMethod.POST)
    @ResponseBody
    public Result subScore(@RequestBody UserScore score,@RequestHeader(value = "appId") String appId) {
        return userScoreService.subScore(score.getUserid(),
                score.getDate(),
                score.getScore(),
                score.getExperience());
    }
}
