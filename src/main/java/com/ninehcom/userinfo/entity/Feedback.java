package com.ninehcom.userinfo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Feedback实体类，将同时用于Mybatis和JPA使用
 * @author shenjizhe
 * @version 1.0.0
Id                            反馈标识码（UUID）
UserId                        用户ID
Time                          反馈时间
Content                       反馈内容
Type                          反馈类型(预留字段)用作反馈问题的归类
ReadTime                      审阅时间
Readed                        是否已经审阅
 */
@Entity
public class Feedback {

    @Id
    private String Id;

    private String UserId;

    private Date Time;

    private String Content;

    private String Type;

    private Date ReadTime;

    private boolean Readed;

    private String Record;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public Date getTime() {
        return Time;
    }

    public void setTime(Date time) {
        Time = time;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public Date getReadTime() {
        return ReadTime;
    }

    public void setReadTime(Date readTime) {
        ReadTime = readTime;
    }

    public boolean isReaded() {
        return Readed;
    }

    public void setReaded(boolean readed) {
        Readed = readed;
    }

    public String getRecord() {
        return Record;
    }

    public void setRecord(String record) {
        Record = record;
    }
}
