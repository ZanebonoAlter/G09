package com.tomcat.status.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import com.tomcat.status.model.config;
import com.tomcat.status.model.configKey;

public interface ConfigService {
	public config getConfigByKey(configKey key);
	public config readJson(HttpServletRequest request) throws IOException;
	public String select_one(String ipAddress,int port);
	public int insert(config config);
	public String update_config(config config);
	public config show_config(String ipAddress,int port);
}
