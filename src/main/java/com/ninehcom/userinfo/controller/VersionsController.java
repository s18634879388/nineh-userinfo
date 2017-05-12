package com.ninehcom.userinfo.controller;

import com.ninehcom.common.enums.ErrorCode;
import com.ninehcom.common.util.Result;
import com.ninehcom.userinfo.service.VersionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

//import com.ninehcom.userinfo.jpa.repository.VersionsRepository;

/**
 * Versions 的控制器，用于显示同时查询2个数据库的结果 * @author shenjizhe
 *
 * @version 1.0.0
 */
@RestController
@RequestMapping(value = "/users")
public class VersionsController {

    @Autowired
    VersionsService versionsService;
//    @Autowired
//    VersionsRepository versionsRepository;

    @RequestMapping(value = "/validateversion", method = RequestMethod.POST)
    @ResponseBody
    public Result validateVersion(
            @RequestHeader("systemtypeid") String systemtypeid,
            @RequestBody Map<String, String> content,@RequestHeader(value = "appId") String appId) {
        String versionNum = content.get("version");
        if (versionNum == null) {
            return Result.Fail(ErrorCode.RequestWrongFormat);
        } else {
            return versionsService.validateVersion(systemtypeid, versionNum);
        }
    }
}
