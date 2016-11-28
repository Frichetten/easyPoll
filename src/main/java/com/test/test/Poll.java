/**
 * Author: Kevin Dalle
 * Purpose: This is the class that will hold all of the poll data for an individual poll.
 */
package com.test.test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Poll {
	private String pollName;
	private int pollNum;
	private String pollQuestion;
	private String isCurrent;
	private Date closeDate;
	private String pollDescription;
	private int endTotal;
	private String pollType;
	private int partakers;
	private PollData pollData;
	private String pollPoster;
	private ArrayList<Tag> tags;
	private String answerParams;
	
	public String getAnswerParams() {
		return answerParams;
	}

	public void setAnswerParams(String answerParams) {
		this.answerParams = answerParams;
	}

	public int getPollNum() {
		return pollNum;
	}

	public void setPollNum(int pollNum) {
		this.pollNum = pollNum;
	}
	
	public Poll(){
	}
/*	
	public static int getTotalPoll() throws SQLException{
		String publicPollsQuery= "SELECT * FROM Polls;";
		//Statement st = dbc.createStatement();
		//ResultSet rs = st.executeQuery(publicPollsQuery);
		/*
		int toReturn = 0;
		while (rs.next()) {
			toReturn++;
		}
		return toReturn;
	}
	
*/
	public static void updatePoll(int pollNum, String pollName, String pollQuestion, 
			String pollDescription, String pollType, int endTotal) {
		DBQuery.updatePoll(pollNum, pollName, pollQuestion, pollDescription, pollType, endTotal);
	}
	
	public static void deletePoll(int pollNum){
		DBQuery.deletePoll(pollNum);
	}
	
	public static void cancelPoll(int pollNum){
		DBQuery.cancelPoll(pollNum);
	}
	
	public static Poll getPollOfTheDay(){
		return DBQuery.getPollOfTheDay();
	}
	
	public static int pollTakerCount(int pollNum){
		return DBQuery.pollTakerCount(pollNum);
	}
	
	public static int endTotalCount(int pollNum){
		return DBQuery.endTotalCount(pollNum);
	}
	
	public Poll(int Pollnum) throws SQLException{
		Poll poll = DBQuery.getPoll(Pollnum);
		this.pollName = poll.getPollName();
		this.pollNum = poll.getPollNum();
		this.pollQuestion = poll.getPollQuestion();
		this.isCurrent = poll.getIsCurrent();
		this.closeDate = poll.getCloseDate();
		this.pollDescription = poll.getPollDescription();
		this.endTotal = poll.getEndTotal();
		this.pollType = poll.getPollType();
		this.pollData = poll.getPollData();
		this.pollPoster = poll.getPollPoster();
		this.tags = poll.getTags();
		this.partakers = poll.getPartakers();
	}
	
	public static void checkCurrent(int pollNum){
		DBQuery.checkCurrent(pollNum);
	}
	
	public Poll(String pollName, int pollNum, String pollQuestion, String isCurrent, Date closeDate, 
			String pollDescription, int endTotal, String PollType, PollData pollData, String pollPoster,
			ArrayList<Tag> tags, int partakers){
		this.pollName = pollName;
		this.pollNum = pollNum;
		this.pollQuestion = pollQuestion;
		this.isCurrent = isCurrent;
		this.closeDate = closeDate;
		this.pollDescription = pollDescription;
		this.endTotal = endTotal;
		this.pollType = PollType;
		this.pollData = pollData;
		this.pollPoster = pollPoster;
		this.tags = tags;
		this.partakers = partakers;
	}
	
	public static ArrayList<Poll> getYourPrivatePolls(String username){
		return DBQuery.getYourPrivatePolls(username);
	}
	
	public PollData getPollData(){
		
		return this.pollData;
	}
	public int getPartakers(){
		return this.partakers;
	}
	public void addPartaker() throws SQLException{
		this.partakers++;
		DBQuery.addPartaker(this.pollNum);
	}
	public String getIsCurrent(){
		return this.isCurrent;
	}
	
	public static Boolean isCurrent(int pollNum){
		return DBQuery.isCurrent(pollNum);
	}
	
	public Date getCloseDate(){
		return this.closeDate;
	}
	
	public int getEndTotal(){
		return this.endTotal;
	}
	public void setEndTotal(int endTotal){
		this.endTotal = endTotal;
	}
	public static int getTotalPoll() throws SQLException{
		return DBQuery.getTotalPolls();
	}
	
	public String getPollPoster() {
		return pollPoster;
	}
	public void setPollPoster(String pollPoster) {
		this.pollPoster = pollPoster;
	}
	public String getPollType() {
		return this.pollType;
	}
	public void setPub(String pub) {

	}
	public String getAnswerType() {
		return "";
	}
	public ArrayList<Tag> getTags(){
		return this.tags;
	}
	public void setAnswerType(String answerType) {
		//this.answerType = answerType;
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
	public String getPollDescription() {
		return pollDescription;
	}
	public void setPollDescription(String pollDescription) {
		this.pollDescription = pollDescription;
	}
	public void setPollType(String pollType) {
		this.pollType = pollType;
	}
	
	
}
