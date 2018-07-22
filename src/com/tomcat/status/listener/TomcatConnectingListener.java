package com.tomcat.status.listener;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.stereotype.Repository;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tomcat.status.model.connecting;
import com.tomcat.status.service.ConnectingService;
import com.tomcat.status.service.LogService;

/**
 * Application Lifecycle Listener implementation class TomcatConnectingListener
 *
 */
@Repository
public class TomcatConnectingListener implements ServletContextListener {
	
    /**
     * Default constructor. 
     */
    public TomcatConnectingListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	Timer timer = new Timer();
        Date parse = new Timestamp(System.currentTimeMillis());
        timer.scheduleAtFixedRate(new TimerTask() {
        	@Override
            public void run() {
        		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
        		ConnectingService connectingservice =(ConnectingService)webApplicationContext.getBean("ConnectingService");
        		LogService logservice =(LogService)webApplicationContext.getBean("LogService");   		
        		List<connecting> ls = connectingservice.selectUnusedConnecting();
            	if(ls.size()>0)
            		for(connecting conn:ls) {
            			connectingservice.cleanConnecting(conn);
            			logservice.addWarnConnecting(conn);
            		}
            }
        } , parse, 60*1000);
    }
	
}
