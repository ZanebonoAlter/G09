//package cn.zucc.edu.deal;
//
//import java.io.IOException;
//
//import org.dom4j.Document;
//import org.dom4j.DocumentException;
//import org.dom4j.DocumentHelper;
//
//public class ThreadDeal implements Runnable{
//	private int port;
//	private String username;
//	private String password;
//	public ThreadDeal(int port,String username,String password) {
//		this.port=port;
//		this.username=username;
//		this.password=password;
//	}
//	@Override
//	public void run() {
//		// TODO Auto-generated method stub
//		String result = "";
//        Document document = null;//引入org.dom4j包
//        try {     
//        	while(true) {
//        		result = Deal.getHtmlContext("http://localhost:"+this.port+"/manager/status?XML=true", this.username, this.password);
//        		Deal.split_result(result,port);
//        		document = DocumentHelper.parseText(result);//将字符串转化为XML的Document
//        		try {
//        			Thread.sleep(60000);
//        		} catch (InterruptedException e) {
//        			// TODO Auto-generated catch block
//        			e.printStackTrace();
//        		}
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        }
//	}
//
//}
