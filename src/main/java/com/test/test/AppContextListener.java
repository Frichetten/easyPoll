package com.test.test;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent context) {
		// TODO Auto-generated method st
		System.out.println("it works!");
		DBQuery dbquery = new DBQuery();
		context.getServletContext().setAttribute("DB", dbquery);
		
	}

}
