package com.tomcat.status.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.tomcat.status.dao.memory_poolMapper;
import com.tomcat.status.model.memory_pool;
import com.tomcat.status.service.MemoryPoolService;

@Service("MemoryPoolService")
public class MemoryPoolServiceImpl implements MemoryPoolService{
	@Resource
	private memory_poolMapper memory_poolmapper;
	@Override
	public List<memory_pool> selectAllMemory_Pool(String ipaddress,int port) {
		// TODO Auto-generated method stub
		return this.memory_poolmapper.selectAllMemory_Pool(ipaddress, port);
	}
	@Override
	public memory_pool readJson(HttpServletRequest request) throws IOException {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		StringBuffer jb = new StringBuffer();
          String line = null;
          memory_pool mp = null;
          try {
              //读取输入流到StringBuffer中
              BufferedReader reader = request.getReader();
                while ((line = reader.readLine()) != null)
                  jb.append(line);
                 
          } catch (Exception e) { /*report an error*/ }

          try {
              //使用JSONObject的parseObject方法解析JSON字符串
              
              mp=gson.fromJson(jb.toString(), memory_pool.class);
                 
          } catch (Exception e) {
            // crash and burn
            throw new IOException("Error parsing JSON request string");
          }
          //System.out.print(config.getIpaddress());
		//System.out.println(gson.fromJson(config, config.getClass()));
		return mp;	
	}
	@Override
	public String insert(memory_pool memory_pool) {
		// TODO Auto-generated method stub
		int flag=this.memory_poolmapper.insert(memory_pool);
		if(flag==1)
			return "success";
		return "插入失败";
	}

}
