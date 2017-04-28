/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ninehcom.userinfo.agent;

import com.ninehcom.userinfo.entity.UserInfo;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.ninehcom.userinfo.util.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author Shenjizhe
 */
@Component
public class SearchAgent {

    @Value("${UserCenter.searchURL}")
    private String searchURL;

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");


    public boolean canUse() {
        return searchURL != null;
    }

    public boolean updateSearchWord(UserInfo user) throws Exception {
        if (searchURL != null) {
            try {
                String host = searchURL;
                Map<String,String> header = new HashMap<>();
                header.put("accept","*/*");
                header.put("connection","Keep-Alive");
                header.put("user-agent","Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/525.13 (KHTML, like Gecko) Chrome/0.2.149.27 Safari/525.13");
                header.put("Content-Type","application/json");
                Map<String,String> bodys = new HashMap<>();
                bodys.put("id",user.getId());
                bodys.put("nickName", user.getNickName());
                bodys.put("avatar", user.getPortrait());
                bodys.put("signature", user.getSignature());
                bodys.put("birthday", user.getBirthday() == null ? null : format.format(user.getBirthday()));
                bodys.put("gender", user.getSex()+"");
                bodys.put("updatedAt", format.format(new Date()));
                HttpResponse response = HttpUtils.doPost(host,"","",header,null,bodys);
                String jsonResultStr = EntityUtils.toString(response.getEntity());
            } catch (Exception ex) {
                return false;
            }
        }
        return true;
    }
}
