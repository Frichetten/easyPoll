package com.test.test;

import java.sql.SQLException;
import java.util.ArrayList;

public class PollTaker {

	private String username;
	private int pollNum;
	private boolean publicAnswers;
	private ArrayList<Integer> answers;
	
	public PollTaker(String username, int pollNum, boolean publicAnswers, ArrayList<Integer> answers){
		this.username = username;
		this.pollNum = pollNum;
		this.publicAnswers = publicAnswers;
		this.answers = answers;
	}
	public Answer getUserAnswer(int pollNum, String username) throws SQLException{
		
		//return userAnswer.getUserAnswer(pollNum, username);
		return null;
	}
	
	public int getPollNum(){
		return this.pollNum;
	}
	
	public boolean getPublicAnswers(){
		return this.publicAnswers;
	}
	
	public ArrayList<Integer> getUserAnswers(){
		return this.answers;
	}
	
	public String getUsername(){
		return this.username;
	}
}
