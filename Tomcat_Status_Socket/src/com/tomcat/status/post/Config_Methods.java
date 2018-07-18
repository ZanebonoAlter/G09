package com.tomcat.status.post;

import com.tomcat.status.model.config;

public class Config_Methods {
	public String Judge_Config(String target_ip,int target_port,String ipAddress,int port) {
		String pathUrl = "http://"+target_ip+":"+target_port+"/Tomcat_Control/config/Judge_Config?ipAddress="
				+ipAddress+"&port="+port;		
		return HttpRequestPost.sendPost(pathUrl);
	}
	public String add_Config(String target_ip,int target_port,config config) {
		String pathUrl = "http://"+target_ip+":"+target_port+"/Tomcat_Control/config/Add_Config";		
		return HttpRequestPost.sendPost_Object(pathUrl, config);
	}
}
