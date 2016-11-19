package com.test.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBQuery{

	static Connection dbc = DBConnection.getConnection();
	static Statement statement;
	static ResultSet rs;
	
	public static User Login(String email, String password) throws SQLException{
		User tempUser = new User();
		email = "'" + email + "'";
		password = "'" + password + "'";
		   
		String loginQuery = "SELECT Username FROM RUser WHERE Email = " + email + 
					" AND Pword = " + password + ";";
		statement = dbc.createStatement();
		rs = statement.executeQuery(loginQuery);
		if(rs.next()){
			System.out.println("User Logged in: " + rs.getString(1));
			tempUser.setUsername(rs.getString(1));
		}
		else
			tempUser.setUsername("");
		return tempUser;
	}
	
	public static Administrator adminLogin(String email, String password) throws SQLException{
		Administrator tempAdmin = new Administrator();
		email = "'" + email + "'";
		password = "'" + password + "'";
		   
		String loginQuery = "SELECT Username FROM AdminUser WHERE Email = " + email + 
					" AND Pword = " + password + ";";
		statement = dbc.createStatement();
		rs = statement.executeQuery(loginQuery);
		if(rs.next()){
			System.out.println("User Logged in: " + rs.getString(1));
			tempAdmin.setUsername(rs.getString(1));
		}
		else
			tempAdmin.setUsername("");
		return tempAdmin;
	}
	
	public static String getPollDescription(String pollId) throws SQLException{
		String toReturn = "";
		String searchQuery = "SELECT Description FROM PollData WHERE PollNum= '"+pollId+"';";
		statement = dbc.createStatement();
		rs = statement.executeQuery(searchQuery);
		if (rs.next()){
			toReturn = rs.getString(1);
		}
		else
			toReturn = "null";
		return toReturn;
	}

}
