package com.tomcat.status.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.tomcat.status.model.memory_pool;

public interface MemoryPoolService {
	public List<memory_pool> selectAllMemory_Pool(String ipAddress,int port);
	public memory_pool readJson(HttpServletRequest request) throws IOException;
	public String insert(memory_pool memory_pool);
}
