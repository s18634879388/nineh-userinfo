package com.ninehcom.userinfo.mapper;

import com.ninehcom.userinfo.entity.UserStatistics;

import java.util.ArrayList;
import java.util.Date;

/**
 * UserStatistics的Mapper，用于Mybatis
 *
 * @author shenjizhe
 * @version 1.0.0
 */
public interface UserStatisticsMapper {

    ArrayList<UserStatistics> selectUserStatisticsRankList(int count);

    UserStatistics selectUserStatisticsByUserId(String userId);

    int insertUserStatistics(String userId, int level, String title);

    int updateUserStatistics(UserStatistics statics);

    int selectCountByDate(Date date);
}
