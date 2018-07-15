package com.tomcat.status.dao;

import org.apache.ibatis.annotations.Param;

import com.tomcat.status.model.memory;
import com.tomcat.status.model.memoryKey;

public interface memoryMapper {
    int deleteByPrimaryKey(memoryKey key);

    int insert(memory record);

    int insertSelective(memory record);

    memory selectByPrimaryKey(memoryKey key);

    int updateByPrimaryKeySelective(memory record);

    int updateByPrimaryKey(memory record);
    
    memory selectTop(@Param("ipaddress")String ipaddress,@Param("port")int port);
}