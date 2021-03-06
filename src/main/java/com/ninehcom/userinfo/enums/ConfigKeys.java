/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ninehcom.userinfo.enums;

/**
 * Created by shixiaoqi on 2017/4/17.
 */
public enum ConfigKeys {

    TeamId("TeamId"),
    UserUrl("UserUrl"),
    AppID("AppID"),
    SmsUrl("SmsUrl"),
    AndroidLoadURL("AndroidLoadURL"),
    IOSLoadURL("IOSLoadURL"),
    SensitiveWordURL("SensitiveWordURL"),
    SearchURL("SearchURL"),
    NickNameMaxChangeTime("NickNameMaxChangeTime"),
    MaxScore("MaxScore"),
    MaxExperience("MaxExperience"),
    CheckCodeText("CheckCodeText"),
    SendGroupText("SendGroupText"),
    CampaignEndTime("CampaignEndTime"),
    CampaignStartTime("CampaignStartTime"),
    SystemStartTime("SystemStartTime"),
    EnterJoinUser("EnterJoinUser"),
    UnionClubUrl("UnionClubUrl"),
    doSignCount("doSignCount"),
    ;
    private String key;

    private ConfigKeys(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return key;
    }

}
