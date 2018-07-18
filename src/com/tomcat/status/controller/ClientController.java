package com.tomcat.status.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.tomcat.status.model.config;



@Controller
@RequestMapping("/client")
public class ClientController {
	@RequestMapping("/test")
	@ResponseBody
	public String doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
				System.out.println("doPost");
				Gson gson = new Gson();
				StringBuffer jb = new StringBuffer();
	              String line = null;
	              String result = "";
	              config config=null;
	              try {
	                  //读取输入流到StringBuffer中
	                  BufferedReader reader = request.getReader();
	                    while ((line = reader.readLine()) != null)
	                      jb.append(line);
	                     
	              } catch (Exception e) { /*report an error*/ }
	 
	              try {
	                  //使用JSONObject的parseObject方法解析JSON字符串
	                  
	                  config=gson.fromJson(jb.toString(), config.class);
	                     
	              } catch (Exception e) {
	                // crash and burn
	                throw new IOException("Error parsing JSON request string");
	              }
	              //先将服务器收到的JSON字符串打印到客户端，再将该字符串转换为JSON对象然后再转换成的JSON字符串打印到客户端
	              PrintStream out = new PrintStream(response.getOutputStream());
	              out.println(jb.toString());
	              out.println(result);
	              System.out.print(config.getIpaddress());
				//System.out.println(gson.fromJson(config, config.getClass()));
				return "ok";
		}
	
    
}
