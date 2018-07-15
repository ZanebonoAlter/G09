package com.tomcat.status.service;

import java.util.List;

import com.tomcat.status.model.memory_pool;

public interface MemoryPoolService {
	public List<memory_pool> selectAllMemory_Pool(String ipAddress,int port);
}
