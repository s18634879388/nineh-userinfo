package com.ninehcom.userinfo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * UserInfo实体类，将同时用于Mybatis和JPA使用
 *
 * @author shenjizhe
 * @version 1.0.0 Id 唯一标识 PhoneNumber 手机号码 NickName 昵称 Signature 个性签名 Portrait
 * 头像URL Name 姓名 Sex 性别(0:未知 1::男 2:女) Birthday 生日 Provinceid 省编码 Cityid 市编码
 * Idnum 身份证号码 Extra 扩展字段
 */
@Entity
public class UserInfo implements Serializable {
    
    @Id
    private String Id;

    private int ChangeNickNameTimes;

    private String PhoneNumber;

    private String NickName;

    private String Signature;

    private String Portrait;

    private String Name;

    private Integer Sex;

    private Date Birthday;

    private Integer Provinceid;

    private Integer Cityid;
    
    private Integer Districtid;

    private String Idnum;

    private String Extra;
    
    private Integer RoleId;

    private Date CreatedAt;

    private String VIPInfo;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public int getChangeNickNameTimes() {
        return ChangeNickNameTimes;
    }

    public void setChangeNickNameTimes(int changeNickNameTimes) {
        ChangeNickNameTimes = changeNickNameTimes;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public String getSignature() {
        return Signature;
    }

    public void setSignature(String signature) {
        Signature = signature;
    }

    public String getPortrait() {
        return Portrait;
    }

    public void setPortrait(String portrait) {
        Portrait = portrait;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getSex() {
        return Sex;
    }

    public void setSex(Integer sex) {
        Sex = sex;
    }

    public Date getBirthday() {
        return Birthday;
    }

    public void setBirthday(Date birthday) {
        Birthday = birthday;
    }

    public Integer getProvinceid() {
        return Provinceid;
    }

    public void setProvinceid(Integer provinceid) {
        Provinceid = provinceid;
    }

    public Integer getCityid() {
        return Cityid;
    }

    public void setCityid(Integer cityid) {
        Cityid = cityid;
    }

    public Integer getDistrictid() {
        return Districtid;
    }

    public void setDistrictid(Integer districtid) {
        Districtid = districtid;
    }

    public String getIdnum() {
        return Idnum;
    }

    public void setIdnum(String idnum) {
        Idnum = idnum;
    }

    public String getExtra() {
        return Extra;
    }

    public void setExtra(String extra) {
        Extra = extra;
    }

    public Integer getRoleId() {
        return RoleId;
    }

    public void setRoleId(Integer roleId) {
        RoleId = roleId;
    }

    public Date getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(Date createdAt) {
        CreatedAt = createdAt;
    }

    public String getVIPInfo() {
        return VIPInfo;
    }

    public void setVIPInfo(String VIPInfo) {
        this.VIPInfo = VIPInfo;
    }
}
