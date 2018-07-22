package com.tomcat.status.service;

import java.util.List;

import com.tomcat.status.model.config;
import com.tomcat.status.model.connecting;
import com.tomcat.status.model.connector;
import com.tomcat.status.model.log;
import com.tomcat.status.model.memory;
import com.tomcat.status.model.memory_pool;

public interface LogService {
	public void Log_Connector(connector connector,config config);
	public void Log_Memory(memory memory,config config);
	public void Log_Memory_Pool(memory_pool memory_pool,config config);
	public List<log> show_not_read(String ipAddress,int port);
	public int selectUnSendLog();
	public int updateSendLog();
	public String clearLog();
	public String clear_local_log(String ipAddress,int port);
	public int selectCountLog(connecting connecting);
	public void addWarnConnecting(connecting connecting);
}
