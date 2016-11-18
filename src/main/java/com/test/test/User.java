package com.test.test;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.web.servlet.ModelAndView;

public class User {
	   private String username;
	   private String password;
	   private String email;
	   
	   public User(){
		   username = "";
		   password = "";
		   email = "";
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
	   public static User verifyUser (String username, String password) throws SQLException{
		   username = "'" + username + "'";
		   password = "'" + password + "'";
		   
		   
		   String loginQuery = "SELECT Username FROM RUser WHERE Username = " + username + 
					" AND Pword = " + password + ";";
		   
		   System.out.println("DEBUG::User: " + username + ", password: " + password);
		   
				return  DBQuery.Login(loginQuery);
		   
	   }
	   
	   
	   public String getEmail(){
		   return email;
	   }
	}
