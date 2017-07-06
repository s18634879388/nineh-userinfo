package com.ninehcom.userinfo.controller;

import com.ninehcom.common.util.Result;
import com.ninehcom.userinfo.service.UserActionService;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
//import com.ninehcom.userinfo.jpa.repository.UserActionRepository;

/**
 * UserAction 的控制器，用于显示同时查询2个数据库的结果 * @author shenjizhe
 *
 * @version 1.0.0
 */
@RestController
@RequestMapping(value = "/users")
public class UserActionController {

    @Autowired
    UserActionService userActionService;


    @ApiOperation(value = "签到", notes = "用户签到", position = 1)
    @ApiResponse(code = 20102, message = "用户签到数据添加失败")
    @RequestMapping(value = "/signin/{userId}", method = RequestMethod.POST)
    @ResponseBody
    public Result doSign(@PathVariable("userId") String userId,
                         @RequestParam(value = "date", required = false)
                         @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,@RequestHeader(value = "appId") String appId)
    {
        if (date == null) {
            date = new Date();
        }
        date = new Date();
        return userActionService.doSign(userId,date,appId);
    }
    @ApiOperation(value = "用户补签", notes = "用户补签", position = 1)
    @ApiResponse(code = 20154, message = "用户补签数据添加失败")
    @RequestMapping(value = "/doAddSign/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public Result doAddSign(@PathVariable("userId") String userId,
                            @RequestParam(value = "date", required = true)
                            @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,@RequestHeader(value = "appId") String appId){
        if (date == null) {
            date = new Date();
        }
        return userActionService.doAddSign(userId, date,appId);
    }

    @ApiOperation(value = "取得用户签到", notes = "按照月份取得用户签到", position = 2)
    @RequestMapping(value = "/getsignins/{userId}/{year}/{month}", method = RequestMethod.GET)
    @ResponseBody
    public Result getUserActionByMonth(
            @PathVariable("userId") String userId,
            @PathVariable("year") int year,
            @PathVariable("month") int month,@RequestHeader(value = "appId") String appId) {
        return userActionService.getUserActionByMonth(userId, year, month);
    }

    @ApiOperation(value = "判断用户是否签到", notes = "按照日期判断", position = 2)
    @RequestMapping(value = "/getsignins/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public Result getUserActionByDate(
            @PathVariable("userId") String userId,
            @RequestParam(value = "date", required = false) Date date,@RequestHeader(value = "appId") String appId) {
        if (date == null) {
            date = new Date();
        }
        return userActionService.getUserActionByDate(userId, date);
    }


}
