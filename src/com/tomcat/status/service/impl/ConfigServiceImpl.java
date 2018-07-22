package com.tomcat.status.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.tomcat.status.dao.configMapper;
import com.tomcat.status.model.config;
import com.tomcat.status.model.configKey;
import com.tomcat.status.service.ConfigService;

@Service("ConfigService")
public class ConfigServiceImpl implements ConfigService{
	@Resource
	private configMapper configMapper;
	@Override
	public config getConfigByKey(configKey key) {
		// TODO Auto-generated method stub
		return this.configMapper.selectByPrimaryKey(key);
	}
	@Override
	public config readJson(HttpServletRequest request) throws IOException {
		// TODO Auto-generated method stub
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
          //System.out.print(config.getIpaddress());
		//System.out.println(gson.fromJson(config, config.getClass()));
		return config;
	}
	@Override
	public String select_one(String ipAddress,int port) {
		// TODO Auto-generated method stub
		configKey configKey = new configKey();
		configKey.setIpaddress(ipAddress);
		configKey.setPort(port);
		if(this.configMapper.select_one(configKey)==1)
			return "exists";
		return "not_exists";
	}
	@Override
	public int insert(config config) {
		// TODO Auto-generated method stub
		return this.configMapper.insert(config);
	}
	@Override
	public String update_config(config config) {
		if(this.configMapper.updateByPrimaryKey(config)==1)
			return "success";
		return "数据库更新失败";
	}
	@Override
	public config show_config(String ipAddress, int port) {
		// TODO Auto-generated method stub
		configKey key = new configKey();
		key.setIpaddress(ipAddress);
		key.setPort(port);
		return this.configMapper.selectByPrimaryKey(key);
	}

	

}
