package com.tomcat.status.post;


import com.tomcat.status.model.memory_pool;

public class Memory_Pool_Methods {
	public String write(String target_ip,int target_port,memory_pool memory_pool) {
		String pathUrl = "http://"+target_ip+":"+target_port+"/Tomcat_Control/memorypool/write";		
		return HttpRequestPost.sendPost_Object(pathUrl,memory_pool);
	}
}
