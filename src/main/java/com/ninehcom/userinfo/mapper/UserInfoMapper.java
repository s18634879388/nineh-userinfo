package com.ninehcom.userinfo.mapper;

import com.ninehcom.userinfo.entity.Tag;
import com.ninehcom.userinfo.entity.UserInfo;
import com.ninehcom.userinfo.view.UserInfoWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * UserUserinfo的Mapper，用于Mybatis
 *
 * @author shenjizhe
 * @version 1.0.0
 */
public interface UserInfoMapper {

    UserInfo selectUserInfoById(String Id);

    UserInfoWrapper selectUserInfoWrapperById(String Id);
    
    Long selectUserIdFromNHID(String nhId);
    
    String selectNHIdFromUserId(Object userId);

    ArrayList<UserInfoWrapper> selectUserInfoWrapperByIds(List<String> Ids);

    ArrayList<Tag> selectTagByUserId(String userId);

    int selectNickNameChangeTime(String Id);

    int insertUser(String Id, String nickName);

    int insertUserInfo(UserInfo user);

    int updateUserInfo(UserInfo user);

    int updateUserNickName(String userId, String nickName);

    int updateUserMobileNumber(String Id, String mobileNum);

    int selectUserPrivilege(String userId, String privilegeKey);

    List<UserInfo> selectUserLike(String nickname);
}
