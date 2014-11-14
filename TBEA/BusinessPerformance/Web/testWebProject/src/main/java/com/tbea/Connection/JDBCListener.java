package com.tbea.Connection;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.lang.*;
public class JDBCListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		Thread trd = new Thread(new Runnable() {
			 public void run(){
				 DBConnectionManager.getInstance("mobileSys");
				 System.out.println("JDBC connection finished!");
			 }
		});
		trd.start();
	}

}
