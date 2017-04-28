/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ninehcom.userinfo.agent;

import com.ninehcom.userinfo.enums.ConfigKeys;
import com.ninehcom.userinfo.service.EditconfigService;
import com.ninehcom.userinfo.util.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author shenjizhe
 */
@Component
public class SmsAgent {

    @Value("${UserCenter.smsUrl}")
    private  String smsUrl;

    @Autowired
    private EditconfigService configService;

    @Autowired
    ConnectAgent agent;

//    @PostConstruct
//    private void init() {
//        smsUrl = configService.getValue(ConfigKeys.SmsUrl);
//    }

    public String snedMessage(String mobilenum, String appid, String contents) throws Exception {
//        smsUrl = configService.getValue(ConfigKeys.SmsUrl);
        String path = "/smsservice/send";
        String CheckCodeText = configService.getValue(ConfigKeys.CheckCodeText);
        contents = CheckCodeText.replace("%s", contents);
        Map<String,String> bodys = new HashMap<>();
        bodys.put("mobilenum",mobilenum);
        bodys.put("appid",appid);
        bodys.put("contents",contents);
        return agent.sendRequestHttpsUTF8(smsUrl,path,bodys);
    }
    
    public String snedOriginMessage(String mobilenum, String appid, String contents) throws Exception {
//        smsUrl = configService.getValue(ConfigKeys.SmsUrl);
        String path = "/smsservice/send";
        Map<String,String> bodys = new HashMap<>();
        bodys.put("mobilenum",mobilenum);
        bodys.put("appid",appid);
        bodys.put("contents",contents);
        return agent.sendRequestHttpsUTF8(smsUrl,path,bodys);
    }
    public String snedMessage2(String mobilenum, String appid, String content1,String content2) throws Exception {
//        smsUrl = configService.getValue(ConfigKeys.SmsUrl);
        String path = "/smsservice/send";
        String EnterJoinUser = configService.getValue(ConfigKeys.EnterJoinUser);
        String contents;
        contents = EnterJoinUser.replace("%s", content1);
        contents = contents.replace("%a",content2);
        Map<String,String> bodys = new HashMap<>();
        bodys.put("mobilenum",mobilenum);
        bodys.put("appid",appid);
        bodys.put("contents",contents);
        return agent.sendRequestHttpsUTF8(smsUrl,path,bodys);
    }
}
