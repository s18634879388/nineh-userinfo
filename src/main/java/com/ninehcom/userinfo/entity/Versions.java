package com.ninehcom.userinfo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
    名称:Versions实体类
    描述:客户端版本
 * @author shenjizhe
 * @version 1.0.0

### 客户端版本 ###
<A NAME="Versions">Versions</A>

名称|类型|描述
-|-|-
Id                  |Integer   |逻辑主键
VersionsTypeId      |Integer   |版本类型ID
VersionsTypeName    |String    |版本类型名称
VersionNum          |String    |版本号（四段式）
LatestVersionNum    |String    |最新版本号
LatestURL           |String    |最新版本下载地址
LatestVersionInfo   |String    |最新版本信息
SizeText            |String    |大小(文本)
ForceUpdateTip      |String    |强制更新提醒
 */
@Entity
public class Versions {

    @Id
    private String Id;
    public  String getId() {
        return Id;
    }
    public void setId(String Id) {
        this.Id = Id;
    }

    private Integer VersionsTypeId;
    public  Integer getVersionsTypeId() {
        return VersionsTypeId;
    }
    public void setVersionsTypeId(Integer VersionsTypeId) {
        this.VersionsTypeId = VersionsTypeId;
    }

    private String VersionsTypeName;
    public  String getVersionsTypeName() {
        return VersionsTypeName;
    }
    public void setVersionsTypeName(String VersionsTypeName) {
        this.VersionsTypeName = VersionsTypeName;
    }

    private String VersionNum;
    public  String getVersionNum() {
        return VersionNum;
    }
    public void setVersionNum(String VersionNum) {
        this.VersionNum = VersionNum;
    }

    private String LatestVersionNum;
    public  String getLatestVersionNum() {
        return LatestVersionNum;
    }
    public void setLatestVersionNum(String LatestVersionNum) {
        this.LatestVersionNum = LatestVersionNum;
    }

    private String LatestURL;
    public  String getLatestURL() {
        return LatestURL;
    }
    public void setLatestURL(String LatestURL) {
        this.LatestURL = LatestURL;
    }

    private String LatestVersionInfo;
    public  String getLatestVersionInfo() {
        return LatestVersionInfo;
    }
    public void setLatestVersionInfo(String LatestVersionInfo) {
        this.LatestVersionInfo = LatestVersionInfo;
    }

    private String SizeText;
    public  String getSizeText() {
        return SizeText;
    }
    public void setSizeText(String SizeText) {
        this.SizeText = SizeText;
    }

    private String ForceUpdateTip;
    public  String getForceUpdateTip() {
        return ForceUpdateTip;
    }
    public void setForceUpdateTip(String ForceUpdateTip) {
        this.ForceUpdateTip = ForceUpdateTip;
    }

}