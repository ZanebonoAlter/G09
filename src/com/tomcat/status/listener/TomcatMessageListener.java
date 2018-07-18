package com.tomcat.status.listener;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.aliyuncs.exceptions.ClientException;
import com.tomcat.status.model.connecting;
import com.tomcat.status.service.ConnectingService;
import com.tomcat.status.service.LogService;
import com.tomcat.status.service.MessageService;

/**
 * Application Lifecycle Listener implementation class TomcatmessageListener
 *
 */
public class TomcatMessageListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public TomcatMessageListener() {
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
        		MessageService messageservice =(MessageService)webApplicationContext.getBean("MessageService");
        		LogService logservice =(LogService)webApplicationContext.getBean("LogService");
        		int number = logservice.selectUnSendLog();
        		if(number>0) {
        			try {
        				logservice.updateSendLog();
						messageservice.sendMessage(number);
					} catch (ClientException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        		}
            }
        } , parse, 60*1000);
    }
	
}
