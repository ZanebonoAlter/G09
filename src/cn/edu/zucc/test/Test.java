package cn.edu.zucc.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        String result = "";
        Document document = null;//引入org.dom4j包
        try {
            result = getHtmlContext("http://localhost:9999/manager/status?XML=true", "zanebono", "123456");
           split_result(result);
            document = DocumentHelper.parseText(result);//将字符串转化为XML的Document
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        //System.out.println(document.asXML());
	}
    public static String getHtmlContext(String tempurl, String username, String password) throws IOException {
        URL url = null;
        BufferedReader breader = null;
        InputStream is = null;
        StringBuffer resultBuffer = new StringBuffer();
        try {
            url = new URL(tempurl);
            String userPassword = username + ":" + password;
            String encoding = new sun.misc.BASE64Encoder().encode (userPassword.getBytes());//在classpath中添加rt.jar包，在%java_home%/jre/lib/rt.jar
 
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Authorization", "Basic " + encoding);
            is = conn.getInputStream();
            breader = new BufferedReader(new InputStreamReader(is));
            String line = "";
            while ((line = breader.readLine()) != null) {
                resultBuffer.append(line);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } finally {
            if(breader != null)
                breader.close();
            if(is != null)
                is.close();
        }
        return resultBuffer.toString();
    }
    public static String[] split_result (String result) {
    	String temp[] = result.split("</status>|<status>|<jvm>|</jvm>|</connector>|<?xml version=\"1.0\" encoding=\"utf-8\"?>|\"\"");
    	//以上为处理不需要的字符
    	String temp_memory[];
    	String temp_memory_pool[];
    	String memory[];
    	String memory_pool[];
    	String temp_connect[];
    	String connect[];
    	for(int i=0;i<temp.length;i++) {//便利需要进一步处理的字符
    		//####################内存############################
    		if(temp[i].indexOf("memory")!=-1) {//如果与内存有关
    			temp_memory = temp[i].split("<|>|/",4);//划分总内存和内存池细节
    			for(int j=0;j<temp_memory.length;j++) {//继续遍历划分
    				if(temp_memory[j].length()>0) {//有效字符
    					//################################################
    					if(temp_memory[j].indexOf("memorypool")!=-1) {//如果是内存池
    						temp_memory_pool=temp_memory[j].split("<|/>");//细节划分内存池
    						for(int k=0;k<temp_memory_pool.length;k++) {//遍历
    							if(temp_memory_pool[k].length()>0) {//有效字符
    								//System.out.println(temp_memory_pool[k]);
    								memory_pool=temp_memory_pool[k].split("memorypool|name|type|usageInit|usageCommitted|usageMax|usageUsed|=|'");
    								//提取关键字符串
    								for(int a=0;a<memory_pool.length;a++) {
    									if(memory_pool[a].length()>0&&!memory_pool[a].equals(" ")) {//去除空格和空值
    										//name type usageInit usageCommitted usageMax usageUsed 依次存入数据库,一个计数器判断类型
    										//System.out.println(memory_pool[a]);
    									}
    								}
    							}	
    						}
    					}
    					else {//总内存
    						//System.out.println(temp_memory[j]);
    						memory=temp_memory[j].split("memory|free|total|max|'|=");
    						for(int a=0;a<memory.length;a++) {
								if(memory[a].length()>0&&!memory[a].equals(" ")) {//去除空格和空值
									//free total max 依次存入数据库
									//System.out.println(memory[a]);
								}
							}
    					}
    					//System.out.println(temp_memory[j]);
    					//####################################################
    					}	
    			}
    		}
    		//#####################连接池################################
    		else if(temp[i].indexOf("connector")!=-1){
    			//System.out.println(temp[i]);
    			temp_connect=temp[i].split("<workers>|</workers>");
    			//分离worker和connector
    			for(int j=0;j<temp_connect.length;j++) {
    				if(temp_connect[j].length()>0) {
    					if(temp_connect[j].indexOf("connector")!=-1) {//为连接池
    						connect=temp_connect[j].split("<connector|<threadInfo|<requestInfo|>|/| |name|maxThreads|currentThreadCount|currentThreadsBusy|maxTime|processingTime|requestCount|errorCount|bytesReceived|bytesSent|=|\"|'");
    						for(int k=0;k<connect.length;k++) {
    							if(connect[k].length()>0&&!connect.equals(" ")) {
    								//System.out.println(connect[k]);
    							}
    						}
    					}else if(temp_connect[j].indexOf("worker")!=-1) {
    						//如果需要另外处理
    					}
    				}
    				//System.out.println(temp_connect[j]);
    			}
    		}
    		//System.out.println(temp[i]);
    	}
    	return null;
    }
}
