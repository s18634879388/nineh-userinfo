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
    public  String getId() {
        return Id;
    }
    public void setId(String Id) {
        this.Id = Id;
    }
    
    private String ChangeNickNameTimes;

    private String NickName;
    public  String getNickName() {
        return NickName;
    }
    public void setNickName(String NickName) {
        this.NickName = NickName;
    }

    private String Signature;
    public  String getSignature() {
        return Signature;
    }
    public void setSignature(String Signature) {
        this.Signature = Signature;
    }

    private String Portrait;
    public  String getPortrait() {
        return Portrait;
    }
    public void setPortrait(String Portrait) {
        this.Portrait = Portrait;
    }

    private String Name;
    public  String getName() {
        return Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }

    private Integer Sex;
    public  Integer getSex() {
        return Sex;
    }
    public void setSex(Integer Sex) {
        this.Sex = Sex;
    }

    private Date Birthday;
    public  Date getBirthday() {
        return Birthday;
    }
    public void setBirthday(Date Birthday) {
        this.Birthday = Birthday;
    }

    private Integer Provinceid;
    public  Integer getProvinceid() {
        return Provinceid;
    }
    public void setProvinceid(Integer Provinceid) {
        this.Provinceid = Provinceid;
    }

    private Integer Cityid;
    public  Integer getCityid() {
        return Cityid;
    }
    public void setCityid(Integer Cityid) {
        this.Cityid = Cityid;
    }
    
    private Integer Districtid;
    public  Integer getDistrictid() {
        return Districtid;
    }
    public void setDistrictid(Integer Districtid) {
        this.Districtid = Districtid;
    }

    private String Idnum;
    public  String getIdnum() {
        return Idnum;
    }
    public void setIdnum(String Idnum) {
        this.Idnum = Idnum;
    }

    private String Extra;
    public  String getExtra() {
        return Extra;
    }
    public void setExtra(String Extra) {
        this.Extra = Extra;
    }

    private Integer RoleId;
    public  Integer getRoleId() {
        return RoleId;
    }
    public void setRoleId(Integer RoleId) {
        this.RoleId = RoleId;
    }

    private Integer Level;
    public  Integer getLevel() {
        return Level;
    }
    public void setLevel(Integer Level) {
        this.Level = Level;
    }

    private String Title;
    public  String getTitle() {
        return Title;
    }
    public void setTitle(String Title) {
        this.Title = Title;
    }

    private Integer Score;
    public  Integer getScore() {
        return Score;
    }
    public void setScore(Integer Score) {
        this.Score = Score;
    }

    private Integer Experience;
    public  Integer getExperience() {
        return Experience;
    }
    public void setExperience(Integer Experience) {
        this.Experience = Experience;
    }

    private Integer MaxDays;
    public  Integer getMaxDays() {
        return MaxDays;
    }
    public void setMaxDays(Integer MaxDays) {
        this.MaxDays = MaxDays;
    }

    private Date LastTime;
    public  Date getLastTime() {
        return LastTime;
    }
    public void setLastTime(Date LastTime) {
        this.LastTime = LastTime;
    }

    private Integer AttentionCount;
    private Integer FansCount;

    /**
     * @return the AttentionCount
     */
    public Integer getAttentionCount() {
        return AttentionCount;
    }

    /**
     * @param AttentionCount the AttentionCount to set
     */
    public void setAttentionCount(Integer AttentionCount) {
        this.AttentionCount = AttentionCount;
    }

    /**
     * @return the FansCount
     */
    public Integer getFansCount() {
        return FansCount;
    }

    /**
     * @param FansCount the FansCount to set
     */   
    public void setFansCount(Integer FansCount) {
        this.FansCount = FansCount;
    }
    
    private Integer TopicCount;

    /**
     * @return the TopicCount
     */
    public Integer getTopicCount() {
        return TopicCount;
    }

    /**
     * @param TopicCount the TopicCount to set
     */
    public void setTopicCount(Integer TopicCount) {
        this.TopicCount = TopicCount;
    }

    /**
     * @return the ChangeNickNameTimes
     */
    public String getChangeNickNameTimes() {
        return ChangeNickNameTimes;
    }

    /**
     * @param ChangeNickNameTimes the ChangeNickNameTimes to set
     */
    public void setChangeNickNameTimes(String ChangeNickNameTimes) {
        this.ChangeNickNameTimes = ChangeNickNameTimes;
    }
    
    private ArrayList<Tag> tagList;

    /**
     * @return the tagList
     */
    public ArrayList<Tag> getTagList() {
        return tagList;
    }

    /**
     * @param tagList the tagList to set
     */
    public void setTagList(ArrayList<Tag> tagList) {
        this.tagList = tagList;
    }
    
    private String VIPInfo;

    /**
     * @return the VIPInfo
     */
    public String getVIPInfo() {
        return VIPInfo;
    }

    /**
     * @param VIPInfo the VIPInfo to set
     */
    public void setVIPInfo(String VIPInfo) {
        this.VIPInfo = VIPInfo;
    }
}