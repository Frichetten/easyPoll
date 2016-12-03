package com.test.test;

import java.sql.SQLException;
import java.util.ArrayList;

public class ReportedQuestion {
	
	//Private class variables
	private String reason;
	private int pollNum;
	private String pollName;
	private String Question;
	private String username;
	private String pollDescription;
	
	//Constructor
	public ReportedQuestion(int pollNum, String username, String Question, String pollDescription, String pollName){
		this.pollNum = pollNum;
		this.username = username;
		this.pollDescription = pollDescription;
		this.pollName = pollName;
		this.Question = Question;
	}
	
	/*
	 * Adds the question the the reported questions table
	 */
	public static void addReportedQuestion(String username,int PollNum) {
		DBQuery.addReportedQuestion(username, PollNum);
	}
	
	//Getters and setters
	
	public String getQuestion(){
		return this.Question;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public int getPollNum() {
		return pollNum;
	}
	public void setPollNum(int pollNum) {
		this.pollNum = pollNum;
	}
	public String getPollName() {
		return pollName;
	}
	public void setPollName(String pollName) {
		this.pollName = pollName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPollDescription() {
		return pollDescription;
	}
	public void setPollDescription(String pollDescription) {
		this.pollDescription = pollDescription;
	}
	public void setQuestion(String question) {
		Question = question;
	}
}
