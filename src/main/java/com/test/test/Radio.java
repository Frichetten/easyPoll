package com.test.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.web.servlet.ModelAndView;

public class Radio implements Answer{

	String[] answerOptions = null;
	String[] answersChosen = null;
	
	public String[] getAnswerOptions(String textQuestion) throws SQLException{
		Connection Dbcon = DBConnection.getConnection();
		
		String SQL = "SELECT PollNum FROM PollData WHERE Question = " + textQuestion;
		Statement statement = Dbcon.createStatement();
		ResultSet rs = statement.executeQuery(SQL);
		
		if (rs.next()){
			System.out.println("PollNum: " + rs.getString(1));
			//If Authentication successful
			//Add these attributes to the model so they will appear
		}
		else {
			
		}
		
		
		return answerOptions;
	}
	
	
}
