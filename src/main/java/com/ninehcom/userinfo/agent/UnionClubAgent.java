package com.ninehcom.userinfo.agent;

import com.ninehcom.userinfo.conf.EditConfigInit;
import com.ninehcom.userinfo.enums.ConfigKeys;
import com.ninehcom.userinfo.service.EditconfigService;
import com.ninehcom.userinfo.util.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/21.
 */
@Component
public class UnionClubAgent {

    public UnionClubAgent(){

    }

    Logger logger = LoggerFactory.getLogger(UnionClubAgent.class);

    private static String UnionClubUrl = null;
    @Autowired
    private EditConfigInit editConfigInit;

    public Integer selectLeagueMatchByDate(Date toDate, String clubId,String appid) throws Exception {
        UnionClubUrl = editConfigInit.getValue(ConfigKeys.UnionClubUrl,appid);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String d = simpleDateFormat.format(toDate);
        Map<String,String> header = new HashMap<>();
        header.put("accept","*/*");
        header.put("connection","Keep-Alive");
        header.put("user-agent","Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/525.13 (KHTML, like Gecko) Chrome/0.2.149.27 Safari/525.13");
        header.put("Content-Type","application/json");
        String path = "/unionclub/league-match-count";
        Map<String,String> bodys = new HashMap<>();
        bodys.put("toDate",d);
        bodys.put("clubId",clubId);
        HttpResponse response = HttpUtils.doGet(UnionClubUrl,path,"",header,bodys);
        String res = EntityUtils.toString(response.getEntity());
        logger.info("selectLeagueMatchByDate result："+res);
        JSONObject jsonObject = new JSONObject(res);
        int count = jsonObject.getInt("tag");
        return count;
    }



}
