package cn.zucc.edu.model;

import java.math.BigInteger;
import java.sql.Timestamp;

public class Memory_Pool {
	private String Name;
	private String Type;
	private double Initial;
	private double Committed;
	private double Maximum;
	private double Used;
	private int Port;
	private Timestamp time;
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public double getInitial() {
		return Initial;
	}
	public void setInitial(double initial) {
		Initial = initial;
	}
	public double getCommitted() {
		return Committed;
	}
	public void setCommitted(double committed) {
		Committed = committed;
	}
	public double getMaximum() {
		return Maximum;
	}
	public void setMaximum(double maximum) {
		Maximum = maximum;
	}
	public double getUsed() {
		return Used;
	}
	public void setUsed(double used) {
		Used = used;
	}
	public int getPort() {
		return Port;
	}
	public void setPort(int port) {
		Port = port;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
}
