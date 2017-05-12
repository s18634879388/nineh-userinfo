package com.ninehcom.userinfo.mapper;

import com.ninehcom.userinfo.entity.Feedback;

import java.util.ArrayList;

/**
 * Feedback的Mapper，用于Mybatis
 *
 * @author shenjizhe
 * @version 1.0.0
 */
public interface FeedbackMapper {

    ArrayList<Feedback> selectAllFeedback(int readed);

    int insertFeedback(String UserId, String Content);

    int updateFeedback(String Id, int Type, String Record);
}
