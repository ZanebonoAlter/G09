package com.tomcat.status.dao;

import com.tomcat.status.model.config;
import com.tomcat.status.model.configKey;

public interface configMapper {
    int deleteByPrimaryKey(configKey key);

    int insert(config record);

    int insertSelective(config record);

    config selectByPrimaryKey(configKey key);

    int updateByPrimaryKeySelective(config record);

    int updateByPrimaryKey(config record);
    
    int select_one(configKey key);
}