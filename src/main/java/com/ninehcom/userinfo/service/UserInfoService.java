package com.ninehcom.userinfo.service;

import com.ninehcom.common.enums.ErrorCode;
import com.ninehcom.common.util.Base62Utils;
import com.ninehcom.common.util.Result;
import com.ninehcom.userinfo.agent.SearchAgent;
import com.ninehcom.userinfo.agent.SensitiveWordAgent;
import com.ninehcom.userinfo.agent.SmsAgent;
import com.ninehcom.userinfo.agent.UCAgent;
import com.ninehcom.userinfo.conf.EditConfigInit;
import com.ninehcom.userinfo.entity.Level;
import com.ninehcom.userinfo.entity.LogInfo;
import com.ninehcom.userinfo.entity.Tag;
import com.ninehcom.userinfo.entity.UserInfo;
import com.ninehcom.userinfo.enums.ConfigKeys;
import com.ninehcom.userinfo.mapper.UserInfoMapper;
import com.ninehcom.userinfo.mapper.UserStatisticsMapper;
import com.ninehcom.userinfo.view.UserInfoWrapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * UserUserinfo的Service
 *
 * @author shenjizhe
 * @version 1.0.0
 */
@Service
public class UserInfoService {

    private static final Logger LOG = Logger.getLogger(UserInfoService.class.getName());

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserStatisticsMapper userStatisticsMapper;
    @Autowired
    private EditConfigInit editConfigInit;
    @Autowired
    SearchAgent searchAgent;
    @Autowired
    private LevelService levelService;

    @Autowired
    private UCAgent ucAgent;
    @Autowired
    private SmsAgent smsAgent;
    @Autowired
    private SensitiveWordAgent sensitiveWordAgent;

    public UserInfoService() {

    }

    /**
     * 发送验证码
     */
    public Result sendCheckCode(String mobilenum, String appid) throws JSONException {
        String response = null;
        try {
            response = ucAgent.sendCheckCode(mobilenum, appid);
        } catch (Exception ex) {
            ex.printStackTrace();
            return Result.Fail(ErrorCode.UserCenterCantConnect, ex);
        }

        JSONObject obj = new JSONObject(response);
        Result result = Result.getResult(obj);
        // send sms code
        if (result.isSuccess()) {
            String code = obj.getString("smscode");
            LOG.info("\n send smscode=" + code + "\n");
            try {
                response = smsAgent.snedMessage(mobilenum, appid, code);
            } catch (Exception ex) {
                return Result.Fail(ErrorCode.SMSCantConnect, ex);
            }
            obj = new JSONObject(response);
            result = Result.getResult(obj);
            return result;
        }
        return result;
    }

    /**
     * 比较验证码的功能 验证码半小时内有效
     */
    public Result checkCode(String mobileNum, String checkCode, String appid) throws JSONException {

        String response = null;
        try {
            response = ucAgent.checkCode(mobileNum, checkCode, appid);
        } catch (Exception ex) {
            return Result.Fail(ErrorCode.UserCenterCantConnect, ex);
        }

        return Result.getResult(response);
    }

    /**
     * 检查手机号是否可用
     */
    public Result validateMobile(String mobileNum) throws JSONException {
        String response = null;
        try {
            response = ucAgent.validateMobile(mobileNum);
        } catch (Exception ex) {
            return Result.Fail(ErrorCode.UserCenterCantConnect, ex);
        }
        return Result.getResult(response);
    }

    /**
     * 检查token
     */
    public Result validateToken(String token) throws JSONException {

        String response = null;
        try {
            response = ucAgent.validateToken(token);
        } catch (Exception ex) {
            return Result.Fail(ErrorCode.UserCenterCantConnect, ex);
        }
        return Result.getResult(response);
    }

    /**
     * 新用户的注册功能
     */
    public Result register(
            String mobileNum,
            String password,
            String checkCode,
            String appID,
            LogInfo logInfo
    ) throws JSONException {

        String response = null;
        try {
            response = ucAgent.register(mobileNum, password, checkCode, appID, logInfo);
        } catch (Exception ex) {
            return Result.Fail(ErrorCode.UserCenterCantConnect, ex);
        }

        Result result = Result.getResult(response);
        if (result.isSuccess()) {
            String userId = result.getValue(UCAgent.KEY_USER_ID);

            if (userId == null || userId.isEmpty()) {
                return Result.Fail(ErrorCode.UserIdIsEmpty);
            }
            String nickname = Base62Utils.getNextAccount();
            int ret = insertUser(userId, nickname);
            if (ret != 1) {
                return Result.Fail(ErrorCode.UserInsertDBFail);
            }
            UserInfo userInfo = new UserInfo();
            userInfo.setId(userId);
            userInfo.setNickName(nickname);
            try {
                boolean flag = searchAgent.updateSearchWord(userInfo);
                if (!flag){
                    return Result.Fail(ErrorCode.SearchAgentUpdateFail);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private int insertUser(String userId, String nickName) {
        int ret = userInfoMapper.insertUser(userId, nickName);
        Level level = levelService.getLevel(0);
        userStatisticsMapper.insertUserStatistics(userId, level.getId(), level.getTitle());
        return ret;
    }

    /**
     * 已有用户的登录功能
     */
    public Result login(String mobileNum, String password, String appID, LogInfo logInfo) throws JSONException {

        String response = null;
        try {
            response = ucAgent.login(mobileNum, password, appID, logInfo);
        } catch (Exception ex) {
            return Result.Fail(ErrorCode.UserCenterCantConnect, ex);
        }
        Result result = Result.getResult(response);
        ResSuccess(result);

        return result;
    }

    /**
     * 三方登录
     */
    public Result threePartLogin(String openId, int authorizedtypeid, String appId, LogInfo logInfo) throws JSONException {

        String response = null;
        try {
            response = ucAgent.threePartLogin(openId, authorizedtypeid, appId, logInfo);
        } catch (Exception ex) {
            return Result.Fail(ErrorCode.UserCenterCantConnect, ex);
        }

        Result result = Result.getResult(response);
        ResSuccess(result);
        return result;
    }

    public Result threePartLoginv2(String openId, String unionId, String accessToken, String refreshToken, int authorizedtypeid, String appId, LogInfo logInfo) throws JSONException {

        String response = null;
        try {
            response = ucAgent.threePartLoginv2(openId, unionId, authorizedtypeid, appId, logInfo);
        } catch (Exception ex) {
            return Result.Fail(ErrorCode.UserCenterCantConnect, ex);
        }

        Result result = Result.getResult(response);
        ResSuccess(result);
        return result;
    }

    public Result tokenlogin(String token) throws JSONException {

        String response = null;
        try {
            response = ucAgent.tokenlogin(token);
        } catch (Exception ex) {
            return Result.Fail(ErrorCode.UserCenterCantConnect, ex);
        }

        Result result = Result.getResult(response);
        ResSuccess(result);
        return result;
    }

    /**
     * 绑定新手机号
     */
    public Result binduser(String token, String mobileNum, String password, String checkCode) throws JSONException {

        String response = null;
        try {
            response = ucAgent.binduser(token, mobileNum, password, checkCode);
        } catch (Exception ex) {
            return Result.Fail(ErrorCode.UserCenterCantConnect, ex);
        }
        return Result.getResult(response);
    }

    /**
     * 换绑定手机号
     */
    public Result resetMobile(String token, String mobileNum, String password, String checkCode) throws JSONException {
        String response = null;
        try {
            response = ucAgent.resetMobile(token, mobileNum, password, checkCode);
        } catch (Exception ex) {
            return Result.Fail(ErrorCode.UserCenterCantConnect, ex);
        }
        return Result.getResult(response);
    }

    /**
     * 用户重置密码
     */
    public Result resetPassword(String mobileNum, String password, String checkCode, String appID, LogInfo logInfo) throws JSONException {
        String response = null;
        try {
            response = ucAgent.resetPassword(mobileNum, password, checkCode, appID, logInfo);
        } catch (Exception ex) {
            return Result.Fail(ErrorCode.UserCenterCantConnect, ex);
        }
        return Result.getResult(response);
    }

    /**
     * 用户修改密码
     */
    public Result modifyPassword(String token, String oldpassword, String newpassword) throws JSONException {

        String response = null;
        try {
            response = ucAgent.modifyPassword(token, oldpassword, newpassword);
        } catch (Exception ex) {
            return Result.Fail(ErrorCode.UserCenterCantConnect, ex);
        }
        return Result.getResult(response);
    }

    /**
     * 根据token取得用户信息
     */
    public Result getUserbytoken(String token) throws JSONException {

        String response = null;
        try {
            response = ucAgent.getUserbytoken(token);
        } catch (Exception ex) {
            return Result.Fail(ErrorCode.UserCenterCantConnect, ex);
        }

        Result result = Result.getResult(response);

        if (result.isSuccess()) {
            String userId = result.getValue(UCAgent.KEY_USER_ID);
            String mobileNum = result.getValue(UCAgent.KEY_MOBILE_NUM);

            UserInfo user = userInfoMapper.selectUserInfoById(userId);
            if (user != null) {
                user.setPhoneNumber(mobileNum);
                result.setTag(user);
            }
        }
        return result;
    }
    
    public Result selectUserIdFromNHID(String hnid){
        Long userId = userInfoMapper.selectUserIdFromNHID(hnid);
        if(userId== null){
            return Result.Fail(ErrorCode.UserNotExist);
        }else{
            return Result.Success((Serializable)userId);
        }
    }
    
      public Result selectNHIdFromUserId(Long userId){
        String nhid = userInfoMapper.selectNHIdFromUserId(userId);
        if(nhid== null){
            return Result.Fail(ErrorCode.UserNotExist);
        }else{
            return Result.Success((Serializable)nhid);
        }
    }

    public Result selectUserInfoWrapperById(String userId) throws Exception {
        UserInfoWrapper user = userInfoMapper.selectUserInfoWrapperById(userId);
        if (user != null) {
            ArrayList<Tag> tagList = userInfoMapper.selectTagByUserId(userId);
            user.setTagList(tagList);
            return Result.Success(user);
        } else {
            return Result.Fail(ErrorCode.UserNotExist);
        }
    }

    public Result selectUserInfoWrapperByIds(List<String> userIds) throws Exception {
        if (userIds != null && userIds.size() > 0) {
            ArrayList<UserInfoWrapper> userList = userInfoMapper.selectUserInfoWrapperByIds(userIds);

            for (int i = 0; i < userList.size(); i++) {
                UserInfoWrapper userInfo = userList.get(i);
                ArrayList<Tag> tagList = userInfoMapper.selectTagByUserId(userInfo.getId());
                userInfo.setTagList(tagList);
            }
            return Result.Success(userList);
        } else {
            return Result.Success(new ArrayList<UserInfoWrapper>());
        }
    }

    /**
     * 登出
     */
    public Result logout(String token) throws JSONException {

        String response = null;
        try {
            response = ucAgent.logout(token);
        } catch (Exception ex) {
            return Result.Fail(ErrorCode.UserCenterCantConnect, ex);
        }
        return Result.getResult(response);
    }

    private Result modifyUserNickName(String token, String userId, String value,String appid) throws Exception {
        int limit = -1;
        try {
//            limit = editconfigService.getInt(ConfigKeys.NickNameMaxChangeTime);
            limit = editConfigInit.getInt(ConfigKeys.NickNameMaxChangeTime,appid);
        } catch (Exception ex) {
            return Result.Fail(ErrorCode.NickNameLimitConfigEmpty);
        }

        if (limit != -1) {
            int times = userInfoMapper.selectNickNameChangeTime(userId);
            if (times >= limit) {
                return Result.Fail(ErrorCode.UserNickNameAlreadyChanged);
            }
        }

        try {
            int ret = userInfoMapper.updateUserNickName(userId, value);
            if (ret != 1) {
                return Result.Fail(ErrorCode.UserUpdateDBFail);
            }
            UserInfo user = userInfoMapper.selectUserInfoById(userId);
        } catch (Exception ex) {
            return Result.Fail(ErrorCode.NickNameUsed);
        }

        return null;
    }

    /**
     * 完善个人信息
     */
    public Result completeinfo(String token, UserInfo user,String appid) throws Exception {
        String response = null;
        try {
            response = ucAgent.getUserbytoken(token);
        } catch (Exception ex) {
            return Result.Fail(ErrorCode.UserCenterCantConnect, ex);
        }
        Result result = Result.getResult(response);

        if (result.isSuccess()) {
            String nickName = user.getNickName();
            if (nickName != null) {

                String[] sensitive = null;
                try {
                    sensitive = sensitiveWordAgent.check(nickName);
                } catch (Exception ex) {
                    return Result.Fail(ErrorCode.SensitiveWordCantConnect, ex);
                }
                if (sensitive != null) {
                    return new Result(
                            ErrorCode.NickSensitiveWordContains.getCode(),
                            ErrorCode.NickSensitiveWordContains.getMessage(),
                            sensitive);
                }

                Result resultNick = modifyUserNickName(token, user.getId(), nickName,appid);
                if (resultNick != null) {
                    return resultNick;
                }
            }
            String signature = user.getSignature();
            if (signature != null) {
                String[] sensitive = sensitiveWordAgent.check(signature);
                if (sensitive != null) {
                    return new Result(
                            ErrorCode.SignatureNickSensitiveWordContains.getCode(),
                            ErrorCode.SignatureNickSensitiveWordContains.getMessage(),
                            sensitive);
                }
            }
//            if (user.getProvinceid()==null){
//                user.setProvinceid(-1);
//            }
//            if (user.getCityid()==null){
//                user.setCityid(-1);
//            }
//            if (user.getDistrictid()==null){
//                user.setDistrictid(-1);
//            }
            int ret = userInfoMapper.updateUserInfo(user);
            if (ret != 1) {
                return Result.Fail(ErrorCode.UserUpdateDBFail);
            }
        }

        if (searchAgent.canUse()) {
            UserInfo originUser = userInfoMapper.selectUserInfoById(user.getId());
            searchAgent.updateSearchWord(user);
        }

        return result;
    }

    public UserInfo getUserInfoByToken(String token) throws JSONException {

        String response = null;
        try {
            response = ucAgent.getUserbytoken(token);
        } catch (Exception ex) {
            return null;
        }

        Result result = Result.getResult(response);

        if (result.isSuccess()) {
            String userId = result.getValue(UCAgent.KEY_USER_ID);
            String mobileNum = result.getValue(UCAgent.KEY_MOBILE_NUM);

            UserInfo user = userInfoMapper.selectUserInfoById(userId);
            if (user != null) {
                user.setPhoneNumber(mobileNum);
            }
            return user;

        } else {
            return null;
        }
    }

    public Result validatePrivilege(String userId, String privilegeKey) {
        int count = userInfoMapper.selectUserPrivilege(userId, privilegeKey);
        if (count > 0) {
            return Result.Success();
        } else {
            return Result.Fail(ErrorCode.UserHaveNotRight);
        }
    }
    public Result ResSuccess(Result result){
        if (result.isSuccess()) {
            String userId = result.getValue(UCAgent.KEY_USER_ID);
            if (userId == null || userId.isEmpty()) {
                return Result.Fail(ErrorCode.UserIdIsEmpty);
            }
            UserInfo user = userInfoMapper.selectUserInfoById(userId);
            if (user == null) {
                String nickname = Base62Utils.getNextAccount();
                int ret = insertUser(userId, nickname);
                if (ret != 1) {
                    return Result.Fail(ErrorCode.UserInsertDBFail);
                }
                user = new UserInfo();
                user.setId(userId);
                user.setNickName(nickname);
                try {
                   boolean flag = searchAgent.updateSearchWord(user);
                    if (!flag){
                        return Result.Fail(ErrorCode.SearchAgentUpdateFail);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }


    public Result selectUserLike(String nickname) {
        String nickname2 = "%"+nickname+"%";
        List<UserInfo> list = userInfoMapper.selectUserLike(nickname2);
        return Result.Success((Serializable) list);
    }
}
