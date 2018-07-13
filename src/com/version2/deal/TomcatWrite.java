package com.version2.deal;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.List;

import javax.management.MalformedObjectNameException;

import cn.zucc.edu.cn.dao.TomcatStatusDao;
import cn.zucc.edu.model.Config;
import cn.zucc.edu.model.Connecting;
import cn.zucc.edu.model.Connector;
import cn.zucc.edu.model.Memory;
import cn.zucc.edu.model.Memory_Pool;

public class TomcatWrite {
	public synchronized void use_or_add_config() {
		TomcatStatusDao ts = new TomcatStatusDao();
		String ipAddress=null;
		int port = -1;
		TomcatBeans tb = new TomcatBeans();
		try {
			ipAddress = tb.getIpAddress();
			port = Integer.parseInt(tb.getPort());
		} catch (MalformedObjectNameException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NullPointerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Config config =new Config(); 
		int status = ts.Judge_config(ipAddress, port);
		if(status==1) {//有记录
			
		}else if(status==-1) {//无记录
			config.setIpAddress(ipAddress);
			config.setPort(port);
			config.setMemory_total(0.8);
			config.setConnector_currentThreadsCount(0.8);
			config.setConnector_currentThreadsBusy(0.5);
			config.setMemory_pool_Code_Cache_used(0.8);
			config.setMemory_pool_Compressed_Class_Space_used(0.8);
			config.setMemory_pool_Metaspace_used(0.8);
			config.setMemory_pool_PS_Eden_Space_used(0.9);
			config.setMemory_pool_PS_Old_Gen_used(0.8);
			config.setMemory_pool_PS_Survivor_Space_used(1);
			ts.add_config(config);
		}
		ts.release();
	}
	public synchronized void release() {
		TomcatStatusDao ts = new TomcatStatusDao();
		String ipAddress=null;
		int port = -1;
		TomcatBeans tb = new TomcatBeans();
		try {
			ipAddress = tb.getIpAddress();
			port = Integer.parseInt(tb.getPort());
		} catch (MalformedObjectNameException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NullPointerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Connecting conn = new Connecting();
		conn.setIpAddress(ipAddress);
		conn.setPort(port);
		conn.setStatus("断开连接");
		int status=ts.Judge_connecting(ipAddress, port);
		if(status==-1) {//没有记录
			System.out.println("未被记录的连接断开");
		}else if(status==1) {//有记录
			ts.update_connecting(conn);
		}
		ts.release();
	}
	public synchronized void connect() {
		TomcatStatusDao ts = new TomcatStatusDao();
		String ipAddress=null;
		int port = -1;
		TomcatBeans tb = new TomcatBeans();
		try {
			ipAddress = tb.getIpAddress();
			port = Integer.parseInt(tb.getPort());
		} catch (MalformedObjectNameException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NullPointerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Connecting conn = new Connecting();
		conn.setIpAddress(ipAddress);
		conn.setPort(port);
		conn.setStatus("已连接");
		int status=ts.Judge_connecting(ipAddress, port);
		if(status==-1) {//没有记录
			conn.setName(ipAddress);
			ts.add_connecting(conn);
		}else if(status==1) {//有记录
			ts.update_connecting(conn);
		}
		ts.release();
	}

	public synchronized void write() {
		StringBuffer sb = new StringBuffer();
		Connector connector = new Connector();
		TomcatStatusDao ts = new TomcatStatusDao();
		String ipAddress=null;
		int port = -1;
		TomcatBeans tb = new TomcatBeans();
		try {
			ipAddress = tb.getIpAddress();
			port = Integer.parseInt(tb.getPort());
		} catch (MalformedObjectNameException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NullPointerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
				try {
					ts.add_connector(connector);
					ts.Judge_connector(connector);
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
		ts.Judge_memory(m);
		
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
		    ts.Judge_memory_pool(mp);
	    }
	    ts.release();
	}
}
