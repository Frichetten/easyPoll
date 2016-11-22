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
	
	public ReportedQuestion(int pollNum, String username, String Question, String pollName, String pollDescription){
		this.pollNum = pollNum;
		this.username = username;
		this.pollDescription = pollDescription;
		this.pollName = pollName;
		this.Question = Question;
	}
	
	public static void addReportedQuestion(String username,int PollNum) throws SQLException{
		DBQuery.addReportedQuestion(username, PollNum);
	}
	
	public String getQuestion(){
		return this.Question;
	}
}
