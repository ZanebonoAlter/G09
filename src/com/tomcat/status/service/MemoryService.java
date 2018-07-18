package com.tomcat.status.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.tomcat.status.model.memory;

public interface MemoryService {
	public memory selectTop(String ipAddress,int port);
	public memory readJson(HttpServletRequest request) throws IOException;
	public String insert(memory memory);
}
