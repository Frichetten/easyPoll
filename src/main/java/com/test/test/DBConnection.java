package com.test.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBConnection {
	
	  //Private class variables
	  private static Connection connection;

	  //This will return a singleton object for the DB connection
	  public static Connection getConnection() {
	    try {
			if (connection == null || connection.isClosed()) {
				Class.forName("com.mysql.jdbc.Driver");
				String url = "jdbc:mysql://localhost:3306/easyPoll";
				connection = DriverManager.getConnection(url,"root","password44");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Class Not Found Exception");
		} catch (SQLException e) {
			System.out.println("SQL Exception in DBConnection");
		}
	    return connection;
	  }
}