/**
 * 
 */
package com.test.test;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author dalle
 *
 */
public class ReportedQuestion {
	// private PollData reportedQuestion;
	private String reason;
	private int pollNum;
	private String pollName;
	private String Question;
	private String username;
	private String pollDescription;
	
	public ReportedQuestion(int pollNum, String username, String Question, String pollDescription, String pollName){
		this.pollNum = pollNum;
		this.username = username;
		this.pollDescription = pollDescription;
		this.pollName = pollName;
		this.Question = Question;
	}
	
	public static void addReportedQuestion(String username,int PollNum) {
		DBQuery.addReportedQuestion(username, PollNum);
	}
	
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
