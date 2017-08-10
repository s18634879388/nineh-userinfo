package com.ninehcom.userinfo.controller;

import com.ninehcom.common.enums.ErrorCode;
import com.ninehcom.common.util.Result;
import com.ninehcom.userinfo.service.FeedbackService;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
//import com.ninehcom.userinfo.jpa.repository.FeedbackRepository;

/**
 * Feedback 的控制器，用于显示同时查询2个数据库的结果 * @author shenjizhe
 *
 * @version 1.0.0
 */
@RestController
@RequestMapping(value = "/users")
public class FeedbackController {

    @Autowired
    FeedbackService feedbackService;

    @ApiOperation(value = "取得所有用户反馈信息", notes = "取得所有用户反馈信息", position = 15)
    @ApiResponse(code = 20109, message = "取得用户反馈失败")
    @RequestMapping(value = "/feedbacks", method = RequestMethod.GET)
    @ResponseBody
    public Result selectFeedback(@RequestHeader(value = "appId") String appId) {
        return feedbackService.getAllFeedback();
    }

    @ApiOperation(value = "取得所有未读的用户反馈信息", notes = "取得所有未读的用户反馈信息", position = 15)
    @ApiResponse(code = 20109, message = "取得用户反馈失败")
    @RequestMapping(value = "/feedbacksunreaded", method = RequestMethod.GET)
    @ResponseBody
    public Result getAllFeedbackUnReaded(@RequestHeader(value = "appId") String appId) {
        return feedbackService.getAllFeedbackUnReaded();
    }

    @ApiOperation(value = "发送反馈信息", notes = "用户发送", position = 15)
    @ApiResponse(code = 20109, message = "取得用户反馈失败")
    @RequestMapping(value = "/sendfeedback", method = RequestMethod.POST)
    @ResponseBody
    public Result sendFeedback(
            @RequestHeader("token") String token,
            @RequestBody Map<String,String> content,@RequestHeader(value = "appId") String appId) {
        
        String text = content.get("content");
        if(text == null){
            return Result.Fail(ErrorCode.RequestWrongFormat);
        }else{
            return feedbackService.sendFeedback(token, text);
        }
    }

    @ApiOperation(value = "审阅反馈信息", notes = "审阅反馈信息", position = 15)
    @RequestMapping(value = "/reviewfeedback/{Id}/{Type}/{Record}", method = RequestMethod.POST)
    @ResponseBody
    public Result reviewFeedBack(
            @PathVariable("Id") String Id,
            @PathVariable("Type") int Type,
            @PathVariable("Record") String Record,@RequestHeader(value = "appId") String appId) {
        return feedbackService.reviewFeedBack(Id, Type, Record);
    }
}
