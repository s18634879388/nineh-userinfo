/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ninehcom.userinfo.agent;
import com.ninehcom.userinfo.service.EditconfigService;
import com.ninehcom.userinfo.util.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author shenjizhe
 */
@Component
public class SensitiveWordAgent {

    @Value("${UserCenter.SensitiveWordURL}")
    private String sensitiveWordURL;

    private JSONObject checkWord(String word) throws Exception {
        String host = sensitiveWordURL;
        Map<String,String> header = new HashMap<>();
        header.put("accept","*/*");
        header.put("connection","Keep-Alive");
        header.put("user-agent","Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/525.13 (KHTML, like Gecko) Chrome/0.2.149.27 Safari/525.13");
        header.put("Content-Type","application/json");
        String body = word;
        HttpResponse response = HttpUtils.doPost(host,"","",header,null,body);
        String jsonResultStr = EntityUtils.toString(response.getEntity());
        JSONObject jsonResult = new JSONObject(jsonResultStr);
        return jsonResult;
    }

//    private Boolean isContain(String word) throws Exception {
//        JSONObject jsonObject = this.checkWord(word);
//        return jsonObject.getBoolean("contain");
//    }
//
//    private Boolean isContain(String word, HttpServletResponse response) throws Exception {
//        JSONObject jsonObject = this.checkWord(word);
//        if (jsonObject.getBoolean("contain")) {
//            response.setStatus(400);
//            String message = "含有敏感词: " + jsonObject.getJSONArray("keywords");
//            response.setHeader("X-Err-Message", URLEncoder.encode(message, "utf-8"));
//        }
//        return jsonObject.getBoolean("contain");
//    }
    public String[] check(String word) throws Exception {
        if(word == "" || word == null){
            return null;
        }
        
        JSONObject jsonObject = this.checkWord(word);
        
        if (jsonObject.getBoolean("contain")) {
            JSONArray array = jsonObject.getJSONArray("keywords");
            String[] text = new String[array.length()];
            for (int i = 0; i < array.length(); i++) {
                text[i] = array.get(i).toString();
            }
            return text;
        }
        return null;
    }
}
