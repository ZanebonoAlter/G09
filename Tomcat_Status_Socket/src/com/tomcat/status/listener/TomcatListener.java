package com.tomcat.status.listener;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.tomcat.status.deal.TomcatWrite;

/**
 * Application Lifecycle Listener implementation class TomcatListener
 *
 */
public class TomcatListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public TomcatListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	new TomcatWrite().release();
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	TomcatWrite tw = new TomcatWrite();
    	tw.use_or_add_config();
    	Timer timer = new Timer();
        Date parse = new Timestamp(System.currentTimeMillis());
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
            	new TomcatWrite().connect();//表示连接
            	new TomcatWrite().write();
            }
        } , parse, 60*1000);
    }
}
	

