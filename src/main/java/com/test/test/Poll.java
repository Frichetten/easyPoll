/**
 * Author: Kevin Dalle
 * Purpose: This is the class that will hold all of the poll data for an individual poll.
 */
package com.test.test;

/**
 * @author kwdalle
 *
 */
public class Poll {
	private String pollName;
	private String pollQuestion;
	private String answerType;
	private String pollView;
	private String answer;
	private String pub;
	
	public String getPub() {
		return pub;
	}
	public void setPub(String pub) {
		this.pub = pub;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getPollView() {
		return pollView;
	}
	public void setPollView(String pollView) {
		this.pollView = pollView;
	}
	public String getAnswerType() {
		return answerType;
	}
	public void setAnswerType(String answerType) {
		this.answerType = answerType;
	}
	public void setPollName(String pollName) {
	   this.pollName = pollName;
	}
	public String getPollName() {
	   return pollName;
	}
	public String getPollQuestion() {
		return pollQuestion;
	}
	public void setPollQuestion(String pollQuestion) {
		this.pollQuestion = pollQuestion;
	}
	
	
}
