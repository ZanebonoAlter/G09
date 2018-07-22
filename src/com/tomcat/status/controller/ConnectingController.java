package com.tomcat.status.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.tomcat.status.model.connecting;
import com.tomcat.status.service.ConnectingService;
import com.tomcat.status.service.LogService;

@RestController
@RequestMapping("/connecting")
public class ConnectingController {
	@Resource
	private ConnectingService connectingservice;
	@Resource
	private LogService logservice;
	
	@RequestMapping(value="/load_all_connecting",produces="text/html;charset=UTF-8")
	public String load_all_connecting(@RequestParam("callback") String callback,HttpServletRequest request,HttpServletResponse resp) {
		Gson gson=new Gson();  
		List<connecting> ls = connectingservice.selectAllConnecting();
        return callback+"("+gson.toJson(ls)+")";  
	}
	@RequestMapping(value="/load_connecting_log",produces="text/html;charset=UTF-8")
	public String load_connecting_log(@RequestParam("callback") String callback) {
		Gson gson=new Gson();
		List<connecting> ls = connectingservice.selectAllConnecting();
		List<Integer> result = new ArrayList<>();
		for(connecting conn : ls) {
			result.add(this.logservice.selectCountLog(conn));
		}
        return callback+"("+gson.toJson(result)+")";  
	}
	@RequestMapping("/release_connecting")
	public String release_connecting(String ipAddress,int port) {
		return this.connectingservice.releaseConnecting(ipAddress, port);
	}
	@RequestMapping("/update_connecting")
	public String update_connecting(String ipAddress,int port) {
		return this.connectingservice.updateConnecting(ipAddress, port);
	}
}
