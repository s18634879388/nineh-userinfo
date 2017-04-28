package com.ninehcom.userinfo.service;

import com.ninehcom.common.util.Result;
import com.ninehcom.userinfo.agent.SmsAgent;
import com.ninehcom.userinfo.entity.Level;
import com.ninehcom.userinfo.entity.Phonelist;
import com.ninehcom.userinfo.enums.ConfigKeys;
import com.ninehcom.userinfo.mapper.LevelMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Level的Service
 *
 * @author shenjizhe
 * @version 1.0.0
 */
@Service
public class LevelService {

    @Autowired
    private LevelMapper LevelMapper;
    @Autowired
    private SmsAgent agent;
    @Autowired
    private EditconfigService editconfigService;

    private ArrayList<Level> levelList;

//    @PostConstruct
//    private void init() {
//        levelList = LevelMapper.selectAllLevel();
//    }

    public Result selectgroup(String appId) throws Exception {
        String sendgroupText = editconfigService.getValue(ConfigKeys.SendGroupText);
        try {
            ArrayList<Phonelist> list = LevelMapper.selectgroup();
            ArrayList<String> errList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                Phonelist phone = list.get(i);
                String mobile = phone.getPhone();
                String param = phone.getParam();
                if (param == null) {
                    param = "";
                }
                sendgroupText = sendgroupText.replace("%s", mobile);
                sendgroupText = sendgroupText.replace("%p", param);
                sendgroupText = sendgroupText.replace("%f", "%");
                String response = agent.snedOriginMessage(mobile, appId, sendgroupText);
                JSONObject obj = new JSONObject(response);
                Result result = Result.getResult(obj);
                if (!result.isSuccess()) {
                    errList.add(mobile);
                }

                LOG.info(mobile + ":" + result.isSuccess());
            }
            return Result.Success(errList);
        } catch (Exception ex) {
            throw ex;
        }

    }
    private static final Logger LOG = Logger.getLogger(LevelService.class.getName());

    public Result getLevels() {
        levelList = LevelMapper.selectAllLevel();
        return Result.Success(levelList);
    }

    public Level getLevel(int experience) {
        levelList = LevelMapper.selectAllLevel();
        for (Level level : levelList) {
            if (experience >= level.getExperience()) {
                return level;
            }
        }
        return null;
    }
}
