package com.ninehcom.userinfo.controller;

import com.ninehcom.common.util.Result;
import com.ninehcom.userinfo.service.EditconfigService;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * Created by shixiaoqi on 2017/4/17.
 */
@RestController
@RequestMapping(value = "/users")
public class EditconfigController {

    @Autowired
    EditconfigService editconfigService;

    @Value("${gauserinfo.datasource.version}")
    private String version;

    @RequestMapping(value = "/version", method = RequestMethod.GET)
    @ResponseBody
    public String getVersion(@RequestHeader(value = "appId") String appId) {
        return version;
    }

    @RequestMapping(value = "/config/{key}", method = RequestMethod.GET)
    @ResponseBody
    public Result getConfig(
            @ApiParam(value = "配置键", defaultValue = "")
            @PathVariable("key") String key,@RequestHeader(value = "appId") String appId) {
        return editconfigService.getConfigValue(key);
    }
}
