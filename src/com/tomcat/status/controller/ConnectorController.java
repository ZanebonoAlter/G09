package com.tomcat.status.controller;

import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.tomcat.status.model.config;
import com.tomcat.status.model.configKey;
import com.tomcat.status.model.connector;
import com.tomcat.status.service.ConfigService;
import com.tomcat.status.service.ConnectorService;
import com.tomcat.status.service.LogService;

@RestController
@RequestMapping("/connector")
public class ConnectorController {
	@Resource
	private ConnectorService connectorservice;
	@Resource
	private ConfigService configservice;
	@Resource
	private LogService logservice;
	
	@RequestMapping(value="/show_top_two",produces="text/html;charset=UTF-8")
	public String show_top_two(@RequestParam("callback") String callback,HttpServletRequest request,HttpServletResponse resp,ModelMap model,String ipAddress,int port) {
		List<connector> ls = connectorservice.selectTopTwo(ipAddress,port);
		Gson gson=new Gson();  
		return callback+"("+gson.toJson(ls)+")";   		
	}
	@RequestMapping("/write")
	public String write(HttpServletRequest request) throws IOException {
		connector connector = this.connectorservice.readJson(request);
		configKey configKey = new configKey();
		configKey.setIpaddress(connector.getIpaddress());
		configKey.setPort(connector.getPort());
		config config = this.configservice.getConfigByKey(configKey);
		this.logservice.Log_Connector(connector, config);
		return this.connectorservice.insert(connector);
	}
}
