package com.tomcat.status.controller;


import java.util.List;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.tomcat.status.model.connecting;
import com.tomcat.status.service.ConnectingService;

@RestController
@RequestMapping("/connecting")
public class ConnectingController {
	@Resource
	private ConnectingService connectingservice;
	
	@RequestMapping(value="/load_all_connecting",produces="text/html;charset=UTF-8")
	public String load_all_connecting(@RequestParam("callback") String callback,HttpServletRequest request,HttpServletResponse resp) {
		Gson gson=new Gson();  
		List<connecting> ls = connectingservice.selectAllConnecting();
        return callback+"("+gson.toJson(ls)+")";  
	}
}
