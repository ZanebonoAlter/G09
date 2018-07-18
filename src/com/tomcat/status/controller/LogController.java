package com.tomcat.status.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.tomcat.status.model.log;
import com.tomcat.status.service.LogService;

@RestController
@RequestMapping("/log")
public class LogController {
	@Resource
	private LogService logservice;
	@RequestMapping("/show_unread_log")
	public String show_unread_log(@RequestParam("callback") String callback,HttpServletRequest request,HttpServletResponse resp,String ipAddress,int port) {
		Gson gson=new Gson();  
		List<log> ls = logservice.show_not_read(ipAddress, port);
        return callback+"("+gson.toJson(ls)+")";  
	}
}
