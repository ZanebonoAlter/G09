package com.tomcat.status.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.tomcat.status.model.config;
import com.tomcat.status.model.configKey;
import com.tomcat.status.model.memory_pool;
import com.tomcat.status.service.ConfigService;
import com.tomcat.status.service.LogService;
import com.tomcat.status.service.MemoryPoolService;

@RestController
@RequestMapping("/memorypool")
public class MemoryPoolController {
	@Resource
	private MemoryPoolService memorypoolservice;
	@Resource
	private ConfigService configservice;
	@Resource
	private LogService logservice;
	
	@RequestMapping(value="/load_memory_pool",produces="text/html;charset=UTF-8")
	public String load_memory_pool(@RequestParam("callback") String callback,HttpServletRequest request,HttpServletResponse resp,String ipAddress,int port) {
		List<memory_pool> ls = memorypoolservice.selectAllMemory_Pool(ipAddress, port);
		Gson gson =new Gson();
		return callback+"("+gson.toJson(ls)+")";
	}
	
	@RequestMapping("/write")
	public String write(HttpServletRequest request) throws IOException {
		memory_pool memory_pool = this.memorypoolservice.readJson(request);
		configKey configKey = new configKey();
		configKey.setIpaddress(memory_pool.getIpaddress());
		configKey.setPort(memory_pool.getPort());
		config config = this.configservice.getConfigByKey(configKey);
		this.logservice.Log_Memory_Pool(memory_pool, config);
		return this.memorypoolservice.insert(memory_pool);
	}
}
