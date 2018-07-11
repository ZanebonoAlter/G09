package com.version2.deal;
 
import java.io.Serializable;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
 
import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.JMException;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
 
final class TomcatInformations implements Serializable {
 
	private static final boolean TOMCAT_USED = System.getProperty("catalina.home") != null;
 
	private static final long serialVersionUID = -6145865427461051370L;
 
	@SuppressWarnings("all")
	private static final List<ObjectName> THREAD_POOLS = new ArrayList<ObjectName>();
	@SuppressWarnings("all")
	private static final List<ObjectName> GLOBAL_REQUEST_PROCESSORS = new ArrayList<ObjectName>();
 
	private final String name;
	private final int maxThreads;
	private final int currentThreadCount;
	private final int currentThreadsBusy;
	private final long bytesReceived;
	private final long bytesSent;
	private final int requestCount;
	private final int errorCount;
	private final long processingTime;
	private final long maxTime;
	private final int port;
	private final String ipAddress;
	
	private TomcatInformations(TomcatBeans mBeans, ObjectName threadPool) throws JMException, NumberFormatException, NullPointerException, UnknownHostException {
		super();
		port = Integer.parseInt(mBeans.getPort());
		ipAddress = mBeans.getIpAddress();
		name = threadPool.getKeyProperty("name");
		maxThreads = (Integer) mBeans.getAttribute(threadPool, "maxThreads");
		currentThreadCount = (Integer) mBeans.getAttribute(threadPool, "currentThreadCount");
		currentThreadsBusy = (Integer) mBeans.getAttribute(threadPool, "currentThreadsBusy");
		ObjectName grp = null;
		for (final ObjectName globalRequestProcessor : GLOBAL_REQUEST_PROCESSORS) {
			if (name.equals(globalRequestProcessor.getKeyProperty("name"))) {
				grp = globalRequestProcessor;
				break;
			}
		}
		if (grp != null) {
			bytesReceived = (Long) mBeans.getAttribute(grp, "bytesReceived");
			bytesSent = (Long) mBeans.getAttribute(grp, "bytesSent");
			requestCount = (Integer) mBeans.getAttribute(grp, "requestCount");
			errorCount = (Integer) mBeans.getAttribute(grp, "errorCount");
			processingTime = (Long) mBeans.getAttribute(grp, "processingTime");
			maxTime = (Long) mBeans.getAttribute(grp, "maxTime");
		} else {
			bytesReceived = 0;
			bytesSent = 0;
			requestCount = 0;
			errorCount = 0;
			processingTime = 0;
			maxTime = 0;
		}
	}
 
	public static List<TomcatInformations> buildTomcatInformationsList() throws NumberFormatException, NullPointerException, UnknownHostException {
		if (!TOMCAT_USED) {
			return Collections.emptyList();
		}
		try {
			synchronized (THREAD_POOLS) {
				if (THREAD_POOLS.isEmpty() || GLOBAL_REQUEST_PROCESSORS.isEmpty()) {
					initTomcatBeans();
				}
			}
			final TomcatBeans mBeans = new TomcatBeans();
			final List<TomcatInformations> tomcatInformationsList = new ArrayList<TomcatInformations>(
					THREAD_POOLS.size());
			for (final ObjectName threadPool : THREAD_POOLS) {
				tomcatInformationsList.add(new TomcatInformations(mBeans, threadPool));
			}
			return tomcatInformationsList;
		} catch (final InstanceNotFoundException e) {
			return Collections.emptyList();
		} catch (final AttributeNotFoundException e) {
			return Collections.emptyList();
		} catch (final JMException e) {
			throw new IllegalStateException(e);
		}
	}
 
	private static void initTomcatBeans() throws MalformedObjectNameException {
		final TomcatBeans mBeans = new TomcatBeans();
		THREAD_POOLS.clear();
		GLOBAL_REQUEST_PROCESSORS.clear();
		THREAD_POOLS.addAll(mBeans.getTomcatThreadPools());
		GLOBAL_REQUEST_PROCESSORS.addAll(mBeans.getTomcatGlobalRequestProcessors());
	}
 
	String getName() {
		return name;
	}
 
	int getMaxThreads() {
		return maxThreads;
	}
 
	int getCurrentThreadCount() {
		return currentThreadCount;
	}
 
	int getCurrentThreadsBusy() {
		return currentThreadsBusy;
	}
 
	long getBytesReceived() {
		return bytesReceived;
	}
 
	long getBytesSent() {
		return bytesSent;
	}
 
	int getRequestCount() {
		return requestCount;
	}
 
	int getErrorCount() {
		return errorCount;
	}
 
	long getProcessingTime() {
		return processingTime;
	}
 
	long getMaxTime() {
		return maxTime;
	}
 
	/** {@inheritDoc} */
	@Override
	public String toString() {
		return getClass().getSimpleName() + "[端口名name=" + getName() + ", 最大线程数maxThreads="
				+ getMaxThreads() + ",当前线程数 currentThreadCount=" + getCurrentThreadCount()
				+ ", 当前活动线程数currentThreadsBusy=" + getCurrentThreadsBusy() + ",接收字节数 bytesReceived="
				+ getBytesReceived() + ",发送字节数 bytesSent=" + getBytesSent() + ",请求数 requestCount="
				+ getRequestCount() + ", 错误数errorCount=" + getErrorCount() + ", 处理时间processingTime="
				+ getProcessingTime() + ", 最大处理时间maxTime=" + getMaxTime() + ']'+",ip:"+getIpAddress()+",port:"+getPort();
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public int getPort() {
		return port;
	}
}