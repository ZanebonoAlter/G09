package com.tomcat.status.service.impl;

import java.io.BufferedReader;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.tomcat.status.dao.memoryMapper;
import com.tomcat.status.model.memory;
import com.tomcat.status.service.MemoryService;

@Service("MemoryService")
public class MemoryServiceImpl implements MemoryService{
	@Resource
	private memoryMapper memorymapper;
	@Override
	public memory selectTop(String ipAddress, int port) {
		// TODO Auto-generated method stub
		return this.memorymapper.selectTop(ipAddress, port);
	}
	@Override
	public memory readJson(HttpServletRequest request) throws IOException {
		Gson gson = new Gson();
		StringBuffer jb = new StringBuffer();
          String line = null;
          memory m = null;
          try {
              //读取输入流到StringBuffer中
              BufferedReader reader = request.getReader();
                while ((line = reader.readLine()) != null)
                  jb.append(line);
                 
          } catch (Exception e) { /*report an error*/ }

          try {
              //使用JSONObject的parseObject方法解析JSON字符串
              
              m=gson.fromJson(jb.toString(), memory.class);
                 
          } catch (Exception e) {
            // crash and burn
            throw new IOException("Error parsing JSON request string");
          }
		return m;	
	}
	@Override
	public String insert(memory memory) {
		// TODO Auto-generated method stub
		int flag = this.memorymapper.insert(memory);
		if(flag==1)
			return "success";
		return "插入失败";
	}
	
}
