package com.tomcat.status.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tomcat.status.dao.memoryMapper;
import com.tomcat.status.model.memory;
import com.tomcat.status.service.MemoryService;

@Service("MemoryService")
public class MemoryServiceImpl implements MemoryService{
	@Resource
	private memoryMapper memorymapper;
	@Override
	public memory selectTop(String ipAddress, int port) {
		// TODO Auto-generated method stub
		return this.memorymapper.selectTop(ipAddress, port);
	}
	
}
