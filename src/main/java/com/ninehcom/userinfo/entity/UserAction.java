package com.ninehcom.userinfo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * UserAction实体类，将同时用于Mybatis和JPA使用
 *
 * @author shenjizhe
 * @version 1.0.0 
 * Id 行为ID 
 * Userid 用户id 
 * Time 动作发生时间 
 * Type 动作类型(1.签到) 
 * Param 行为参数
 * Score 行为积分
 */
@Entity
public class UserAction implements Serializable{

    public UserAction() {
    }

    public static String generationId() {
        return UUID.randomUUID().toString();
    }

    public UserAction(String Userid, int Type, Date Time, Integer Param, Integer Score, Long timeStamp) {
        this.Id = generationId();
        this.Userid = Userid;
        this.Time = Time;
        this.Type = Type;
        this.Param = Param;
        this.Score = Score;
        this.timeStamp = timeStamp;
    }

    @Id
    private String Id;

    private String Userid;

    private Date Time;

    private int Type;

    private Integer Param;

    private int Score;

    private Long timeStamp;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUserid() {
        return Userid;
    }

    public void setUserid(String userid) {
        Userid = userid;
    }

    public Date getTime() {
        return Time;
    }

    public void setTime(Date time) {
        Time = time;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public Integer getParam() {
        return Param;
    }

    public void setParam(Integer param) {
        Param = param;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
