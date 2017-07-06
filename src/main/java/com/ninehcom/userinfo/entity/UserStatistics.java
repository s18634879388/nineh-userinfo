package com.ninehcom.userinfo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * UserStatistics实体类，将同时用于Mybatis和JPA使用
 * @author shenjizhe
 * @version 1.0.0
UserId                        用户id
Level                         用户级别
Title                         头衔
Score                         用户积分
Experience                    用户经验
MaxDays                       最大登陆天数
LastTime                      最后一次登录时间
AttentionCount                关注数量
FansCount                     粉丝数量
TopicCount                    发帖数
Rank                          用户排名
 */
@Entity
public class UserStatistics implements Serializable{
    public UserStatistics(){}
    public UserStatistics(String userId, int maxDays, Date date, long time) {
        this.UserId = userId;
        this.MaxDays = maxDays;
        this.LastTime = date;
        this.timeStamp = time;
    }

    @Id
    private String UserId;
    public  String getUserId() {
        return UserId;
    }
    public void setUserId(String UserId) {
        this.UserId = UserId;
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
    public  Integer getAttentionCount() {
        return AttentionCount;
    }
    public void setAttentionCount(Integer AttentionCount) {
        this.AttentionCount = AttentionCount;
    }

    private Integer FansCount;
    public  Integer getFansCount() {
        return FansCount;
    }
    public void setFansCount(Integer FansCount) {
        this.FansCount = FansCount;
    }

    private Integer Rank;
    public  Integer getRank() {
        return Rank;
    }
    public void setRank(Integer Rank) {
        this.Rank = Rank;
    }

    private String NickName;
    private String Portrait;

    private Long timeStamp;

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * @return the NickName
     */
    public String getNickName() {
        return NickName;
    }

    /**
     * @param NickName the NickName to set
     */
    public void setNickName(String NickName) {
        this.NickName = NickName;
    }

    /**
     * @return the Portrait
     */
    public String getPortrait() {
        return Portrait;
    }

    /**
     * @param Portrait the Portrait to set
     */
    public void setPortrait(String Portrait) {
        this.Portrait = Portrait;
    }
    
    private int TopicCount;

    /**
     * @return the TopicCount
     */
    public int getTopicCount() {
        return TopicCount;
    }

    /**
     * @param TopicCount the TopicCount to set
     */
    public void setTopicCount(int TopicCount) {
        this.TopicCount = TopicCount;
    }
}