package com.tomcat.status.service;

import com.tomcat.status.model.config;
import com.tomcat.status.model.configKey;

public interface ConfigService {
	public config getConfigByKey(configKey key);
}
