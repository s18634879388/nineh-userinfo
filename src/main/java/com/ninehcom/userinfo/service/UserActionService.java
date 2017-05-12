package com.ninehcom.userinfo.service;

import com.ninehcom.common.enums.ErrorCode;
import com.ninehcom.common.util.Result;
import com.ninehcom.userinfo.controller.ItemController;
import com.ninehcom.userinfo.entity.Item;
import com.ninehcom.userinfo.entity.UserAction;
import com.ninehcom.userinfo.entity.UserInfo;
import com.ninehcom.userinfo.entity.UserStatistics;
import com.ninehcom.userinfo.enums.ConfigKeys;
import com.ninehcom.userinfo.mapper.UserActionMapper;
import com.ninehcom.userinfo.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * UserAction的Service
 *
 * @author shenjizhe
 * @version 1.0.0
 */
@Service
public class UserActionService {

    private final int SigninActionType = 1;
    //用户类型为补签
    private final int AddSignActionType = 9;
    private final int Score = 1;

    @Autowired
    private UserActionMapper userActionMapper;
    @Autowired
    private UserStatisticsService userStatisticsService;
    @Autowired
    EditconfigService editconfigService;
    @Autowired
    private UserScoreService userScoreService;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemController itemController;


    private DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    private int compareDate(Date date1, Date date2) {
        Date d1 = new Date(date1.getYear(), date1.getMonth(), date1.getDate());
        Date d2 = new Date(date2.getYear(), date2.getMonth(), date2.getDate());

        long intervalMilli = d1.getTime() - d2.getTime();
        return (int) (intervalMilli / (24 * 60 * 60 * 1000));
    }

    // 取得用户最大连续登陆天数
    private int getUserContinuousSignInDay(String userId) {
        List<UserAction> actions = selectAllUserActionByType(userId);
        if (actions == null || actions.size() == 0) {
            return 0;
        }
        int maxDays = 1;
        int continousDays = 1;
        Date lastDate = new Date(2000, 0, 1);
        for (UserAction action : actions) {
            Date actionDate = action.getTime();
            int diff = compareDate(actionDate, lastDate);
            if (diff > 1) {
                // 差异大于一天，之前的连续天数比较最大值，并重置连续天数
                if (maxDays < continousDays) {
                    maxDays = continousDays;
                }
                continousDays = 1;
            } else if (diff == 1) {
                // 差异等于一天，增加连续天数
                continousDays++;
            }
            lastDate = actionDate;
        }
        if (maxDays < continousDays) {
            maxDays = continousDays;
        }
        return maxDays;
    }

    public Result getUserActionByMonth(String userId, int year, int month) {
        ArrayList<UserAction> list = userActionMapper.selectUserActionByMonth(userId,year, month);
        return Result.Success(list);
    }

    public Result getUserActionByDate(String userId, Date date) {
        List<UserAction> actions = userActionMapper.selectUserActionByDate(userId,date);
        if (actions != null && actions.size() == 1) {
            return Result.Success(actions.get(0));
        } else {
            return Result.Fail(ErrorCode.UserUnSignedInToday);
        }
    }

    private List<String> userList = new ArrayList();

    // 签到
    public Result doSign(String userId, Date date) {

        if (userId == null || userId.isEmpty()) {
            return Result.Fail(ErrorCode.UserIdIsEmpty);
        }

        if (userList.contains(userId)) {
            return Result.Fail(ErrorCode.UserSigning);
        }
        userList.add(userId);

        try {
            Date startTime = editconfigService.getDate(ConfigKeys.SystemStartTime);
            if (startTime != null && startTime.after(date)) {
                return Result.Fail(ErrorCode.SignDateTooEarly);
            }
            Date now = date;
            List<UserAction> actions = userActionMapper.selectUserActionByDate(userId,now);
            if (actions != null && actions.size() == 1) {
                return Result.Fail(ErrorCode.UserSignedInToday);
            } else {
                _Score _score = calcScore(userId, now);
                int ret = doUserAction(userId, 1, now, null, _score.score,now.getTime());
                if (ret == 1) {
                    int maxDays = getUserContinuousSignInDay(userId);
                    Result r = userScoreService.addScore(userId, now, _score.score, _score.score);
                    if (r.isSuccess()) {
                        UserStatistics statistics = new UserStatistics();
                        statistics.setUserId(userId);
                        statistics.setMaxDays(maxDays);
                        statistics.setLastTime(now);
                        statistics.setTimeStamp(now.getTime());
                        r = userStatisticsService.updateUserStatistics(statistics);
                        if (r.isSuccess()) {
                            Integer count = userStatisticsService.selectCountByDate(now);
                            HashMap<String, String> map = new HashMap<>();
                            map.put("count", count.toString());
                            map.put("score", _score.score.toString());
                            map.put("isSequence", _score.isSequence.toString());
                            map.put("isMatchDate", _score.isMatchDate.toString());
                            r.setTag(map);
                        }
                        return r;
                    } else {
                        return r;
                    }
                } else {
                    return Result.Fail(ErrorCode.UserSignedFail);
                }
            }
        } finally {
            userList.remove(userId);
        }
    }



    public class _Score {

        public Integer score = 0;
        public Boolean isSequence = false;
        public Boolean isMatchDate = false;
    }

    private _Score calcScore(String userId, Date date) {
        _Score _socre = new _Score();

        String teamId = editconfigService.getValue(ConfigKeys.TeamId);
        int count = userActionMapper.selectLeaguecalendarByDate(teamId, date);
        int continueCount = userActionMapper.selectUserActionByLastDate(userId, SigninActionType, date);
        int score = 1;
        if (count >= 1) {
            _socre.isMatchDate = true;
            score *= 2;
        }
        if (continueCount >= 1) {
            _socre.isSequence = true;
            score *= 2;
        }

        _socre.score = score;
        return _socre;
    }

    //取得用户的指定行为记录
    private List<UserAction> selectAllUserAction(String userId, int type) {
        return userActionMapper.selectUserActionByType(userId, type);
    }
    //取得用户的签到和补签行为记录
    private List<UserAction> selectAllUserActionByType(String userId) {
        return userActionMapper.selectUserActionByTypes(userId);
    }

    // 用户执行操作
    private int doUserAction(String userId, int actionTypeId, Date date, String param, Integer score,Long timeStamp) {
        UserAction action = new UserAction(userId, actionTypeId, date, param, score ,timeStamp);
        return userActionMapper.insertUserAction(action);
    }
    //用户补签操作
    public Result doAddSign(String userId, Date date) {
        if(userId == null || userId.isEmpty()){
            return Result.Fail(ErrorCode.UserIdIsEmpty);
        }
        if (userList.contains(userId)) {
            return Result.Fail(ErrorCode.UserSigning);
        }
        userList.add(userId);
        try {
            UserInfo userInfo = userInfoMapper.selectUserInfoById(userId);
            if (userInfo == null) {
                return Result.Fail(ErrorCode.UserNotExist);
            }
            Date registerTime = userInfo.getCreatedAt();
            if (registerTime != null && registerTime.after(date)) {
                return Result.Fail(ErrorCode.SignDateregisterTime);
            }
            if (new Date().before(date)) {
                return Result.Fail(ErrorCode.SignDateTooLater);
            }
            Date now = date;
            now.setHours(23);
            now.setMinutes(59);
            now.setSeconds(59);
            List<UserAction> actions = userActionMapper.selectUserActionByDate1(userId, now);
            if (actions != null && actions.size() == 1) {
                return Result.Fail(ErrorCode.UserSigned);
            } else {
                //判断用户是否有补签卡
                Result result = itemService.useSupplementary1(userId);
                Integer error = result.getErrCode();
                if (error != 0) {
                    return result;
                }
                //补签直接给用户1分
                int ret = doUserAction(userId, AddSignActionType, now, null, Score,now.getTime());
                if (ret == 1) {
                    int maxDays = getUserContinuousSignInDay(userId);
                    Result r = userScoreService.addScore(userId, now, Score, Score);
                    if (r.isSuccess()) {
                        //更新用户表中的成绩积分等用户信息
                        UserStatistics statistics = new UserStatistics();
                        Date lastTime = new Date();
                        statistics.setUserId(userId);
                        statistics.setMaxDays(maxDays);
                        statistics.setLastTime(lastTime);
                        statistics.setTimeStamp(lastTime.getTime());
                        r = userStatisticsService.updateUserStatistics(statistics);
                        if (r.isSuccess()) {
                            HashMap<String, String> map = new HashMap<>();
                            map.put("score", "1");
                            r.setTag(map);
                        }
                        return r;
                    } else {
                        return r;
                    }
                } else {
                    return Result.Fail(ErrorCode.UserAddSignedFail);
                }
            }
        }finally {
            userList.remove(userId);
        }
    }
}
