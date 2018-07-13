package cn.zucc.edu.model;

public class Config {
	private String ipAddress;
	private int port;
	private double memory_total;
	private double memory_pool_Compressed_Class_Space_used;
	private double memory_pool_Code_Cache_used;
	private double memory_pool_Metaspace_used;
	private double memory_pool_PS_Eden_Space_used;
	private double memory_pool_PS_Old_Gen_used;
	private double memory_pool_PS_Survivor_Space_used;
	private double connector_currentThreadsCount;
	private double connector_currentThreadsBusy;
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
	public double getMemory_total() {
		return memory_total;
	}
	public void setMemory_total(double memory_total) {
		this.memory_total = memory_total;
	}

	public double getMemory_pool_Compressed_Class_Space_used() {
		return memory_pool_Compressed_Class_Space_used;
	}
	public void setMemory_pool_Compressed_Class_Space_used(double memory_pool_Compressed_Class_Space_used) {
		this.memory_pool_Compressed_Class_Space_used = memory_pool_Compressed_Class_Space_used;
	}
	public double getMemory_pool_Code_Cache_used() {
		return memory_pool_Code_Cache_used;
	}
	public void setMemory_pool_Code_Cache_used(double memory_pool_Code_Cache_used) {
		this.memory_pool_Code_Cache_used = memory_pool_Code_Cache_used;
	}
	public double getMemory_pool_Metaspace_used() {
		return memory_pool_Metaspace_used;
	}
	public void setMemory_pool_Metaspace_used(double memory_pool_Metaspace_used) {
		this.memory_pool_Metaspace_used = memory_pool_Metaspace_used;
	}
	public double getMemory_pool_PS_Eden_Space_used() {
		return memory_pool_PS_Eden_Space_used;
	}
	public void setMemory_pool_PS_Eden_Space_used(double memory_pool_PS_Eden_Space_used) {
		this.memory_pool_PS_Eden_Space_used = memory_pool_PS_Eden_Space_used;
	}
	public double getMemory_pool_PS_Old_Gen_used() {
		return memory_pool_PS_Old_Gen_used;
	}
	public void setMemory_pool_PS_Old_Gen_used(double memory_pool_PS_Old_Gen_used) {
		this.memory_pool_PS_Old_Gen_used = memory_pool_PS_Old_Gen_used;
	}
	public double getMemory_pool_PS_Survivor_Space_used() {
		return memory_pool_PS_Survivor_Space_used;
	}
	public void setMemory_pool_PS_Survivor_Space_used(double memory_pool_PS_Survivor_Space_used) {
		this.memory_pool_PS_Survivor_Space_used = memory_pool_PS_Survivor_Space_used;
	}
	public double getConnector_currentThreadsCount() {
		return connector_currentThreadsCount;
	}
	public void setConnector_currentThreadsCount(double connector_currentThreadsCount) {
		this.connector_currentThreadsCount = connector_currentThreadsCount;
	}
	public double getConnector_currentThreadsBusy() {
		return connector_currentThreadsBusy;
	}
	public void setConnector_currentThreadsBusy(double connector_currentThreadsBusy) {
		this.connector_currentThreadsBusy = connector_currentThreadsBusy;
	}

}
