package com.tomcat.status.service.impl;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tomcat.status.dao.connectingMapper;
import com.tomcat.status.model.connecting;
import com.tomcat.status.model.connectingKey;
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
	@Override
	public String releaseConnecting(String ipAddress, int port) {
		// TODO Auto-generated method stub
		connectingKey connectingKey = new connectingKey();
		connectingKey.setIpaddress(ipAddress);
		connectingKey.setPort(port);
		if(this.connectingmapper.selectOne(connectingKey)!=1)
			return "no_this_record";
		connecting connecting = this.connectingmapper.selectByPrimaryKey(connectingKey);
		Calendar now = Calendar.getInstance();
		connecting.setLastTime(now.getTime());
		connecting.setStatus("断开连接");
		if(this.connectingmapper.updateByPrimaryKeySelective(connecting)==1)
			return "success";
		return "更新数据库失败";
	}
	@Override
	public String updateConnecting(String ipAddress, int port) {
		// TODO Auto-generated method stub
				Calendar now = Calendar.getInstance();		
				connectingKey connectingKey = new connectingKey();
				connectingKey.setIpaddress(ipAddress);
				connectingKey.setPort(port);
				if(this.connectingmapper.selectOne(connectingKey)!=1) {
					connecting conn = new connecting();
					conn.setIpaddress(ipAddress);
					conn.setName(ipAddress);
					conn.setLastTime(now.getTime());
					conn.setPort(port);
					conn.setStatus("已连接");
					if(this.connectingmapper.insert(conn)==1)
						return "success";
					return "数据库插入失败";
				}
				connecting connecting = this.connectingmapper.selectByPrimaryKey(connectingKey);
				
				connecting.setLastTime(now.getTime());
				connecting.setStatus("已连接");
				if(this.connectingmapper.updateByPrimaryKeySelective(connecting)==1)
					return "success";
				return "更新数据库失败";
	}
	@Override
	public List<connecting> selectUnusedConnecting() {
		// TODO Auto-generated method stub
		return this.connectingmapper.selectUnusedConnecting();
	}

}
