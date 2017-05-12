package com.ninehcom.userinfo.mapper;

import com.ninehcom.userinfo.entity.Versions;

import java.util.ArrayList;

/**
 * Versions的Mapper，用于Mybatis
 *
 * @author shenjizhe
 * @version 1.0.0
 */
public interface VersionsMapper {

    ArrayList<Versions> selectAllVersions();
    Versions selectVersionByType(String typeid);
}
