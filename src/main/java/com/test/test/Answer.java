package com.test.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Answer {
	
	//Private class variables
	private boolean isRadio;
	private ArrayList<String> answerOptions = null;
	private ArrayList<Integer> answersChosen = null;
	private String answerparams;
	
	/*
	 * Default constructor. Spring MVC needs this DO NOT DELETE
	 */
	public Answer(){
		
	}
	
	/*
	 * Answer object constructor
	 */
	public Answer(ArrayList<String> options, ArrayList<Integer> chosen, boolean isRadio){
		this.answerOptions = options;
		this.answersChosen = chosen;
		this.isRadio = isRadio;
	}
	
	/*
	 * Will add the answer chosen
	 */
	public void addAnswerChosen(int index, int pollNum){
		this.answersChosen.set(index, this.answersChosen.get(index)+1);
		DBQuery.addAnswer(index+1, pollNum);
	}
	
	//Getters and setters don't need better comments
	public void setAnswer(String answer){
		this.answerparams = answer;
	}
	public String getAnswer(){
		return this.answerparams;
	}
	public boolean getIsRadio(){
		return this.isRadio;
	}
	public ArrayList<Integer> getAnswerChosen(){
		return this.answersChosen;
	}
	public ArrayList<String> getAnswerOptions(){
		return this.answerOptions;
	}
	public Answer getUserAnswer(int pollNum, String username){
		return DBQuery.getUserAnswer(pollNum, username);
	}
	public void setAnswerOptions(ArrayList<String> answerOptions){
		this.answerOptions = answerOptions;
	}
	public String getAnswerparams() {
		return answerparams;
	}
	public void setAnswerparams(String answerparams) {
		this.answerparams = answerparams;
	}
}
