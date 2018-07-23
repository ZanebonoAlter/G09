package com.tomcat.status.post;

import com.tomcat.status.model.memory;

public class Memory_Methods {
	public String write(String target_ip,int target_port,memory memory) {
		String pathUrl = "http://"+target_ip+":"+target_port+"/Tomcat_Control/memory/write";		
		return HttpRequestPost.sendPost_Object(pathUrl,memory);
	}

}
