package com.tomcat.status.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.tomcat.status.model.connector;

public interface ConnectorService {
	public List<connector> selectTopTwo(String ipAddress,int port);
	public connector readJson(HttpServletRequest request) throws IOException;
	public String insert(connector record);
}
