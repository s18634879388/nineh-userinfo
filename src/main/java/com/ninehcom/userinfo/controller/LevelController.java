package com.ninehcom.userinfo.controller;

import com.ninehcom.common.util.Result;
import com.ninehcom.userinfo.service.LevelService;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
//import com.ninehcom.userinfo.jpa.repository.LevelRepository;

/**
 * Level 的控制器，用于显示同时查询2个数据库的结果 * @author shenjizhe
 *
 * @version 1.0.0
 */
@RestController
@RequestMapping(value = "/users")
public class LevelController {

    @Autowired
    LevelService levelService;
//    @Autowired
//    LevelRepository LevelRepository;

    @ApiOperation(value = "等级", notes = "用户等级列表", position = 1)
    @RequestMapping(value = "/levels", method = RequestMethod.GET)
    @ResponseBody
    public Result getLevels(@RequestHeader(value = "appId") String appId) {
        return levelService.getLevels();
    }

    @ApiOperation(value = "群发短信", notes = "群发短信", position = 2)
    @RequestMapping(value = "/sendgroup/{appId}", method = RequestMethod.GET)
    @ResponseBody
    public Result sendGroup(@PathVariable("appId") String appid,@RequestHeader(value = "appId") String appId) throws Exception {
        return levelService.selectgroup(appid);
    }
}
