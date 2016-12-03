package com.test.test;

import java.sql.SQLException;
import java.util.ArrayList;

public class PollData {
	
	//Private class variables
	private Answer totAnswers;
	private ArrayList<PollTaker> polltakers;
	private int params;
	
	//Constructor
	PollData(Answer answer, ArrayList<PollTaker> polltakers, int params){
		this.totAnswers = answer;
		this.polltakers = polltakers;
		this.params = params;
	}
	
	/*
	 * Adds to the poll takers
	 */
	public void addPollTaker(String username,int pollNum,boolean publicAnswers,ArrayList<Integer> answers){
		polltakers.add(new PollTaker(username, pollNum, publicAnswers, answers));
		DBQuery.addPollTaker(polltakers.get(polltakers.size()-1));
	}
	
	//Getters 
	
	public Answer getAnswer(){
		return this.totAnswers;
	}
	public int getParams(){
		return this.params;
	}
	public ArrayList<PollTaker> getPollTakers(){
		return this.polltakers;
	}
}
