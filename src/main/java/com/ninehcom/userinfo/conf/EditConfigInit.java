package com.ninehcom.userinfo.conf;

import com.ninehcom.userinfo.aop.DataSourceAop;
import com.ninehcom.userinfo.entity.Editconfig;
import com.ninehcom.userinfo.enums.ConfigKeys;
import com.ninehcom.userinfo.mapper.EditconfigMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by shixiaoqi on 2017/6/26.
 * 启动加载所有俱乐部数据源的editconfig配置类
 */
@Component
public class EditConfigInit {

    Logger logger = LoggerFactory.getLogger(EditConfigInit.class);
    @Autowired
    private EditconfigMapper editconfigMapper;

    private Map editConfigs = new HashMap<>();
    private Map editConfigKeyValue;

    private DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @PostConstruct
    public void init() throws IOException {
        Properties properties = new Properties();
        properties.load(DataSourceAop.class.getClassLoader().getResourceAsStream("appid-clubid.properties"));
        Set keys =  properties.keySet();
        for (Object key:keys
             ) {
            String value = (String) properties.get(key);
            logger.info("appid-clubid.properties value is : "+value);
            DataSourceContextHolder.setDataSource(value);
            List<Editconfig> editconfigList = editconfigMapper.selectAllEditconfig();
            editConfigKeyValue= new HashMap<>();
            for (Editconfig editconfig:editconfigList
                 ) {
                editConfigKeyValue.put(editconfig.getKey(),editconfig.getValue());
            }
            editConfigs.put(key+"",editConfigKeyValue);
        }
    }

    public String getValue(ConfigKeys configKeys,String appid) {
        logger.info("configKeys is : "+configKeys.toString());
        Map map = (HashMap) editConfigs.get(appid);
        logger.info("get editConfig value is : " + map.get(configKeys.toString()));
        return (String) map.get(configKeys.toString());
    }
    public Date getDate(ConfigKeys configkeys,String appid) {
        try {
            return format.parse(getValue(configkeys,appid));
        } catch (ParseException ex) {
            return null;
        }
    }

    public Integer getInt(ConfigKeys configkeys,String appid) {
        return Integer.parseInt(getValue(configkeys,appid));
    }
    public Double getDouble(ConfigKeys configkeys,String appid) {
        return Double.parseDouble(getValue(configkeys,appid));
    }

    public Float getFloat(ConfigKeys configkeys,String appid) {
        return Float.parseFloat(getValue(configkeys,appid));
    }

    public Long getLong(ConfigKeys configkeys,String appid) {
        return Long.parseLong(getValue(configkeys,appid));
    }

    public JSONObject getJson(ConfigKeys configkeys,String appid) {
        try {
            return new JSONObject(getValue(configkeys,appid));
        } catch (JSONException ex) {
            return null;
        }
    }

    public JSONArray getJsonArray(ConfigKeys configkeys,String appid) {
        try {
            return new JSONArray(getValue(configkeys,appid));
        } catch (JSONException ex) {
            return null;
        }
    }

}
