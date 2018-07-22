package com.tomcat.status.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tomcat.status.model.log;
import com.tomcat.status.model.logKey;

public interface logMapper {
    int deleteByPrimaryKey(logKey key);

    int insert(log record);

    int insertSelective(log record);

    log selectByPrimaryKey(logKey key);

    int updateByPrimaryKeySelective(log record);

    int updateByPrimaryKey(log record);
    
    List<log> selectUnreadLog(@Param("ipaddress")String ipAddress,@Param("port")int port);
    
    int selectUnSendLog();
    
    int updateSendLog();
    
    int clearUnreadLog();
    
    int clearLocalLog(@Param("ipaddress")String ipAddress,@Param("port")int port);
    
    int selectCountUnreadLog(@Param("ipaddress")String ipAddress,@Param("port")int port);
}