package com.ninehcom.userinfo.service;

import com.ninehcom.common.enums.ErrorCode;
import com.ninehcom.common.util.Result;
import com.ninehcom.userinfo.conf.EditConfigInit;
import com.ninehcom.userinfo.entity.Version;
import com.ninehcom.userinfo.entity.Versions;
import com.ninehcom.userinfo.enums.ConfigKeys;
import com.ninehcom.userinfo.mapper.VersionsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Versions的Service
 *
 * @author shenjizhe
 * @version 1.0.0
 */
@Service
public class VersionsService {

    @Autowired
    private VersionsMapper versionsMapper;
    @Autowired
    private EditConfigInit editConfigInit;

    //private HashMap<String, Versions> map;
//    @PostConstruct
//    private void init() {
//        ArrayList<Versions> versions = versionsMapper.selectAllVersions();
//        map = new HashMap<>();
//        for (Versions version : versions) {
//            map.put(version.getId(), version);
//        }
//    }
    private String getVerionURL(String systemtypeid,String appid) {
        if (systemtypeid.compareTo("1") == 0) {
//            return editconfigService.getValue(ConfigKeys.AndroidLoadURL);
            return editConfigInit.getValue(ConfigKeys.AndroidLoadURL,appid);
        } else if (systemtypeid.compareTo("2") == 0) {
//            return editconfigService.getValue(ConfigKeys.IOSLoadURL);
            return editConfigInit.getValue(ConfigKeys.IOSLoadURL,appid);
        }
        return null;
    }

    public Result validateVersion(String systemtypeid, String versionNum) {
        Boolean isNewest = false;
        Boolean isTooOld = false;
        Versions ver = versionsMapper.selectVersionByType(systemtypeid);
        if (ver != null) {
            // validate param
            Version serviceOldVer = Version.createVersion(ver.getVersionNum());
            if (serviceOldVer == null) {
                return Result.Fail(ErrorCode.ServiceOldVersionWrongFormat);
            }
            Version serviceNewVer = Version.createVersion(ver.getLatestVersionNum());
            if (serviceNewVer == null) {
                return Result.Fail(ErrorCode.ServiceNewVersionWrongFormat);
            }
            Version clientVer = Version.createVersion(versionNum);
            if (clientVer == null) {
                return Result.Fail(ErrorCode.ClientVersionWrongFormat);
            }

            int comOldValue = Version.compare(clientVer, serviceOldVer);
            int comNewValue = Version.compare(clientVer, serviceNewVer);

            if (comOldValue < 0) {
                isNewest = false;
                isTooOld = true;
            } else if (comNewValue < 0) {
                isNewest = false;
                isTooOld = false;
            } else {
                isNewest = true;
                isTooOld = false;
            }

            HashMap<String, String> map = new HashMap<>();
            map.put("isLatest", isNewest.toString());
            map.put("isTooOld", isTooOld.toString());
            map.put("url", ver.getLatestURL());
            map.put("version", ver.getLatestVersionNum());
            map.put("latestVersionInfo", ver.getLatestVersionInfo());
            map.put("size", ver.getSizeText());
            map.put("forceUpdateTip", ver.getForceUpdateTip());
            return Result.Success(map);
        } else {
            return Result.Fail(ErrorCode.VersionTypeNotExistFail);
        }
    }
}
