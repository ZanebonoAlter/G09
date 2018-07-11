package cn.zucc.edu.model;

import java.sql.Timestamp;

public class Memory {
	private double free;
	private double total;
	private double max;
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
	public double getFree() {
		return free;
	}
	public void setFree(double free) {
		this.free = free;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getMax() {
		return max;
	}
	public void setMax(double max) {
		this.max = max;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
}
