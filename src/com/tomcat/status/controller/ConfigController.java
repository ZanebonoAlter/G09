package com.tomcat.status.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tomcat.status.model.config;
import com.tomcat.status.service.ConfigService;

@RestController
@RequestMapping("/config")
public class ConfigController {
	@Resource
	private ConfigService configservice;
	
	@RequestMapping("/Judge_Config")
	public String Judge_Config (String ipAddress,int port) {
		return this.configservice.select_one(ipAddress, port);	
	}
	
	@RequestMapping("/Add_Config")
	public String Add_Config(HttpServletRequest request) throws IOException {
		config config=this.configservice.readJson(request);
		if(this.configservice.insert(config)==1)
			return "success";
		return "false";
	}
}
