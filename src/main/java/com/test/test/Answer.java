package com.test.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Answer {
	
	//public String[] getAnswerOptions(String textQuestion) throws Exception;
	private boolean isRadio;
	private ArrayList<String> answerOptions = null;
	private ArrayList<Integer> answersChosen = null;
	private String answer;
	
	public Answer(){
		
	}
	public void setAnswer(String answer){
		this.answer = answer;
	}
	public void addAnswerChosen(int index, int pollNum) throws SQLException{
		this.answersChosen.set(index, this.answersChosen.get(index)+1);
		DBQuery.addAnswer(index+1, pollNum);
	}
	public String getAnswer(){
		return this.answer;
	}
	public boolean getIsRadio(){
		return this.isRadio;
	}
	public Answer(ArrayList<String> options, ArrayList<Integer> chosen, boolean isRadio){
		this.answerOptions = options;
		this.answersChosen = chosen;
		this.isRadio = isRadio;
	}
	
	public ArrayList<Integer> getAnswerChosen(){
		return this.answersChosen;
	}
	
	public ArrayList<String> getAnswerOptions(){
		return this.answerOptions;
	}
	
	/*
	public String[] getAnswerOptions(int Pollnum, String username) throws SQLException{
		Connection Dbcon = DBConnection.getConnection();
		
		//String SQL = "SELECT PollNum FROM PollData WHERE Question = " + textQuestion;
		Statement statement = Dbcon.createStatement();
		ResultSet rs = statement.executeQuery(SQL);
		
		if (rs.next()){
			System.out.println("PollNum: " + rs.getString(1));
			//If Authentication successful
			//Add these attributes to the model so they will appear
		}
		else {
			
		}
		
		
		return null;
	}
	*/
	public Answer getUserAnswer(int pollNum, String username){
		return DBQuery.getUserAnswer(pollNum, username);
	}

}
