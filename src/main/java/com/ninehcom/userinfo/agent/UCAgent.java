/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ninehcom.userinfo.agent;

import com.ninehcom.userinfo.entity.LogInfo;
import com.ninehcom.userinfo.enums.ConfigKeys;
import com.ninehcom.userinfo.service.EditconfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author shenjizhe
 */
@Component
public class UCAgent {

    public final static String KEY_USER_ID = "unionuserid";
    public final static String KEY_MOBILE_NUM = "mobilenum";

    @Value("${UserCenter.userUrl}")
    private String userUrl;

    @Autowired
    ConnectAgent agent;

    public UCAgent() {

    }

    public String sendCheckCode(String mobilenum, String appid) throws Exception {
        String path = "/user/getsmscode";
        Map<String,String> bodys = new HashMap<>();
        bodys.put("mobilenum",mobilenum);
        bodys.put("appid",appid);
        return agent.sendRequestHttpsUTF8(userUrl,path,bodys);
    }

    public String checkCode(String mobileNum, String checkCode, String appid) throws Exception {
        String path = "/user/validatesmscode";
        Map<String,String> bodys = new HashMap<>();
        bodys.put("mobilenum",mobileNum);
        bodys.put("appid",appid);
        bodys.put("smscode",checkCode);
        return agent.sendRequestHttpsUTF8(userUrl,path,bodys);
    }

    public String validateMobile(String mobileNum) throws Exception {
        String path = "/user/validatemobilenum";
        Map<String,String> bodys = new HashMap<>();
        bodys.put("mobilenum",mobileNum);
        return agent.sendRequestHttpsUTF8(userUrl,path,bodys);
    }

    public String validateToken(String token) throws Exception {
        String path = "/user/validatetoken";
        Map<String,String> bodys = new HashMap<>();
        bodys.put("token",token);
        return agent.sendRequestHttpsUTF8(userUrl,path,bodys);
    }

    public String register(String mobileNum, String password, String checkCode, String appID, LogInfo logInfo) throws Exception {
        String path = "/user/registerlogin";
        Map<String,String> bodys = new HashMap<>();
        bodys.put("mobilenum",mobileNum);
        bodys.put("smscode",checkCode);
        bodys.put("password",password);
        bodys.put("appid",appID);
        bodys.put("ip",logInfo.getIp()+"");
        bodys.put("systemtypeid",logInfo.getSystemtypeid());
        bodys.put("equipmentnum",logInfo.getEquipmentnum());
        return agent.sendRequestHttpsUTF8(userUrl,path,bodys);
    }

    public String login(String mobileNum, String password, String appID, LogInfo logInfo) throws Exception {
        String path = "/user/login";
        Map<String,String> bodys = new HashMap<>();
        bodys.put("mobilenum",mobileNum);
        bodys.put("password",password);
        bodys.put("appid",appID);
        bodys.put("ip",logInfo.getIp()+"");
        bodys.put("systemtypeid",logInfo.getSystemtypeid());
        bodys.put("equipmentnum",logInfo.getEquipmentnum());
        return agent.sendRequestHttpsUTF8(userUrl,path,bodys);
    }

    public String resetMobile(String token, String mobileNum, String password, String checkCode) throws Exception {
        String path = "/user/resetmobilenum";
        Map<String,String> bodys = new HashMap<>();
        bodys.put("token",token);
        bodys.put("password",password);
        bodys.put("mobilenum",mobileNum);
        bodys.put("smscode",checkCode);
        return agent.sendRequestHttpsUTF8(userUrl,path,bodys);
    }

    public String resetPassword(String mobileNum, String password, String checkCode, String appID, LogInfo logInfo) throws Exception {
        String path = "/user/resetpassword";
        Map<String,String> bodys = new HashMap<>();
        bodys.put("mobilenum",mobileNum);
        bodys.put("smscode",checkCode);
        bodys.put("password",password);
        bodys.put("appid",appID);
        bodys.put("ip",logInfo.getIp()+"");
        bodys.put("systemtypeid",logInfo.getSystemtypeid());
        bodys.put("equipmentnum",logInfo.getEquipmentnum());
        return agent.sendRequestHttpsUTF8(userUrl,path,bodys);
    }

    public String modifyPassword(String token, String oldpassword, String newpassword) throws Exception {
        String path = "/user/modifypassword";
        Map<String,String> bodys = new HashMap<>();
        bodys.put("token",token);
        bodys.put("oldpassword",oldpassword);
        bodys.put("newpassword",newpassword);
        return agent.sendRequestHttpsUTF8(userUrl,path,bodys);
    }

    public String getUserbytoken(String token) throws Exception {
        String path = "/user/getbytoken";
        Map<String,String> bodys = new HashMap<>();
        bodys.put("token",token);
        return agent.sendRequestHttpsUTF8(userUrl,path,bodys);
    }

    public String threePartLogin(String openId, int authorizedtypeid, String appId, LogInfo logInfo) throws Exception {
        String path = "/user/authorizedlogin";
        Map<String,String> bodys = new HashMap<>();
        bodys.put("openid",openId);
        bodys.put("appid",appId);
        bodys.put("authorizedtypeid",String.valueOf(authorizedtypeid));
        bodys.put("ip",logInfo.getIp()+"");
        bodys.put("systemtypeid",logInfo.getSystemtypeid());
        bodys.put("equipmentnum",logInfo.getEquipmentnum());
        return agent.sendRequestHttpsUTF8(userUrl,path,bodys);
    }

    public String threePartLoginv2(String openId, String unionId, int authorizedtypeid, String appId, LogInfo logInfo) throws Exception {
        String path = "/user/authorizedlogin2";
        Map<String,String> bodys = new HashMap<>();
        bodys.put("openid",openId);
        bodys.put("unionid",unionId);
        bodys.put("appid",appId);
        bodys.put("authorizedtypeid",String.valueOf(authorizedtypeid));
        bodys.put("ip",logInfo.getIp()+"");
        bodys.put("systemtypeid",logInfo.getSystemtypeid());
        bodys.put("equipmentnum",logInfo.getEquipmentnum());
        return agent.sendRequestHttpsUTF8(userUrl,path,bodys);
    }

    public String binduser(String token, String mobileNum, String password, String checkCode) throws Exception {
        String path = "/user/binduser";
        Map<String,String> bodys = new HashMap<>();
        bodys.put("token",token);
        bodys.put("mobilenum",mobileNum);
        bodys.put("smscode",checkCode);
        bodys.put("password",password);
        return agent.sendRequestHttpsUTF8(userUrl,path,bodys);
    }

    public String tokenlogin(String token) throws Exception {
        String path = "/user/tokenlogin";
        Map<String,String> bodys = new HashMap<>();
        bodys.put("token",token);
        return agent.sendRequestHttpsUTF8(userUrl,path,bodys);
    }

    public String logout(String token) throws Exception {
        String path = "/user/logout";
        Map<String,String> bodys = new HashMap<>();
        bodys.put("token",token);
        return agent.sendRequestHttpsUTF8(userUrl,path,bodys);
    }

}
