package com.test.test;

import java.sql.ResultSet;
import java.sql.*;
import java.sql.SQLException;
import org.springframework.web.servlet.ModelAndView;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class User {
	   private String username;
	   private String password;
	   private String email;
	   static Connection dbc = DBConnection.getConnection();
	   public ArrayList<Poll> polls;
	   
	   public User(){
	   }
	   
	   public static User verifyUser(String email, String password) throws SQLException{
		   return DBQuery.Login(email, password);
	   }
	   
	   public static ArrayList<Poll> getPolls() throws SQLException{

		   return DBQuery.getPolls();
	   }
	   
	   public static ArrayList<Poll> getMyPolls(String username) throws SQLException{

		   return DBQuery.getMyPolls(username);
	   }
	   
	   public void setUsername(String username) {
	      this.username = username;
	   }
	   public String getUsername() {
	      return username;
	   }
	   
	   public void setPassword(String password) {
	      this.password = password;
	   }
	   public String getPassword() {
	      return password;
	   }
	   
	   public void setEmail(String email){
		   this.email = email;
	   }
	   
	   public String getEmail(){
		   return email;
	   }
	}
