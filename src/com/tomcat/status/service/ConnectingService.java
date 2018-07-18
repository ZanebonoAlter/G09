package com.tomcat.status.service;

import java.util.List;

import com.tomcat.status.model.connecting;

public interface ConnectingService {
	public List<connecting> selectAllConnecting();
	public String releaseConnecting(String ipAddress,int port);
	public String updateConnecting(String ipAddress,int port);
	public List<connecting> selectUnusedConnecting();
}
