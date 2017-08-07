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

    private Integer Level;

    private String Title;

    private Integer Score;

    private Integer Experience;

    private Integer MaxDays;

    private Date LastTime;

    private Integer AttentionCount;

    private Integer FansCount;

    private Integer Rank;

    private String NickName;

    private String Portrait;

    private Long timeStamp;
    
    private int TopicCount;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
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

    public Integer getRank() {
        return Rank;
    }

    public void setRank(Integer rank) {
        Rank = rank;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public String getPortrait() {
        return Portrait;
    }

    public void setPortrait(String portrait) {
        Portrait = portrait;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getTopicCount() {
        return TopicCount;
    }

    public void setTopicCount(int topicCount) {
        TopicCount = topicCount;
    }
}