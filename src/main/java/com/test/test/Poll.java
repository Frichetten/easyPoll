/**
 * Author: Kevin Dalle
 * Purpose: This is the class that will hold all of the poll data for an individual poll.
 */
package com.test.test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author kwdalle
 *
 */
public class Poll {
	private String pollName;
	private int pollNum;
	private String pollQuestion;
	private String isCurrent;
	private String closeDate;
	private String pollDescription;
	private int endTotal;
	private Date EndTime;
	private String PollType;
	private PollData pollData;
	private String pollPoster;
	private ArrayList<Tag> tags;
	
	public int getPollNum() {
		return pollNum;
	}

	public void setPollNum(int pollNum) {
		this.pollNum = pollNum;
	}
	
	public Poll(){	
	}
/*	
	public static int getTotalPoll() throws SQLException{
		String publicPollsQuery= "SELECT * FROM Polls;";
		//Statement st = dbc.createStatement();
		//ResultSet rs = st.executeQuery(publicPollsQuery);
		/*
		int toReturn = 0;
		while (rs.next()) {
			toReturn++;
		}
		return toReturn;
	}
*/
	public Poll(String pollName, int pollNum, String pollQuestion, String isCurrent, 
			String pollDescription, String PollType, PollData pollData, String pollPoster,
			ArrayList<Tag> tags){
		this.pollName = pollName;
		this.pollNum = pollNum;
		this.pollQuestion = pollQuestion;
		this.isCurrent = isCurrent;
		this.pollDescription = pollDescription;
		this.PollType = PollType;
		this.pollData = pollData;
		this.pollPoster = pollPoster;
		this.tags = tags;
	}
	
	public PollData getPollData(){
		
		return this.pollData;
	}
	
	public static int getTotalPoll() throws SQLException{
		return DBQuery.getTotalPolls();
	}
	
	public String getPollPoster() {
		return pollPoster;
	}
	public void setPollPoster(String pollPoster) {
		this.pollPoster = pollPoster;
	}
	public String getIsPublic() {
		return "";
	}
	public void setPub(String pub) {

	}

	public String getPollView() {
		return "";
	}
	public void setPollView(String pollView) {
		//this.pollView = pollView;
	}
	public String getAnswerType() {
		return "";
	}
	public void setAnswerType(String answerType) {
		//this.answerType = answerType;
	}
	public void setPollName(String pollName) {
	   this.pollName = pollName;
	}
	public String getPollName() {
	   return pollName;
	}
	public String getPollQuestion() {
		return pollQuestion;
	}
	public void setPollQuestion(String pollQuestion) {
		this.pollQuestion = pollQuestion;
	}
	public String getPollDescription() {
		return pollDescription;
	}
	public void setPollDescription(String pollDescription) {
		this.pollDescription = pollDescription;
	}
	
	
}
