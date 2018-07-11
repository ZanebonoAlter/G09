package cn.edu.zucc.test;

import java.lang.management.CompilationMXBean;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;
import java.util.List;
import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;
public class JVM {
	 
	/**
	 * @param ֱ��ͨ��jdk����ȡϵͳ���״̬����1.5.0_10-b03�汾���ϲ���ͨ��
	 */
	public static void main(String[] args) {
		
		System.out.println("=======================ͨ��java����ȡ���ϵͳ״̬============================ ");
		int i = (int)Runtime.getRuntime().totalMemory()/1024;//Java ������е��ڴ�����,���ֽ�Ϊ��λ
		System.out.println("�ܵ��ڴ��� i is "+i);
		int j = (int)Runtime.getRuntime().freeMemory()/1024;//Java ������еĿ����ڴ���
		System.out.println("�����ڴ��� j is "+j);
		System.out.println("����ڴ��� is "+Runtime.getRuntime().maxMemory()/1024);
	
		System.out.println("=======================OperatingSystemMXBean============================ ");
		OperatingSystemMXBean osm = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
	    System.out.println(osm.getFreeSwapSpaceSize()/1024);
	    System.out.println(osm.getFreePhysicalMemorySize()/1024);
	    System.out.println(osm.getTotalPhysicalMemorySize()/1024);
	    
	    //��ȡ����ϵͳ�����Ϣ
	    System.out.println("osm.getArch() "+osm.getArch());
	    System.out.println("osm.getAvailableProcessors() "+osm.getAvailableProcessors());
	    System.out.println("osm.getCommittedVirtualMemorySize() "+osm.getCommittedVirtualMemorySize());
	    System.out.println("osm.getName() "+osm.getName());
	    System.out.println("osm.getProcessCpuTime() "+osm.getProcessCpuTime());
	    System.out.println("osm.getVersion() "+osm.getVersion());
	    //��ȡ����������ڴ�ʹ�����
	    System.out.println("=======================MemoryMXBean============================ ");
	    MemoryMXBean mm=(MemoryMXBean)ManagementFactory.getMemoryMXBean();
	    System.out.println("getHeapMemoryUsage "+mm.getHeapMemoryUsage());
	    System.out.println("getNonHeapMemoryUsage "+mm.getNonHeapMemoryUsage());
	    //��ȡ�����̵߳ĸ���״̬��CPU ռ��������Լ�����ϵͳ�е��߳�״��
	    System.out.println("=======================ThreadMXBean============================ ");
	    ThreadMXBean tm=(ThreadMXBean)ManagementFactory.getThreadMXBean();
	    System.out.println("getThreadCount "+tm.getThreadCount());
	    System.out.println("getPeakThreadCount "+tm.getPeakThreadCount());
	    System.out.println("getCurrentThreadCpuTime "+tm.getCurrentThreadCpuTime());
	    System.out.println("getDaemonThreadCount "+tm.getDaemonThreadCount());
	    System.out.println("getCurrentThreadUserTime "+tm.getCurrentThreadUserTime());
	    
	    //��ǰ���������
	    System.out.println("=======================CompilationMXBean============================ ");
	    CompilationMXBean gm=(CompilationMXBean)ManagementFactory.getCompilationMXBean();
	    System.out.println("getName "+gm.getName());
	    System.out.println("getTotalCompilationTime "+gm.getTotalCompilationTime());
	    
	    //��ȡ����ڴ�ص�ʹ�����
	    System.out.println("=======================MemoryPoolMXBean============================ ");
	    List<MemoryPoolMXBean> mpmList=ManagementFactory.getMemoryPoolMXBeans();
	    for(MemoryPoolMXBean mpm:mpmList){
		    System.out.println("getUsage "+mpm.getName());
		    //System.out.println("getMemoryManagerNames "+mpm.getMemoryManagerNames().toString());
		    System.out.println("Type:"+mpm.getType().toString());
		    System.out.println("Memory Usage:"+mpm.getUsage());
	    }
        //��ȡGC�Ĵ����Լ�����ʱ��֮�����Ϣ
	    System.out.println("=======================MemoryPoolMXBean============================ ");
	    List<GarbageCollectorMXBean> gcmList=ManagementFactory.getGarbageCollectorMXBeans();
	    for(GarbageCollectorMXBean gcm:gcmList){
		    System.out.println("getName "+gcm.getName());
		    System.out.println("getMemoryPoolNames "+gcm.getMemoryPoolNames());
	    }
        //��ȡ����ʱ��Ϣ
	    System.out.println("=======================RuntimeMXBean============================ ");
	    RuntimeMXBean rmb=(RuntimeMXBean)ManagementFactory.getRuntimeMXBean();
	    System.out.println("getClassPath "+rmb.getClassPath());
	    System.out.println("getLibraryPath "+rmb.getLibraryPath());
	    System.out.println("getVmVersion "+rmb.getVmVersion());
	}
 
}