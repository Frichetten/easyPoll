package com.test.test;

import java.sql.SQLException;
import java.util.ArrayList;

public class PollData {
	
	private Answer totAnswers;
	private ArrayList<PollTaker> polltakers;
	private int params;
	
	PollData(Answer answer, ArrayList<PollTaker> polltakers, int params){
		this.totAnswers = answer;
		this.polltakers = polltakers;
		this.params = params;
	}
	
	public Answer getAnswer(){
		return this.totAnswers;
	}
	
	public void addPollTaker(String username,int pollNum,boolean publicAnswers,ArrayList<Integer> answers) throws SQLException{
		polltakers.add(new PollTaker(username, pollNum, publicAnswers, answers));
		DBQuery.addPollTaker(polltakers.get(polltakers.size()-1));
	}
	
	public int getParams(){
		return this.params;
	}
	
	public ArrayList<PollTaker> getPollTakers(){
		return this.polltakers;
	}
}
