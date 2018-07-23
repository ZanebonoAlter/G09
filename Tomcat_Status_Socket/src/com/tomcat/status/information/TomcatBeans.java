package com.tomcat.status.information;
 
import java.lang.management.ManagementFactory;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Set;
 
import javax.management.JMException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.Query;
 
public class TomcatBeans {
 
	private final MBeanServer mbeanServer;
 
	public TomcatBeans() {
		this(getPlatformMBeanServer());
	}
 
	private TomcatBeans(MBeanServer mbeanServer) {
		super();
		this.mbeanServer = mbeanServer;
	}
	/**
	 * 获取MBeanServer
	 * @return
	 */
	static MBeanServer getPlatformMBeanServer() {
		return ManagementFactory.getPlatformMBeanServer();
	}
	/**
	 * 获取tomcat的线程池
	 * @return
	 * @throws MalformedObjectNameException
	 */
	Set<ObjectName> getTomcatThreadPools() throws MalformedObjectNameException {
		return mbeanServer.queryNames(new ObjectName("*:type=ThreadPool,*"), null);
	}
 
	Set<ObjectName> getTomcatGlobalRequestProcessors() throws MalformedObjectNameException {
		return mbeanServer.queryNames(new ObjectName("*:type=GlobalRequestProcessor,*"), null);
	}
 
	Object getAttribute(ObjectName name, String attribute) throws JMException {
		return mbeanServer.getAttribute(name, attribute);
	}
	public String getIpAddress() throws MalformedObjectNameException, NullPointerException,
    UnknownHostException {
	
        	InetAddress ip = null;
            return ip.getLocalHost().getHostAddress();
   
	}
	public String getPort() throws MalformedObjectNameException, NullPointerException,
    UnknownHostException {
		Set<ObjectName> objectNames = mbeanServer.queryNames(new ObjectName("*:type=Connector,*"),
        Query.match(Query.attr("protocol"), Query.value("HTTP/1.1")));
		String port = objectNames.iterator().next().getKeyProperty("port");
		return port;
	}
}