package com.tomcat.status.post;

public class Connecting_Methods {
	public String release(String target_ip,int target_port,String ipAddress,int port) {
		String pathUrl = "http://"+target_ip+":"+target_port+"/Tomcat_Control/connecting/release_connecting?ipAddress="+ipAddress+"&port="+port;		
		return HttpRequestPost.sendPost(pathUrl);
	}
	public String update(String target_ip,int target_port,String ipAddress,int port) {
		String pathUrl = "http://"+target_ip+":"+target_port+"/Tomcat_Control/connecting/update_connecting?ipAddress="+ipAddress+"&port="+port;		
		return HttpRequestPost.sendPost(pathUrl);
	}
}
