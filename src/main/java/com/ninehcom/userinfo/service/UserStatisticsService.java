package com.ninehcom.userinfo.service;

import com.ninehcom.common.enums.ErrorCode;
import com.ninehcom.common.util.Result;
import com.ninehcom.userinfo.entity.Level;
import com.ninehcom.userinfo.entity.UserStatistics;
import com.ninehcom.userinfo.mapper.UserStatisticsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;

/**
 * UserStatistics的Service
 *
 * @author shenjizhe
 * @version 1.0.0
 */
@Service
public class UserStatisticsService {

    @Autowired
    private UserStatisticsMapper userStatisticsMapper;

    @Autowired
    private LevelService levelService;

    public Result addScoreUserStatistics(UserStatistics statistics) {
        Result r = getUserStatistics(statistics.getUserId());
        if (r.isSuccess()) {
            UserStatistics current = (UserStatistics) r.getTag();
            statistics.setScore(statistics.getScore() + current.getScore());
            statistics.setExperience(statistics.getExperience() + current.getExperience());
        } else {
            statistics.setScore(statistics.getScore());
            statistics.setExperience(statistics.getExperience());
        }
        return updateUserStatistics(statistics);
    }

    public Result subScoreUserStatistics(UserStatistics statistics) {
        Result r = getUserStatistics(statistics.getUserId());
        if (r.isSuccess()) {
            UserStatistics current = (UserStatistics) r.getTag();
            statistics.setScore(current.getScore() - statistics.getScore());
            statistics.setExperience(current.getExperience() - statistics.getExperience());
        } else {
            statistics.setScore(0);
            statistics.setExperience(0);
        }
        return updateUserStatistics(statistics);
    }

    public Result updateUserStatistics(UserStatistics temp) {

        String userId = temp.getUserId();
        if (userId == null || userId.isEmpty()) {
            return Result.Fail(ErrorCode.UserIdIsEmpty);
        }
        UserStatistics sign = userStatisticsMapper.selectUserStatisticsByUserId(userId);
        if (sign == null) {
            Level level = levelService.getLevel(0);
            userStatisticsMapper.insertUserStatistics(userId, level.getId(), level.getTitle());
        }

        if (temp.getExperience() != null) {
            Level level = levelService.getLevel(temp.getExperience().intValue());
            if (level != null) {
                temp.setLevel(level.getId());
                temp.setTitle(level.getTitle());
            }
        }

        int ret = userStatisticsMapper.updateUserStatistics(temp);
        if (ret == 1) {
            return Result.Success();
        } else {
            return Result.Fail(ErrorCode.UserSigneSearchUpdateFail);
        }
    }

    public int selectCountByDate(Date date) {
        return userStatisticsMapper.selectCountByDate(date);
    }

    public Result getUserStatisticsList(int count) {
        ArrayList<UserStatistics> list = userStatisticsMapper.selectUserStatisticsRankList(count);
        return Result.Success("取得排行榜成功", list);
    }

    public Result getUserStatistics(String userId) {
        UserStatistics statics = userStatisticsMapper.selectUserStatisticsByUserId(userId);

        if (statics == null) {
            return Result.Fail(ErrorCode.UserUnSigned);
        } else {
            return Result.Success("取得个人统计信息成功", statics);
        }
    }

    public Result updateUserTopicCount(String userId, int topicCount) {
        UserStatistics userStatistics = new UserStatistics();
        userStatistics.setUserId(userId);
        userStatistics.setTopicCount(topicCount);
        Result result = updateUserStatistics(userStatistics);
        return result;
    }
}
