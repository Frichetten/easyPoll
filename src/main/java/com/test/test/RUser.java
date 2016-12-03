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

public class RUser extends User{
	
	   //Private class variables
	   private String username;
	   private String password;
	   private String email;
	   private ArrayList<Group> pollGroups;
	   public ArrayList<Poll> polls;
	   
	   /**
	    * DONT DELETE THIS SPRING MVC NEEDS IT TO FUNCTION
	    */
	   public RUser(){
	   }
	   
	   public static RUser verifyUser(String email, String password) {
		   return DBQuery.Login(email, password);
	   }
	   
	   public static boolean checkUser(String username){
		   return DBQuery.checkUser(username);
	   }
	   
	   public static Group getGroup(int groupNum){
			return DBQuery.getPollGroup(groupNum);
	   }
	   
	   public static void forgotPassword(String email){
		   DBQuery.forgotPassword(email);
	   }
	   public static void deleteAccount(String email){
		   DBQuery.deleteAccount(email);
	   }
	   public static void updateAccount(String queryName, String username, String email, String password){
		   DBQuery.updateAccount(queryName, username, email, password);
	   }
	   
	   public static ArrayList<RUser> getGroupMembers(String groupNum){
		   return DBQuery.getGroupMembers(groupNum);
	   }
	   
	   public static ArrayList<Poll> getPublicPolls() {
		   return DBQuery.getPublicPolls();
	   }
	   
	   public static int getMyPollsCount(String username){
		   return DBQuery.getMyPollsCount(username);
	   }
	   
	   public static int getMyPollsVoted(String username){
		   return DBQuery.getMyPollsVoted(username);
	   }
	   
	   public static void createRUser(String username, String password, String email){
		   DBQuery.createRUser(username, password, email);
	   }
	   
	   public static String getMyPollsMostVoted(String username){
		   return DBQuery.getMyPollsMostVoted(username);
	   }
	   
	   public static ArrayList<Poll> getPolls() {
		   return DBQuery.getPolls();
	   }
	   
	   public static void addPoll(Poll poll, String username, ArrayList<String> answerOptions) {
		   DBQuery.addPoll(poll, username, answerOptions);
	   }
	   
	   public static ArrayList<Poll> getMyPolls(String username){
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
