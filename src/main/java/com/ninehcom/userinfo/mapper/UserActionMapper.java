package com.ninehcom.userinfo.mapper;

import com.ninehcom.userinfo.entity.UserAction;

import java.util.ArrayList;
import java.util.Date;

/**
 * UserAction的Mapper，用于Mybatis
 *
 * @author shenjizhe
 * @version 1.0.0
 */
public interface UserActionMapper {

    ArrayList<UserAction> selectUserActionByType(String userId, int type);

    ArrayList<UserAction> selectUserActionByTypes(String userId);

    // 判断当天是不是连续日
    int selectUserActionByLastDate(String userId, int type, Date date);
    
    int insertUserAction(UserAction action);

    ArrayList<UserAction> selectUserActionByDate(String userId, Date date);

    ArrayList<UserAction> selectUserActionByDate1(String userId, Date date);
    
    ArrayList<UserAction> selectUserActionByMonth(String userId, int year, int month);

    int selectLeaguecalendarByDate(String teamId, Date date);
    
}
