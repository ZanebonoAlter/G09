package cn.zucc.edu.model;

import java.sql.Timestamp;

public class Connector {
	private String name=null;
	private int maxThreads=0;
	private int currentThreadCount=0;
	private int currentThreadsBusy=0;
	private long maxTime=0;
	private long processingTime=0;
	private int requestCount=0;
	private int errorCount=0;
	private long bytesReceived=0;
	private long bytesSent=0;
	private int port;
	private Timestamp time;
	private String ipAddress;
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public Timestamp getTime() {
		return new Timestamp(System.currentTimeMillis());
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMaxThreads() {
		return maxThreads;
	}
	public void setMaxThreads(int maxThreads) {
		this.maxThreads = maxThreads;
	}
	public int getCurrentThreadCount() {
		return currentThreadCount;
	}
	public void setCurrentThreadCount(int currentThreadCount) {
		this.currentThreadCount = currentThreadCount;
	}
	public int getCurrentThreadsBusy() {
		return currentThreadsBusy;
	}
	public void setCurrentThreadsBusy(int currentThreadsBusy) {
		this.currentThreadsBusy = currentThreadsBusy;
	}
	public long getMaxTime() {
		return maxTime;
	}
	public void setMaxTime(long maxTime) {
		this.maxTime = maxTime;
	}
	public long getProcessingTime() {
		return processingTime;
	}
	public void setProcessingTime(long processingTime) {
		this.processingTime = processingTime;
	}
	public int getRequestCount() {
		return requestCount;
	}
	public void setRequestCount(int requestCount) {
		this.requestCount = requestCount;
	}
	public int getErrorCount() {
		return errorCount;
	}
	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}
	public long getBytesReceived() {
		return bytesReceived;
	}
	public void setBytesReceived(long bytesReceived) {
		this.bytesReceived = bytesReceived;
	}
	public long getBytesSent() {
		return bytesSent;
	}
	public void setBytesSent(long bytesSent) {
		this.bytesSent = bytesSent;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
}
