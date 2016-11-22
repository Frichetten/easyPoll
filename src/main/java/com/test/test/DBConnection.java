package com.test.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBConnection {
	
	private static Connection connection;

	  public static Connection getConnection() {
	    try {
			if (connection == null || connection.isClosed()) {
				Class.forName("com.mysql.jdbc.Driver");
				String url = "jdbc:mysql://localhost:3306/easyPoll";
				connection = DriverManager.getConnection(url,"root","");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Class Not Found Exception");
		} catch (SQLException e) {
			System.out.println("SQL Exception in DBConnection");
		}
	    return connection;
	  }
}