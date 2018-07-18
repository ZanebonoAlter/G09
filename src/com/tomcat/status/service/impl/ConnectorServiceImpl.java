package com.tomcat.status.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.tomcat.status.dao.connectorMapper;
import com.tomcat.status.model.config;
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

	@Override
	public connector readJson(HttpServletRequest request) throws IOException {
			Gson gson = new Gson();
			StringBuffer jb = new StringBuffer();
	          String line = null;
	          connector conn = null;
	          try {
	              //读取输入流到StringBuffer中
	              BufferedReader reader = request.getReader();
	                while ((line = reader.readLine()) != null)
	                  jb.append(line);
	                 
	          } catch (Exception e) { /*report an error*/ }

	          try {
	              //使用JSONObject的parseObject方法解析JSON字符串
	              
	              conn=gson.fromJson(jb.toString(), connector.class);
	                 
	          } catch (Exception e) {
	            // crash and burn
	            throw new IOException("Error parsing JSON request string");
	          }
	          //System.out.print(config.getIpaddress());
			//System.out.println(gson.fromJson(config, config.getClass()));
			return conn;		
	}

	@Override
	public String insert(connector record) {
		// TODO Auto-generated method stub
		int flag=this.connectormapper.insert(record);
		if(flag==1)
			return "success";
		return "插入失败";
	}

}
