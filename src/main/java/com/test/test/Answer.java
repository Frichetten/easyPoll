package com.test.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Answer {
	
	//public String[] getAnswerOptions(String textQuestion) throws Exception;



	
	boolean isRadio;
	ArrayList<String> answerOptions = null;
	ArrayList<String> answersChosen = null;
	String answer = "";
	
	public Answer(){
	
	}
	public String getAnswer(){
		return this.answer;
	}
	public Answer(ArrayList<String> options, ArrayList<String> chosen){
		this.answerOptions = options;
		this.answersChosen = chosen;
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
