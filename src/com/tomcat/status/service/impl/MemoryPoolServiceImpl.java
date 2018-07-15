package com.tomcat.status.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tomcat.status.dao.memory_poolMapper;
import com.tomcat.status.model.memory_pool;
import com.tomcat.status.service.MemoryPoolService;

@Service("MemoryPoolService")
public class MemoryPoolServiceImpl implements MemoryPoolService{
	@Resource
	private memory_poolMapper memory_poolmapper;
	@Override
	public List<memory_pool> selectAllMemory_Pool(String ipaddress,int port) {
		// TODO Auto-generated method stub
		return this.memory_poolmapper.selectAllMemory_Pool(ipaddress, port);
	}

}
