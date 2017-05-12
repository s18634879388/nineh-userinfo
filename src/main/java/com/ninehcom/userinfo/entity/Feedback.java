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
    public  String getId() {
        return Id;
    }
    public void setId(String Id) {
        this.Id = Id;
    }

    private String UserId;
    public  String getUserId() {
        return UserId;
    }
    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    private Date Time;
    public  Date getTime() {
        return Time;
    }
    public void setTime(Date Time) {
        this.Time = Time;
    }

    private String Content;
    public  String getContent() {
        return Content;
    }
    public void setContent(String Content) {
        this.Content = Content;
    }

    private String Type;
    public  String getType() {
        return Type;
    }
    public void setType(String Type) {
        this.Type = Type;
    }

    private Date ReadTime;
    public  Date getReadTime() {
        return ReadTime;
    }
    public void setReadTime(Date ReadTime) {
        this.ReadTime = ReadTime;
    }

    private boolean Readed;
    public  boolean getReaded() {
        return Readed;
    }
    public void setReaded(boolean Readed) {
        this.Readed = Readed;
    }

    private String Record;

    /**
     * @return the Record
     */
    public String getRecord() {
        return Record;
    }

    /**
     * @param Record the Record to set
     */
    public void setRecord(String Record) {
        this.Record = Record;
    }
}
