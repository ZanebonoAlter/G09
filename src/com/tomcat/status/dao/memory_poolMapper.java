package com.tomcat.status.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tomcat.status.model.memory_pool;
import com.tomcat.status.model.memory_poolKey;

public interface memory_poolMapper {
    int deleteByPrimaryKey(memory_poolKey key);

    int insert(memory_pool record);

    int insertSelective(memory_pool record);

    memory_pool selectByPrimaryKey(memory_poolKey key);

    int updateByPrimaryKeySelective(memory_pool record);

    int updateByPrimaryKey(memory_pool record);
    
    List<memory_pool> selectAllMemory_Pool(@Param("ipaddress")String ipaddress,@Param("port")int port);
}