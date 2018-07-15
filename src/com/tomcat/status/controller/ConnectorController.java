package com.tomcat.status.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.tomcat.status.model.connector;
import com.tomcat.status.service.ConnectorService;

@RestController
@RequestMapping("/connector")
public class ConnectorController {
	@Resource
	private ConnectorService connectorservice;
	
	@RequestMapping(value="/show_top_two",produces="text/html;charset=UTF-8")
	public String show_top_two(@RequestParam("callback") String callback,HttpServletRequest request,HttpServletResponse resp,ModelMap model,String ipAddress,int port) {
		List<connector> ls = connectorservice.selectTopTwo(ipAddress,port);
		Gson gson=new Gson();  
		return callback+"("+gson.toJson(ls)+")";   		
	}

}
