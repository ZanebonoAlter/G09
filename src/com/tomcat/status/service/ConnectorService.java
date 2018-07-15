package com.tomcat.status.service;

import java.util.List;

import com.tomcat.status.model.connector;

public interface ConnectorService {
	public List<connector> selectTopTwo(String ipAddress,int port);
}
