package com.ninehcom.userinfo.mapper;

import com.ninehcom.userinfo.entity.UserScore;

import java.util.Date;
import java.util.List;

/**
 * UserScore的Mapper，用于Mybatis
 *
 * @author shenjizhe
 * @version 1.0.0
 */
public interface UserScoreMapper {

    List<UserScore> selectAllUserScore();
    UserScore selectUserScoreByIDDate(String id, Date date);
    int insertUserScoreByIDDate(UserScore score);
    int updateUserScoreByIDDate(UserScore score);
}
