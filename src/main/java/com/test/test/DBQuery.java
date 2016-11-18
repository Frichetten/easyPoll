package com.test.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBQuery{

	static Connection dbc = DBConnection.getConnection();
	
	static Statement statement;
	
	static ResultSet rs;
	
	//public void setStatement() throws SQLException{
	//	statement = dbc.createStatement();
	//}
	
	public static User Login(String query) throws SQLException{
			User tempUser = new User();
			statement = dbc.createStatement();
			System.out.println(query);
			rs = statement.executeQuery(query);
			System.out.println(query);
			int hi = rs.findColumn("Username");
			if(rs.next()){
				System.out.println("User Logged in: " + rs.getString(1));
				tempUser.setUsername(rs.getString(1));
			}
			else
				tempUser.setUsername("");
			return tempUser;
		
	}

}
