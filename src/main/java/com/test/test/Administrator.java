/**
 * 
 */
package com.test.test;

import java.sql.SQLException;
import java.util.ArrayList;

public class Administrator extends User {
	
	//Private object variables
	private String username;
	private String password;
	private String email;
	private ArrayList<ReportedQuestion> reportedQuestions;
	
	/**
	 * Will make sure that the username and password that are passed in are valid and if so 
	 * will return a Administrator object
	 * @param email
	 * @param password
	 * @return
	 */
	public static Administrator verifyAdmin(String email, String password){
		return DBQuery.adminLogin(email, password);
	}
	
	/**
	 * Public constructor needed by the API although we don't use it. If this isn't here
	 * Spring MVC will freak out. DO NOT DELETE IT IS A TEDIOUS TO FIND BUG
	 */
	public Administrator(){
		
	}
	
	/*
	 * Will create an admin with a given username and password and insert
	 * it into the DB
	 */
	public static void createAdmin(String username, String password){
		DBQuery.createAdmin(username, password);
	}
	
	/*
	 * Sends feedback by adding the message into the DB
	 */
	public static void sendFeedback(String textarea){
		DBQuery.sendFeedback(textarea);
	}
	
	/*
	 * Allows Admins to see all the feedback in the DB
	 */
	public static ArrayList<String> getFeedback(){
		return DBQuery.getFeedback();
	}
	
	/*
	 * Sends the support ticket from the user to the Admin
	 */
	public static void sendSupportTicket(String textarea, String username){
		DBQuery.sendSupportTicket(textarea,username);
	}
	
	/*
	 * Allows the user to getSupportTickets that include the name of the 
	 * sender along with their message and ticket number
	 */
	public static ArrayList<Administrator> getSupportTickets(){
		return DBQuery.getSupportTickets();
	}
	
	/*
	 * Constructor that will build a entire Admin object from the DB and return it
	 */
	public Administrator(String username) {
		Administrator ad = DBQuery.getAdmin(username);
		this.username = ad.getUsername();
		this.email = ad.getEmail();
		this.reportedQuestions = ad.getReportedQuestions();
	}
	
	/**
	 * Standard constructor
	 * @param username
	 * @param email
	 * @param reportedQuestions
	 */
	public Administrator(String username, String email, ArrayList<ReportedQuestion> reportedQuestions){
		this.username = username;
		this.email = email;
		this.reportedQuestions = reportedQuestions;
	}
	
	//Getters and setters -> Don't need better comments
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public ArrayList<ReportedQuestion> getReportedQuestions() {
		return reportedQuestions;
	}
	public void setReportedQuestions(ArrayList<ReportedQuestion> reportedQuestions) {
		this.reportedQuestions = reportedQuestions;
	}
}
