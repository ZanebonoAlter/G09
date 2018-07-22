package com.tomcat.status.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tomcat.status.dao.logMapper;
import com.tomcat.status.model.config;
import com.tomcat.status.model.connecting;
import com.tomcat.status.model.connector;
import com.tomcat.status.model.log;
import com.tomcat.status.model.memory;
import com.tomcat.status.model.memory_pool;
import com.tomcat.status.service.LogService;

@Service("LogService")
public class LogServiceImpl implements LogService{
	@Resource
	private logMapper logmapper;
	
	@Override
	public void Log_Connector(connector connector, config config) {
		log log = new log();
		double result;
		String ipaddress=connector.getIpaddress();
		int port = connector.getPort();
		Date time = connector.getTime();
		
		result = (connector.getCurrentthreadcount()*1.0)/connector.getMaxthreads();
		if(result>config.getConnectorCurrentthreadscount()) {//当前线程数量超标
			log.setIpaddress(ipaddress);
			log.setPort(port);
			log.setTime(time);
			log.setErrorType("线程池:"+connector.getName());
			log.setReadStatus("not read");
			log.setSendStatus("not send");
			log.setDescribeMessage("线程数量警告:currentThreadsCount:"+result*100+"%"+">"+config.getConnectorCurrentthreadscount()*100+"%");
			this.logmapper.insert(log);
		}
		result = (connector.getCurrentthreadsbusy()*1.0)/connector.getMaxthreads();
		if(result>config.getConnectorCurrentthreadsbusy()) {
			log.setIpaddress(ipaddress);
			log.setPort(port);
			log.setTime(time);
			log.setErrorType("线程池:"+connector.getName());
			log.setReadStatus("not read");
			log.setSendStatus("not send");
			log.setDescribeMessage("繁忙线程数量警告:currentThreadsBusy:"+result*100+"%"+">"+config.getConnectorCurrentthreadsbusy()*100+"%");
			this.logmapper.insert(log);
		}
	}

	@Override
	public void Log_Memory(memory memory, config config) {
		log log =new log();
		double result;
		String ipaddress=memory.getIpaddress();
		int port = memory.getPort();
		Date time = memory.getTime();
		result=memory.getTotal()/memory.getMax();
		if(result>config.getMemoryTotal()) {
			log.setIpaddress(ipaddress);
			log.setPort(port);
			log.setTime(time);
			log.setErrorType("总内存");
			log.setReadStatus("not read");
			log.setSendStatus("not send");
			log.setDescribeMessage("总内存占比警告:currentThreadsBusy:"+result*100+"%"+">"+config.getMemoryTotal()*100+"%");
			this.logmapper.insert(log);
		}
	}

	@Override
	public void Log_Memory_Pool(memory_pool memory_pool, config config) {
		log log =new log();
		double result;
		String ipaddress=memory_pool.getIpaddress();
		int port = memory_pool.getPort();
		Date time = memory_pool.getTime();
		double memory_pool_Compressed_Class_Space_used=config.getMemoryPoolCompressedClassSpaceUsed();
		double memory_pool_Code_Cache_used=config.getMemoryPoolCodeCacheUsed();
		double memory_pool_Metaspace_used =config.getMemoryPoolMetaspaceUsed();
		double memory_pool_PS_Eden_Space_used = config.getMemoryPoolPsEdenSpaceUsed();
		double memory_pool_PS_Old_Gen_used = config.getMemoryPoolPsOldGenUsed();
		double memory_pool_PS_Survivor_Space_used = config.getMemoryPoolPsSurvivorSpaceUsed();
		double memory_pool_switch=1;
    	if(memory_pool.getName().equals("Code Cache")) {
    		memory_pool_switch=memory_pool_Code_Cache_used;
    	}
    	if(memory_pool.getName().equals("Compressed Class Space")) {
    		memory_pool_switch=memory_pool_Compressed_Class_Space_used;
    	}
    	if(memory_pool.getName().equals("Metaspace")) {
    		memory_pool_switch=memory_pool_Metaspace_used;
    	}
    	if(memory_pool.getName().equals("PS Eden Space")) {
    		memory_pool_switch=memory_pool_PS_Eden_Space_used;
    	}
    	if(memory_pool.getName().equals("PS Old Gen")) {
    		memory_pool_switch=memory_pool_PS_Old_Gen_used;
    	}
    	if(memory_pool.getName().equals("PS Survivor Space")) {
    		memory_pool_switch=memory_pool_PS_Survivor_Space_used;
    	}
		result = memory_pool.getUsed()/memory_pool.getMaximum();
		if(result>memory_pool_switch) {
			log.setIpaddress(ipaddress);
			log.setPort(port);
			log.setTime(time);
			log.setErrorType("内存池:"+memory_pool.getName());
			log.setReadStatus("not read");
			log.setSendStatus("not send");
			log.setDescribeMessage("内存池占比警告:"+memory_pool.getName()+":"+result*100+"%"+">"+memory_pool_switch*100+"%");
			this.logmapper.insert(log);
		}
	}

	@Override
	public List<log> show_not_read(String ipAddress, int port) {
		// TODO Auto-generated method stub
		return this.logmapper.selectUnreadLog(ipAddress, port);
	}

	@Override
	public int selectUnSendLog() {
		// TODO Auto-generated method stub
		return this.logmapper.selectUnSendLog();
	}

	@Override
	public int updateSendLog() {
		// TODO Auto-generated method stub
		return this.logmapper.updateSendLog();
	}

	@Override
	public String clearLog() {
		// TODO Auto-generated method stub
			return "已阅"+this.logmapper.clearUnreadLog()+"条消息";		
	}

	@Override
	public String clear_local_log(String ipAddress, int port) {
		// TODO Auto-generated method stub
		return "已阅"+this.logmapper.clearLocalLog(ipAddress, port);
	}

	@Override
	public int selectCountLog(connecting conn) {
		// TODO Auto-generated method stub
		String result = ""+this.logmapper.selectCountUnreadLog(conn.getIpaddress(), conn.getPort());
		if("".equals(result))
			return 0;
		else
		return this.logmapper.selectCountUnreadLog(conn.getIpaddress(), conn.getPort());
	}

	@Override
	public void addWarnConnecting(connecting connecting) {
		// TODO Auto-generated method stub
		log log = new log();
		log.setIpaddress(connecting.getIpaddress());
		log.setPort(connecting.getPort());
		log.setReadStatus("not read");
		log.setSendStatus("not send");
		log.setErrorType("Tomcat连接");
		log.setTime(connecting.getLastTime());
		log.setDescribeMessage(connecting.getIpaddress()+":"+connecting.getPort()+"在"+connecting.getLastTime()+"异常断开连接");
		this.logmapper.insert(log);
	}

	
	
}
