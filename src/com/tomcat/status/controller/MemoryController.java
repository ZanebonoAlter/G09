package com.tomcat.status.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.tomcat.status.model.memory;
import com.tomcat.status.service.MemoryService;

@RestController
@RequestMapping("/memory")
public class MemoryController {
	@Resource
	private MemoryService memoryservice;
	
	@RequestMapping(value="/show_top_memory",produces="text/html;charset=UTF-8")
	public String load_all_memory_pool(@RequestParam("callback") String callback,HttpServletRequest request,HttpServletResponse resp,String ipAddress,int port) {
		Gson gson=new Gson();  
		memory mem = memoryservice.selectTop(ipAddress, port);
		return callback+"("+gson.toJson(mem)+")";
	}
}
