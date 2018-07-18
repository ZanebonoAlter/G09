package com.tomcat.status.post;

import com.tomcat.status.model.connector;

public class Connector_Methods {
	public String write(String target_ip,int target_port,connector connector) {
		String pathUrl = "http://"+target_ip+":"+target_port+"/Tomcat_Control/connector/write";		
		return HttpRequestPost.sendPost_Object(pathUrl,connector);
	}
}
