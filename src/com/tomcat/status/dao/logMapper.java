package com.tomcat.status.dao;

import com.tomcat.status.model.log;
import com.tomcat.status.model.logKey;

public interface logMapper {
    int deleteByPrimaryKey(logKey key);

    int insert(log record);

    int insertSelective(log record);

    log selectByPrimaryKey(logKey key);

    int updateByPrimaryKeySelective(log record);

    int updateByPrimaryKey(log record);
}