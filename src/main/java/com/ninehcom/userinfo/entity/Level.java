package com.ninehcom.userinfo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Level实体类，将同时用于Mybatis和JPA使用
 * @author shenjizhe
 * @version 1.0.0
Level                         等级
Title                         头衔
Experience                    经验阈值
 */
@Entity
public class Level {

    @Id
    private int Id;
    public  int getId() {
        return Id;
    }
    public void setId(int Id) {
        this.Id = Id;
    }

    private String Title;
    public  String getTitle() {
        return Title;
    }
    public void setTitle(String Title) {
        this.Title = Title;
    }

    private int Experience;
    public  int getExperience() {
        return Experience;
    }
    public void setExperience(int Experience) {
        this.Experience = Experience;
    }

}
