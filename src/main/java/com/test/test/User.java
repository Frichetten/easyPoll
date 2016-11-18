package com.test.test;

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
	   
	   public static ArrayList<Poll> getPublicPolls() throws SQLException{
		   // Insert the user into the database
		   String publicPollsQuery= "SELECT PollName, Description, p.PollNum FROM Polls p JOIN PollData pd on pd.PollNum = p.PollNum;";
		   Statement st = dbc.createStatement();
		   ResultSet rs = st.executeQuery(publicPollsQuery);
		   ArrayList<Poll> toReturn = new ArrayList<Poll>();
		   while (rs.next()) {
			   toReturn.add(new Poll(rs.getString(1),null, rs.getString(2),null,null,null, rs.getString(3)));
		   }
		   return toReturn;
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
