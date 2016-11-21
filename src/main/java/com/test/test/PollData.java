package com.test.test;

import java.util.ArrayList;

public class PollData {
	
	private Answer cumAnswers;
	private ArrayList<PollTaker> polltakers;
	
	PollData(Answer answer, ArrayList<PollTaker> polltakers){
		this.cumAnswers = answer;
		this.polltakers = polltakers;
	}
	
	public Answer getCumAnswer(){
		
		return null;
	}
}
