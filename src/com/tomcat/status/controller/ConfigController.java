package com.tomcat.status.controller;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import com.tomcat.status.service.ConfigService;
import com.tomcat.status.service.ConnectingService;

@RestController("/controller")
public class ConfigController {
	@Resource
	private ConnectingService connectingservice;
	@Resource
	private ConfigService configservice;
	
	
//	@Resource
//	private ConnectorService connectorservice;
//	@RequestMapping(value="/test",method=RequestMethod.GET) 
//	public String test(HttpServletRequest request,Model model) throws IllegalArgumentException, IllegalAccessException, ClassNotFoundException{  
//		List<connector> ls = connectorservice.selectTopTwo();
//		if(ls.size()>0)
//		for(connector c : ls) {
//			Class<?> x = null;  
//	        x = Class.forName("com.tomcat.status.model.connector");  
//	        Field [] fields = x.getDeclaredFields();  
//	          
//	        for(Field f:fields){  
//	            f.setAccessible(true);  
//	        }  
//	        //输出所有属性  
//	        System.out.println("=============About "+c.getName()+"===============");  
//	        for(Field f:fields){  
//	            String field = f.toString().substring(f.toString().lastIndexOf(".")+1);         //取出属性名称  
//	            System.out.println(c.getIpaddress()+"."+field+" --> "+f.get(c));  
//	        } 
//	      long test= c.getTime().getTime();
//	      long now = System.currentTimeMillis();
//	      if((now-test)/1000>60) {
//	    	  System.out.println((int)((now-test)/1000/60)+"min");
//	      }
//		}
////		configKey configkey = new configKey();
////		String ipAddress = request.getParameter("ipAddress");
////		configkey.setIpaddress(ipAddress);
////		configkey.setPort(9999);
////        System.out.println("Ipaddress:"+ipAddress);
////        config config = this.configservice.getConfigByKey(configkey);
////        model.addAttribute("config",config);  
//        return "index";  
//    } 
	
}
