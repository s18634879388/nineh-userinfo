package com.ninehcom.userinfo.service;

import com.ninehcom.common.enums.ErrorCode;
import com.ninehcom.common.util.Result;
import com.ninehcom.userinfo.agent.UnionClubAgent;
import com.ninehcom.userinfo.conf.EditConfigInit;
import com.ninehcom.userinfo.controller.ItemController;
import com.ninehcom.userinfo.entity.Item;
import com.ninehcom.userinfo.entity.UserAction;
import com.ninehcom.userinfo.entity.UserInfo;
import com.ninehcom.userinfo.entity.UserStatistics;
import com.ninehcom.userinfo.enums.ConfigKeys;
import com.ninehcom.userinfo.mapper.UserActionMapper;
import com.ninehcom.userinfo.mapper.UserInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

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
    private UserScoreService userScoreService;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private ItemService itemService;
    @Autowired
    private UnionClubAgent unionClubAgent;
    @Autowired
    private EditConfigInit editConfigInit;
    private Logger logger = LoggerFactory.getLogger(UserActionService.class);
    private Date startTime;
    private String timeText;
    private Random radom = new Random();
    private Random rand = new Random();
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private String signRedisKey = "user_dosign_appid_"; //key值命名----------


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
    public Result doSign(String userId, Date date,String appid) {
        try {
            logger.info("doSign enter userId------------>"+userId+"---------------->enter doSign");
            if (userId == null || userId.isEmpty()) {
                logger.info("doSign Error userId--------->"+userId+"------ErrorCode------->"+ErrorCode.UserIdIsEmpty);
                return Result.Fail(ErrorCode.UserIdIsEmpty);
            }
            startTime = editConfigInit.getDate(ConfigKeys.SystemStartTime,appid);
            if (startTime != null && startTime.after(date)) {
                logger.info("doSign Error userId--------->"+userId+"-----ErrorCode------->"+ErrorCode.SignDateTooEarly);
                return Result.Fail(ErrorCode.SignDateTooEarly);
            }
            String day = "_"+format.format(date);
            int ret;
            _Score _score;
            Integer count;
            synchronized (this) {
                List<UserAction> actions = userActionMapper.selectUserActionByDate(userId, date);
                if (actions != null && actions.size() == 1) {
                    logger.info("doSign Error userId--------->"+userId+"------ErrorCode------->"+ErrorCode.UserSignedInToday);
                    return Result.Fail(ErrorCode.UserSignedInToday);
                }
                _score = calcScore(userId, date,appid);
                String testRedis = stringRedisTemplate.opsForValue().get(signRedisKey+appid + day);
                date = new Date();
                count = (testRedis == null) ? 0 : Integer.parseInt(testRedis);
                if (count < 300) {
                    long l = stringRedisTemplate.opsForValue().increment(signRedisKey+appid + day, 1L);
                    count = (int) l;
                    if (count < 3) {
                        stringRedisTemplate.expire(signRedisKey+appid + day, 3, TimeUnit.DAYS);

                    }
                    ret = doUserAction(userId, 1, date, count, _score.score, date.getTime());
                } else {
                    //加随机数
                    count = doSignCount(day,appid);
                    ret = doUserAction(userId, 1, date, count, _score.score, date.getTime());
                }
            }
            if (ret == 1) {
                int maxDays = getUserContinuousSignInDay(userId);
                Result r = userScoreService.addScore(userId, date, _score.score, _score.score,appid);
                if (r.isSuccess()) {
                    UserStatistics statistics = new UserStatistics(userId, maxDays, date, date.getTime());
                    r = userStatisticsService.updateUserStatistics(statistics);
                    if (r.isSuccess()) {
                        HashMap<String, String> map = new HashMap<>();
                        map.put("count", count.toString());
                        map.put("score", _score.score.toString());
                        map.put("isSequence", _score.isSequence.toString());
                        map.put("isMatchDate", _score.isMatchDate.toString());
                        r.setTag(map);
                    }
                    logger.info("doSign success AddScore success userId------------>"+userId+"---------------->enter doSign");
                    return r;
                } else {
                    logger.info("doSign success AddScore Error userId------------>"+userId+"---------------->enter doSign");
                    return r;
                }
            } else {
                logger.info("doSign Error userId------------>"+userId+"-----ErrorCode----->"+ErrorCode.UserSignedFail);
                return Result.Fail(ErrorCode.UserSignedFail);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("doSign unKnowError userId------------>"+userId+"-----ErrorCode----->"+ErrorCode.UserSignedFail);
            return Result.Fail(ErrorCode.UserSignedFail);
        }
    }
    public int doSignCount(String day,String appid) {
        int baseNum;
        int randNum;
        try {
            timeText = editConfigInit.getValue(ConfigKeys.doSignCount,appid);
            String[] values = timeText.split("-");
            if (values.length == 1) {
                baseNum = Integer.parseInt(values[0]);
                randNum = 0;
            } else if (values.length == 2) {
                baseNum = Integer.parseInt(values[0]);
                randNum = Integer.parseInt(values[1]) - baseNum;
            } else {
                baseNum = 1;
                randNum = 0;
            }
        } catch (Exception ex) {
            baseNum = 1;
            randNum = 0;
        }

        int count;
        if (randNum <= 0) {
            count = baseNum;
        } else {
            count = rand.nextInt(randNum) + baseNum;
        }
        if (count <= 0) {
            count = 1;
        }
        long l = stringRedisTemplate.opsForValue().increment(signRedisKey+appid + day, count);
        return (int) l;
    }



    public class _Score {

        public Integer score = 0;
        public Boolean isSequence = false;
        public Boolean isMatchDate = false;
    }

    private _Score calcScore(String userId, Date date,String appid) throws Exception {
        _Score _socre = new _Score();

//        String teamId = editconfigService.getValue(ConfigKeys.TeamId);
        String teamId = editConfigInit.getValue(ConfigKeys.TeamId,appid);
//        int count = userActionMapper.selectLeaguecalendarByDate(teamId, date);
        int count = unionClubAgent.selectLeagueMatchByDate(date,teamId,appid);
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
    private int doUserAction(String userId, int actionTypeId, Date date, Integer param, Integer score,Long timeStamp) {
        UserAction action = new UserAction(userId, actionTypeId, date, param, score ,timeStamp);
        return userActionMapper.insertUserAction(action);
    }
    //用户补签操作
    public Result doAddSign(String userId, Date date,String appid) {
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
                    Result r = userScoreService.addScore(userId, now, Score, Score,appid);
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
