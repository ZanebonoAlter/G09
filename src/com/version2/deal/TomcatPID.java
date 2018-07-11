package com.version2.deal;
 
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
 
final public class TomcatPID {
 
	/**
	 * 获取当前进程id
	 * @return
	 */
	public static String getPID(){
		final RuntimeMXBean rtb = ManagementFactory.getRuntimeMXBean();
		//pid@host
		return rtb.getName().split("@")[0];
	}
	public static String getHost(){
		final RuntimeMXBean rtb = ManagementFactory.getRuntimeMXBean();
		//pid@host
		return rtb.getName().split("@")[1];
	}
}