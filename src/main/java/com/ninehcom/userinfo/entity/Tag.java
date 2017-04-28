package com.ninehcom.userinfo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
    名称:Tag实体类
    描述:用户标签
 * @author shenjizhe
 * @version 1.0.0

### 用户标签 ###
<A NAME="Tag">Tag</A>

名称|类型|描述
-|-|-
id                  |Integer   |标签ID
key                 |String    |标签键
title               |String    |标签标题
icon_url            |String    |图标URL
 */
@Entity
public class Tag implements Serializable{

    @Id
    private Integer id;
    public  Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    private String key;
    public  String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }

    private String title;
    public  String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    private String iconUrl;
    public  String getIconUrl() {
        return iconUrl;
    }
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

}