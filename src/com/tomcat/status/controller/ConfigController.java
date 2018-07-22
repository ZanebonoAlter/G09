package com.tomcat.status.controller;

import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
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
	@RequestMapping(value="/Update_Config",produces="text/html;charset=UTF-8")
	public String Update_Config(@RequestParam("callback") String callback,String ipAddress,int port,double memoryTotal,double memoryPoolCompressedClassSpaceUsed,double connectorCurrentthreadscount,double connectorCurrentthreadsbusy,double memoryPoolCodeCacheUsed,double memoryPoolMetaspaceUsed,double memoryPoolPsEdenSpaceUsed,double memoryPoolPsOldGenUsed,double memoryPoolPsSurvivorSpaceUsed) {
		Gson gson=new Gson(); 
		config config = new config();
		config.setIpaddress(ipAddress);
		config.setPort(port);
		config.setMemoryTotal(memoryTotal);
		config.setMemoryPoolCompressedClassSpaceUsed(memoryPoolCompressedClassSpaceUsed);
		config.setConnectorCurrentthreadscount(connectorCurrentthreadscount);
		config.setConnectorCurrentthreadsbusy(connectorCurrentthreadsbusy);
		config.setMemoryPoolCodeCacheUsed(memoryPoolCodeCacheUsed);
		config.setMemoryPoolMetaspaceUsed(memoryPoolMetaspaceUsed);
		config.setMemoryPoolPsEdenSpaceUsed(memoryPoolPsEdenSpaceUsed);
		config.setMemoryPoolPsOldGenUsed(memoryPoolPsOldGenUsed);
		config.setMemoryPoolPsSurvivorSpaceUsed(memoryPoolPsSurvivorSpaceUsed);
        return callback+"("+gson.toJson(this.configservice.update_config(config))+")";
	}
	@RequestMapping(value="/Show_Config",produces="text/html;charset=UTF-8")
	public String Show_Config(@RequestParam("callback") String callback,String ipAddress,int port) {
		Gson gson=new Gson();  
		
        return callback+"("+gson.toJson(this.configservice.show_config(ipAddress, port))+")"; 
	}
}
