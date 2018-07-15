package com.tomcat.status.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tomcat.status.dao.connectorMapper;
import com.tomcat.status.model.connector;
import com.tomcat.status.service.ConnectorService;

@Service("ConnectorService")
public class ConnectorServiceImpl implements ConnectorService{
	@Resource
	private connectorMapper connectormapper;
	
	@Override
	public List<connector> selectTopTwo(String ipAddress,int port) {
		// TODO Auto-generated method stub
		return this.connectormapper.selectTopTwo(ipAddress,port);
	}

}
