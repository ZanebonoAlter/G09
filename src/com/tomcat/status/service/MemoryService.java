package com.tomcat.status.service;

import com.tomcat.status.model.memory;

public interface MemoryService {
	public memory selectTop(String ipAddress,int port);
}
