package com.ninehcom.userinfo.mapper;

import com.ninehcom.userinfo.entity.Level;
import com.ninehcom.userinfo.entity.Phonelist;

import java.util.ArrayList;

/**
 * Level的Mapper，用于Mybatis
 *
 * @author shenjizhe
 * @version 1.0.0
 */
public interface LevelMapper {

    ArrayList< Level> selectAllLevel();
    ArrayList<Phonelist> selectgroup();
}
