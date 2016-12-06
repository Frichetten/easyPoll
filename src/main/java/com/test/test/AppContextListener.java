package com.test.test;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	@Override
	public void contextInitialized(ServletContextEvent context) {
		System.out.println("it works!");
		DBQuery dbquery = new DBQuery();
		context.getServletContext().setAttribute("DB", dbquery);
		
	}

}
