package com.tomcat.status.dao;

import java.util.List;

import com.tomcat.status.model.connecting;
import com.tomcat.status.model.connectingKey;

public interface connectingMapper {
    int deleteByPrimaryKey(connectingKey key);

    int insert(connecting record);

    int insertSelective(connecting record);

    connecting selectByPrimaryKey(connectingKey key);

    int updateByPrimaryKeySelective(connecting record);

    int updateByPrimaryKey(connecting record);
    
    List<connecting> selectAllConnecting();
    
    int selectOne(connectingKey key);
    
    List<connecting> selectUnusedConnecting();
}