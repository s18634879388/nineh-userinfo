/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ninehcom.userinfo.agent;

import com.ninehcom.userinfo.util.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Shenjizhe
 */
@Component
public class ConnectAgent {
    public String sendRequestHttpsUTF8(String request,String path,Map<String,String> bodys) throws Exception{
        Map<String,String> header = new HashMap<>();
        header.put("accept","*/*");
        header.put("connection","Keep-Alive");
        header.put("user-agent","Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/525.13 (KHTML, like Gecko) Chrome/0.2.149.27 Safari/525.13");
        header.put("Content-Type","application/json");
        HttpResponse response = HttpUtils.doGet(request,path,"",header,bodys);
        return EntityUtils.toString(response.getEntity());
    }
    
//    public String sendRequestHttpUTF8(String request) throws Exception{
//        return HttpUtils.getAsString(request, "utf-8",null);
//    }
}
