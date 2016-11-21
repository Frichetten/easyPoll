package com.test.test;

import java.util.ArrayList;

public class PollData {
	
	private Answer totAnswers;
	private ArrayList<PollTaker> polltakers;
	
	PollData(Answer answer, ArrayList<PollTaker> polltakers){
		this.totAnswers = answer;
		this.polltakers = polltakers;
	}
	
	public Answer getAnswer(){
		
		return this.totAnswers;
	}
}
