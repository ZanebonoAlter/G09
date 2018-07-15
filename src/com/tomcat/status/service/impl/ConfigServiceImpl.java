package com.tomcat.status.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

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

}
