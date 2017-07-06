package com.ninehcom.userinfo.service;

import com.ninehcom.common.enums.ErrorCode;
import com.ninehcom.common.util.Result;
import com.ninehcom.userinfo.conf.EditConfigInit;
import com.ninehcom.userinfo.entity.UserScore;
import com.ninehcom.userinfo.entity.UserStatistics;
import com.ninehcom.userinfo.enums.ConfigKeys;
import com.ninehcom.userinfo.mapper.UserScoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.Date;

/**
 * UserScore的Service
 *
 * @author shenjizhe
 * @version 1.0.0
 */
@Service
public class UserScoreService {

    @Autowired
    private UserScoreMapper userScoreMapper;

    @Autowired
    private UserStatisticsService userStatisticsService;
    @Autowired
    private EditConfigInit editConfigInit;

    private int MaxScore;
    private int MaxExperience;


    private int getAddScore(int currentScore, int expectScore, int maxScore) {
        return Integer.min(maxScore - currentScore, expectScore);
    }

    private int getSubScore(int currentScore, int expectScore) {
        return Integer.min(currentScore, expectScore);
    }

    public class RealScore implements Serializable {

        public RealScore() {
        }

        public RealScore(int score, int experience) {
            this.score = score;
            this.experience = experience;
        }

        private int score;
        private int experience;

        /**
         * @return the score
         */
        public int getScore() {
            return score;
        }

        /**
         * @param score the score to set
         */
        public void setScore(int score) {
            this.score = score;
        }

        /**
         * @return the experience
         */
        public int getExperience() {
            return experience;
        }

        /**
         * @param experience the experience to set
         */
        public void setExperience(int experience) {
            this.experience = experience;
        }
    }

    public Result addScore(String userId, Date time, int score, int experience,String appid) {
        if (score < 0 || experience < 0) {
            return Result.Fail(ErrorCode.ScoreValidateFail);
        }
        UserScore userScore = userScoreMapper.selectUserScoreByIDDate(userId, time);
        MaxScore = editConfigInit.getInt(ConfigKeys.MaxScore,appid);
        MaxExperience = editConfigInit.getInt(ConfigKeys.MaxExperience,appid);
        int realScore = getAddScore(userScore != null ? userScore.getScore() : 0,
                score,
                MaxScore);
        int realExperience = getAddScore(userScore != null ? userScore.getExperience() : 0,
                experience,
                MaxExperience);

        int ret;
        if (userScore == null) {
            userScore = new UserScore(userId, time, realScore, realExperience);
            ret = userScoreMapper.insertUserScoreByIDDate(userScore);
        } else {
            userScore.setScore(realScore + userScore.getScore());
            userScore.setExperience(realExperience + userScore.getExperience());
            ret = userScoreMapper.updateUserScoreByIDDate(userScore);
        }
        if (ret == 1) {
            UserStatistics statistics = new UserStatistics();
            statistics.setUserId(userId);
            statistics.setScore(realScore);
            statistics.setExperience(realExperience);
            userStatisticsService.addScoreUserStatistics(statistics);
            return Result.Success(new RealScore(realScore, realExperience));
        } else {
            return Result.Fail(ErrorCode.ScoreAddToDBFail);
        }
    }

    public Result addCampaignScore(String userId, int score, int experience) {
        if (score < 0 || experience < 0) {
            return Result.Fail(ErrorCode.ScoreValidateFail);
        }else {
            UserStatistics statistics = new UserStatistics();
            statistics.setUserId(userId);
            statistics.setScore(score);
            statistics.setExperience(experience);
            userStatisticsService.addScoreUserStatistics(statistics);
            return Result.Success(new RealScore(score, experience));
        }
    }

    public Result subScore(String userId, Date time, int score, int experience) {
        if (score < 0 || experience < 0) {
            return Result.Fail(ErrorCode.ScoreValidateFail);
        }
        UserScore userScore = userScoreMapper.selectUserScoreByIDDate(userId, time);
        if (userScore != null) {
            int realScore = getSubScore(userScore.getScore(), score);
            int realExperience = getSubScore(userScore.getExperience(), experience);

            userScore.setScore(userScore.getScore() - realScore);
            userScore.setExperience(userScore.getExperience() - realExperience);
            int ret = userScoreMapper.updateUserScoreByIDDate(userScore);
            if (ret == 1) {
                UserStatistics statistics = new UserStatistics();
                statistics.setUserId(userId);
                statistics.setScore(realScore);
                statistics.setExperience(realExperience);
                userStatisticsService.subScoreUserStatistics(statistics);
                return Result.Success(new RealScore(realScore, realExperience));
            } else {
                return Result.Fail(ErrorCode.ScoreSubToDBFail);
            }
        } else {
            return Result.Fail(ErrorCode.NoScoreForSubFail);
        }
    }
}
