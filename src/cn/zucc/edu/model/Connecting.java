package cn.zucc.edu.model;

import java.sql.Timestamp;

public class Connecting {
	private String ipAddress;
	private int port;
	private String status;
	private String name;
	private Timestamp last_time;
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getLast_time() {
		return  new Timestamp(System.currentTimeMillis());
	}
	public void setLast_time(Timestamp last_time) {
		this.last_time = last_time;
	}
}
