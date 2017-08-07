package com.ninehcom.userinfo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * UserScore实体类，将同时用于Mybatis和JPA使用
 *
 * @author shenjizhe
 * @version 1.0.0 Userid 用户ID Date 日期 Score 每日积分 Experience 每日经验
 */
@Entity
public class UserScore implements Serializable {

    public UserScore() {
    }

    public UserScore(String Userid, Date Date, int Score, int Experience) {
        this.Userid = Userid;
        this.Date = Date;
        this.Score = Score;
        this.Experience = Experience;
    }

    @Id
    private String Userid;

    private Date Date;

    private int Score;

    private int Experience;

    public String getUserid() {
        return Userid;
    }

    public void setUserid(String userid) {
        Userid = userid;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public int getExperience() {
        return Experience;
    }

    public void setExperience(int experience) {
        Experience = experience;
    }
}
