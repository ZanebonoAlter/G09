package com.version2.deal;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.List;

import cn.zucc.edu.cn.dao.TomcatStatusDao;
import cn.zucc.edu.model.Connector;
import cn.zucc.edu.model.Memory;
import cn.zucc.edu.model.Memory_Pool;

public class TomcatWrite {
	
	public synchronized void write() {
		StringBuffer sb = new StringBuffer();
		Connector connector = new Connector();
		TomcatStatusDao ts = new TomcatStatusDao();
		String ipAddress=null;
		int port = -1;
		try {
			for(TomcatInformations info : TomcatInformations.buildTomcatInformationsList()){
				connector.setName(info.getName());
				connector.setMaxThreads(info.getMaxThreads());
				connector.setCurrentThreadCount(info.getCurrentThreadCount());
				connector.setCurrentThreadsBusy(info.getCurrentThreadsBusy());
				connector.setMaxTime(info.getMaxTime());
				connector.setProcessingTime(info.getProcessingTime());
				connector.setRequestCount(info.getRequestCount());
				connector.setErrorCount(info.getErrorCount());
				connector.setBytesReceived(info.getBytesReceived());
				connector.setBytesSent(info.getBytesSent());
				connector.setPort(info.getPort());
				connector.setIpAddress(info.getIpAddress());
				port=info.getPort();
				ipAddress=info.getIpAddress();
				try {
					ts.add_connector(connector);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        		
		//System.out.println(sb.toString());
		
		Memory m = new Memory();
		double free = Runtime.getRuntime().freeMemory();
		double total = Runtime.getRuntime().totalMemory();
		double max = Runtime.getRuntime().maxMemory();
		m.setFree(free/1024/1024);
		m.setTotal(total/1024/1024);
		m.setMax(max/1024/1024);
		m.setIpAddress(ipAddress);
		m.setPort(port);
		ts.add_memory(m);
		
//		System.out.println("JVM");
//		System.out.println("free:"+free/1024/1024+"MB");
//		System.out.println("total:"+total/1024/1024+"MB");
//		System.out.println("max:"+max/1024/1024+"MB");
		List<MemoryPoolMXBean> mpmList=ManagementFactory.getMemoryPoolMXBeans();
		Memory_Pool mp = new Memory_Pool();
	    for(MemoryPoolMXBean mpm:mpmList){
		    //System.out.println("getUsage "+mpm.getName());
		    //System.out.println("getMemoryManagerNames "+mpm.getMemoryManagerNames().toString());
		    //System.out.println("Type:"+mpm.getType().toString());
		    //System.out.println(mpm.getUsage());
		   
		    mp.setName(mpm.getName());
		    mp.setType(mpm.getType().toString());
		    mp.setInitial(mpm.getUsage().getInit());
		    mp.setCommitted(mpm.getUsage().getCommitted());
		    mp.setMaximum(mpm.getUsage().getMax());
		    mp.setUsed(mpm.getUsage().getUsed());
		    mp.setPort(port);
		    mp.setIpAddress(ipAddress);
		    
		    ts.add_memory_pool(mp);
	    }
	    ts.release();
	}
}
