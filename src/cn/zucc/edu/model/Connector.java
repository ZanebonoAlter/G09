package cn.zucc.edu.model;

import java.sql.Timestamp;

public class Connector {
	private String name=null;
	private int maxThreads=0;
	private int currentThreadCount=0;
	private int currentThreadsBusy=0;
	private int maxTime=0;
	private int processingTime=0;
	private int requestCount=0;
	private int errorCount=0;
	private int bytesReceived=0;
	private int bytesSent=0;
	private int port;
	private Timestamp time;
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public Timestamp getTime() {
		return time;
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
	public int getMaxTime() {
		return maxTime;
	}
	public void setMaxTime(int maxTime) {
		this.maxTime = maxTime;
	}
	public int getProcessingTime() {
		return processingTime;
	}
	public void setProcessingTime(int processingTime) {
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
	public int getBytesReceived() {
		return bytesReceived;
	}
	public void setBytesReceived(int bytesReceived) {
		this.bytesReceived = bytesReceived;
	}
	public int getBytesSent() {
		return bytesSent;
	}
	public void setBytesSent(int bytesSent) {
		this.bytesSent = bytesSent;
	}
}
