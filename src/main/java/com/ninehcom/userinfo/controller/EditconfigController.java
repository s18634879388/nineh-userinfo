package com.ninehcom.userinfo.controller;

import com.ninehcom.userinfo.service.EditconfigService;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by shixiaoqi on 2017/4/17.
 */
@RestController
@RequestMapping(value = "/users")
public class EditconfigController {

    @Autowired
    EditconfigService editconfigService;

    @RequestMapping(value = "/config/{key}", method = RequestMethod.GET)
    @ResponseBody
    public String getConfig(
            @ApiParam(value = "配置键", defaultValue = "")
            @PathVariable("key") String key,
            @RequestHeader(value = "appId") String appId) {
        return editconfigService.getConfigValue(key);
    }
}
