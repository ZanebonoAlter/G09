package com.version2.deal;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServletRequest;

/**
 * Application Lifecycle Listener implementation class TomcatStatusListener
 *
 */
public class TomcatStatusListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public TomcatStatusListener() {
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
            	new TomcatWrite().write();
            }
        } , parse, 60*1000);
    }
	
}
