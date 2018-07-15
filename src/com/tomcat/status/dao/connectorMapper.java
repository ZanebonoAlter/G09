package com.tomcat.status.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tomcat.status.model.connector;
import com.tomcat.status.model.connectorKey;

public interface connectorMapper {
    int deleteByPrimaryKey(connectorKey key);

    int insert(connector record);

    int insertSelective(connector record);

    connector selectByPrimaryKey(connectorKey key);

    int updateByPrimaryKeySelective(connector record);

    int updateByPrimaryKey(connector record);
    
    List<connector> selectTopTwo(@Param("ipaddress")String ipAddress,@Param("port")int port);
}