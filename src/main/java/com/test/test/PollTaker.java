package com.test.test;

import java.sql.SQLException;
import java.util.ArrayList;

public class PollTaker {

	String username;
	int pollNum;
	boolean publicAnswers;
	ArrayList<String> answers;
	
	public PollTaker(String username, int pollNum, boolean publicAnswers, ArrayList<String> answers){
		this.username = username;
		this.pollNum = pollNum;
		this.publicAnswers = publicAnswers;
		this.answers = answers;
	}
	public Answer getUserAnswer(int pollNum, String username) throws SQLException{
		
		//return userAnswer.getUserAnswer(pollNum, username);
		return null;
	}
}
