package com.ninehcom.userinfo.view;

import com.ninehcom.userinfo.entity.Tag;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * UserInfoWrapper实体类，将同时用于Mybatis和JPA使用
 * @author shenjizhe
 * @version 1.0.0
Id                            唯一标识
NickName                      昵称
Signature                     个性签名
Portrait                      头像URL
Name                          姓名
Sex                           性别(0:未知 1::男 2:女)
Birthday                      生日
Provinceid                    省编码
Cityid                        市编码
Districtid
Idnum                         身份证号码
Extra                         扩展字段
RoleId                        权限（0：普通用户 1：管理员 11版主）
Level                         用户级别
Title                         头衔
Score                         用户积分
Experience                    用户经验
MaxDays                       最大登陆天数
LastTime                      最后一次登录时间
 */
@Entity
public class UserInfoWrapper implements Serializable {

    @Id
    private String Id;
    
    private String ChangeNickNameTimes;

    private String NickName;

    private String Signature;

    private String Portrait;

    private String Name;

    private Integer Sex;

    private Date Birthday;

    private Integer Provinceid;

    private Integer Cityid;
    
    private Integer Districtid;

    private String Idnum;

    private String Extra;

    private Integer RoleId;

    private Integer Level;

    private String Title;

    private Integer Score;

    private Integer Experience;

    private Integer MaxDays;

    private Date LastTime;

    private Integer AttentionCount;

    private Integer FansCount;
    
    private Integer TopicCount;
    
    private ArrayList<Tag> tagList;
    
    private String VIPInfo;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getChangeNickNameTimes() {
        return ChangeNickNameTimes;
    }

    public void setChangeNickNameTimes(String changeNickNameTimes) {
        ChangeNickNameTimes = changeNickNameTimes;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public String getSignature() {
        return Signature;
    }

    public void setSignature(String signature) {
        Signature = signature;
    }

    public String getPortrait() {
        return Portrait;
    }

    public void setPortrait(String portrait) {
        Portrait = portrait;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getSex() {
        return Sex;
    }

    public void setSex(Integer sex) {
        Sex = sex;
    }

    public Date getBirthday() {
        return Birthday;
    }

    public void setBirthday(Date birthday) {
        Birthday = birthday;
    }

    public Integer getProvinceid() {
        return Provinceid;
    }

    public void setProvinceid(Integer provinceid) {
        Provinceid = provinceid;
    }

    public Integer getCityid() {
        return Cityid;
    }

    public void setCityid(Integer cityid) {
        Cityid = cityid;
    }

    public Integer getDistrictid() {
        return Districtid;
    }

    public void setDistrictid(Integer districtid) {
        Districtid = districtid;
    }

    public String getIdnum() {
        return Idnum;
    }

    public void setIdnum(String idnum) {
        Idnum = idnum;
    }

    public String getExtra() {
        return Extra;
    }

    public void setExtra(String extra) {
        Extra = extra;
    }

    public Integer getRoleId() {
        return RoleId;
    }

    public void setRoleId(Integer roleId) {
        RoleId = roleId;
    }

    public Integer getLevel() {
        return Level;
    }

    public void setLevel(Integer level) {
        Level = level;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Integer getScore() {
        return Score;
    }

    public void setScore(Integer score) {
        Score = score;
    }

    public Integer getExperience() {
        return Experience;
    }

    public void setExperience(Integer experience) {
        Experience = experience;
    }

    public Integer getMaxDays() {
        return MaxDays;
    }

    public void setMaxDays(Integer maxDays) {
        MaxDays = maxDays;
    }

    public Date getLastTime() {
        return LastTime;
    }

    public void setLastTime(Date lastTime) {
        LastTime = lastTime;
    }

    public Integer getAttentionCount() {
        return AttentionCount;
    }

    public void setAttentionCount(Integer attentionCount) {
        AttentionCount = attentionCount;
    }

    public Integer getFansCount() {
        return FansCount;
    }

    public void setFansCount(Integer fansCount) {
        FansCount = fansCount;
    }

    public Integer getTopicCount() {
        return TopicCount;
    }

    public void setTopicCount(Integer topicCount) {
        TopicCount = topicCount;
    }

    public ArrayList<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(ArrayList<Tag> tagList) {
        this.tagList = tagList;
    }

    public String getVIPInfo() {
        return VIPInfo;
    }

    public void setVIPInfo(String VIPInfo) {
        this.VIPInfo = VIPInfo;
    }
}