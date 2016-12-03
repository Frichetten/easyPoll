package com.test.test;

import java.sql.SQLException;
import java.util.ArrayList;

public class PollTaker {

	//Private class variables
	private String username;
	private int pollNum;
	private boolean publicAnswers;
	private ArrayList<Integer> answers;
	
	//Constructor
	public PollTaker(String username, int pollNum, boolean publicAnswers, ArrayList<Integer> answers){
		this.username = username;
		this.pollNum = pollNum;
		this.publicAnswers = publicAnswers;
		this.answers = answers;
	}
	
	//Getters
	
	public Answer getUserAnswer(int pollNum, String username) throws SQLException{
		//Doesn't do anything but don't delete
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
