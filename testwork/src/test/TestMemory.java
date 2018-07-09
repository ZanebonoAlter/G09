package test;

public class TestMemory {

    public static void main(String[] args) {
    	for(;;){

       System. out .println( " 内存信息1 :" + toMemoryInfo());
       try {
		Thread.sleep(1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    	}

    }

    /**

      * 获取当前 jvm 的内存信息

      */

    public static String toMemoryInfo() {

       Runtime currRuntime = Runtime.getRuntime ();

       int nFreeMemory = ( int ) (currRuntime.freeMemory() / 1024 / 1024);

       int nTotalMemory = ( int ) (currRuntime.totalMemory() / 1024 / 1024);

       return nFreeMemory + "M/" + nTotalMemory +"M(free/total)" ;

    }

}