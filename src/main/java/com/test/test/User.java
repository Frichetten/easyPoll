package com.test.test;

<<<<<<< HEAD
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.web.servlet.ModelAndView;
=======
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
>>>>>>> f0b8c9520420e5e7b5ef889fbc9789f4e1b8a8cf

public class User {
	   private String username;
	   private String password;
	   private String email;
<<<<<<< HEAD
	   
	   public User(){
		   username = "";
		   password = "";
		   email = "";
	   }

=======
	   static Connection dbc = DBConnection.getConnection();
	   
	   public static ArrayList<Poll> getPublicPolls() throws SQLException{
		   String publicPollsQuery= "SELECT PollName, Description, p.PollNum FROM Polls p JOIN PollData pd on pd.PollNum = p.PollNum;";
		   Statement st = dbc.createStatement();
		   ResultSet rs = st.executeQuery(publicPollsQuery);
		   ArrayList<Poll> toReturn = new ArrayList<Poll>();
		   while (rs.next()) {
			   toReturn.add(new Poll(rs.getString(1),null, rs.getString(2),null,null,null, rs.getString(3)));
		   }
		   return toReturn;
	   }
	   
>>>>>>> f0b8c9520420e5e7b5ef889fbc9789f4e1b8a8cf
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
