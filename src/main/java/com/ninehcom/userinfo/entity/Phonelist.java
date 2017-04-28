package com.ninehcom.userinfo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
    名称:Phonelist实体类
    描述:
 * @author shenjizhe
 * @version 1.0.0

###  ###
<A NAME="Phonelist">Phonelist</A>

名称|类型|描述
-|-|-
phone               |String    |手机号码
param               |String    |个性参数
 */
@Entity
public class Phonelist implements Serializable{

    @Id
    private String phone;
    public  String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    private String param;
    public  String getParam() {
        return param;
    }
    public void setParam(String param) {
        this.param = param;
    }

}