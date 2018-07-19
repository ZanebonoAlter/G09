package com.tomcat.status.deal;

import java.io.IOException;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.management.MalformedObjectNameException;

import com.tomcat.status.information.TomcatBeans;
import com.tomcat.status.model.config;
import com.tomcat.status.model.connecting;
import com.tomcat.status.model.connector;
import com.tomcat.status.model.memory;
import com.tomcat.status.model.memory_pool;
import com.tomcat.status.post.Config_Methods;
import com.tomcat.status.post.Connecting_Methods;
import com.tomcat.status.post.Connector_Methods;
import com.tomcat.status.post.Memory_Methods;
import com.tomcat.status.post.Memory_Pool_Methods;
import com.tomcat.status.information.*;


public class TomcatWrite {
	private static final String CONFIGNAME = "/config.properties";
	private String target_ip;
	private int target_port;
	private static Properties prop;
	public TomcatWrite() {
		//String property = System.getProperty("user.dir");
        try {
        	prop = new Properties();
        	 //InputStream in = new BufferedInputStream(new FileInputStream(property+CONFIGNAME));
             //prop.load(in);
        	InputStream fis =TomcatWrite.class.getClassLoader().getResourceAsStream(CONFIGNAME);   
        	prop.load(fis);
        	this.target_ip=prop.getProperty("target_ip");
        	this.target_port=Integer.parseInt(prop.getProperty("target_port"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
	public synchronized void use_or_add_config() {
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
			config config =new config(); 
			config.setIpaddress(ipAddress);
			config.setPort(port);
			config.setMemoryTotal(0.8);
			config.setConnectorCurrentthreadscount(0.8);
			config.setConnectorCurrentthreadsbusy(0.5);
			config.setMemoryPoolCodeCacheUsed(0.8);
			config.setMemoryPoolCompressedClassSpaceUsed(0.8);
			config.setMemoryPoolMetaspaceUsed(0.8);
			config.setMemoryPoolPsEdenSpaceUsed(0.9);
			config.setMemoryPoolPsOldGenUsed(0.8);
			config.setMemoryPoolPsSurvivorSpaceUsed(1.0);
			String result = new Config_Methods().Judge_Config(target_ip, target_port, ipAddress, port);
			if(result.equals("exists")) {
				System.out.println("存在记录");
			}else {
				new Config_Methods().add_Config(target_ip, target_port, config);
			}
	}
	public synchronized void release() {
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
		new Connecting_Methods().release(target_ip, target_port, ipAddress, port);
	}
	public synchronized void connect() {
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
		connecting conn = new connecting();
		conn.setIpaddress(ipAddress);
		conn.setPort(port);
		new Connecting_Methods().update(target_ip, target_port, ipAddress, port);
		
	}

	public synchronized void write() {
		connector connector = new connector();
		String ipAddress=null;
		Calendar now = Calendar.getInstance();
		Date time = now.getTime();
		int port = -1;
		TomcatBeans tb = new TomcatBeans();
		Connector_Methods cm = new Connector_Methods();
		Memory_Methods mm = new Memory_Methods();
		Memory_Pool_Methods memory_pool_methods = new Memory_Pool_Methods();
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
				connector.setMaxthreads(info.getMaxThreads());
				connector.setCurrentthreadcount(info.getCurrentThreadCount());
				connector.setCurrentthreadsbusy(info.getCurrentThreadsBusy());
				connector.setMaxtime(info.getMaxTime());
				connector.setProcessingtime(info.getProcessingTime());
				connector.setRequestcount(info.getRequestCount());
				connector.setErrorcount(info.getErrorCount());
				connector.setBytesreceived(info.getBytesReceived());
				connector.setBytessent(info.getBytesSent());
				connector.setPort(info.getPort());
				connector.setIpaddress(info.getIpAddress());
				connector.setTime(time);
				cm.write(target_ip, target_port,connector);
//				String result=cm.log(target_ip, target_port,connector);
//				if(!result.equals("healthy")) {//有描述信息
//					log log =new log();
//					log.setIpaddress(ipAddress);
//					log.setPort(port);
//					log.setReadStatus("not read");
//					log.setSendStatus("not send");
//					log.setDescribeMessage(result);
//					log.setErrorType("Connector");
//					log.setTime(time);
//					cm.write_log(target_ip, target_port, log);
//				}
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
		
		memory m = new memory();
		double free = Runtime.getRuntime().freeMemory();
		double total = Runtime.getRuntime().totalMemory();
		double max = Runtime.getRuntime().maxMemory();
		m.setFree(free/1024/1024);
		m.setTotal(total/1024/1024);
		m.setMax(max/1024/1024);
		m.setIpaddress(ipAddress);
		m.setPort(port);
		m.setTime(time);
		mm.write(target_ip, target_port, m);
//		String result=mm.log(target_ip, target_port,m);
//		if(!result.equals("healthy")) {//有描述信息
//			log log =new log();
//			log.setIpaddress(ipAddress);
//			log.setPort(port);
//			log.setReadStatus("not read");
//			log.setSendStatus("not send");
//			log.setDescribeMessage(result);
//			log.setErrorType("Memory");
//			log.setTime(time);
//			mm.write_log(target_ip, target_port, log);
//		}
//		System.out.println("JVM");
//		System.out.println("free:"+free/1024/1024+"MB");
//		System.out.println("total:"+total/1024/1024+"MB");
//		System.out.println("max:"+max/1024/1024+"MB");
		List<MemoryPoolMXBean> mpmList=ManagementFactory.getMemoryPoolMXBeans();
		memory_pool mp = new memory_pool();
		
	    for(MemoryPoolMXBean mpm:mpmList){
		    //System.out.println("getUsage "+mpm.getName());
		    //System.out.println("getMemoryManagerNames "+mpm.getMemoryManagerNames().toString());
		    //System.out.println("Type:"+mpm.getType().toString());
		    //System.out.println(mpm.getUsage());
		   
		    mp.setName(mpm.getName());
		    mp.setType(mpm.getType().toString());
		    mp.setInitial((double)mpm.getUsage().getInit());
		    mp.setCommitted((double)mpm.getUsage().getCommitted());
		    mp.setMaximum((double)mpm.getUsage().getMax());
		    mp.setUsed((double)mpm.getUsage().getUsed());
		    mp.setPort(port);
		    mp.setIpaddress(ipAddress);
		    mp.setTime(time);
		    memory_pool_methods.write(target_ip, target_port, mp);
	    }

	}
	public boolean isHostConnectable() {
		String host = target_ip;
		int port = target_port;
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(host, port));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
