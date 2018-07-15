package com.tomcat.status.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.tomcat.status.model.memory_pool;
import com.tomcat.status.service.MemoryPoolService;

@RestController
@RequestMapping("/memorypool")
public class MemoryPoolController {
	@Resource
	private MemoryPoolService memorypoolservice;
	
	@RequestMapping(value="/load_memory_pool",produces="text/html;charset=UTF-8")
	public String load_memory_pool(@RequestParam("callback") String callback,HttpServletRequest request,HttpServletResponse resp,String ipAddress,int port) {
		List<memory_pool> ls = memorypoolservice.selectAllMemory_Pool(ipAddress, port);
		Gson gson =new Gson();
		return callback+"("+gson.toJson(ls)+")";
	}
}
