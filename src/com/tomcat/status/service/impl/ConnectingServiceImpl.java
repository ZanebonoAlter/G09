package com.tomcat.status.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tomcat.status.dao.connectingMapper;
import com.tomcat.status.model.connecting;
import com.tomcat.status.service.ConnectingService;

@Service("ConnectingService")
public class ConnectingServiceImpl implements ConnectingService{
	@Resource
	private connectingMapper connectingmapper;
	@Override
	public List<connecting> selectAllConnecting() {
		// TODO Auto-generated method stub
		return this.connectingmapper.selectAllConnecting();
	}

}
