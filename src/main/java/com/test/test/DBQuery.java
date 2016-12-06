package com.test.test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

public class DBQuery{

	/*
	 * IMPORTANT NOTICE TO ALL PEOPLE. THIS CLASS BASICALLY ONLY CONTAINS
	 * COMMENTS AT THE TOP OF THE FUNCTION. BECAUSE ALL OF THESE FUNCTIONS 
	 * HAVE BEEN ENCAPSULATED SO THAT IT ONLY EXECUTES SQL STATEMENTS THERE
	 * REALLY ISNT ANY NEED FOR COMMENTS BECAUSE ITS JUST RUNNING THOSE QUERYS 
	 */
	
	//Private class variables
	static Connection dbc = DBConnection.getConnection();
	static Statement statement;
	static ResultSet rs;
	
	/*
	 * Logs the user in given a username and password. If successful, will return 
	 * a valid RUser object. Otherwise it will return null
	 */
	public static RUser Login(String email, String password){
		RUser tempUser = new RUser();
		try{
			String savedEmail = email;
			email = "'" + email + "'";
			password = "'" + password + "'";
			   
			//Select statement looking for a username
			String loginQuery = "SELECT Username FROM RUser WHERE Email = " + email + 
						" AND Pword = " + password + ";";
			statement = dbc.createStatement();
			rs = statement.executeQuery(loginQuery);
			if(rs.next()){
				System.out.println("User Logged in: " + rs.getString(1));
				tempUser.setUsername(rs.getString(1));
				tempUser.setEmail(savedEmail);
			}
			else
				tempUser.setUsername("");
			return tempUser;
		} catch (SQLException e){
			e.printStackTrace();
		}
		return tempUser;
	}
	
	/*
	 * This function will check if that username is already taken
	 */
	public static boolean checkUser(String username) {
		try{
			String loginQuery = "SELECT Username FROM RUser WHERE Username = '" + username + "';";
			Statement statement = dbc.createStatement();
			ResultSet rs = statement.executeQuery(loginQuery);
			if(rs.next()){
				return true;
			}
			else
				return false;
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return false;
	}
	
	/*
	 * This will actually insert the user into the DB
	 */
	public static void createRUser(String username, String password, String email){
		try{
			String insertQuery = "INSERT INTO RUser (Username, Pword, Email) Values (?,?,?);";
			PreparedStatement statement = dbc.prepareStatement(insertQuery);
			statement.setString(1, username);
			statement.setString(2, password);
			statement.setString(3, email);
			statement.execute();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	/*
	 * This poll will return a full poll object based on the passed in Pollnum
	 */
	public static Poll getPoll(int pollNumber) {
		try{
			String pollID = "'" + pollNumber + "'";
		
		   String PollQuery= "SELECT * FROM Polls p" +
				   " LEFT join PollData ON PollData.PollNum = p.PollNum" +
				   " LEFT JOIN PollTaker ON PollTaker.PollNum = PollData.PollNum" +
				   " LEFT JOIN PollTags ON PollTags.PollNum = p.PollNum" +
				   " where p.pollNum = " + pollID +
				   " ORDER BY p.pollNum, PollTaker.Username;";
		   statement = dbc.createStatement();
		   System.out.println(PollQuery);
		   ResultSet rs = statement.executeQuery(PollQuery);
		   System.out.println(PollQuery);
		   Poll singlePoll = new Poll();
		   
		   boolean next = rs.next();
		   if (next) {
			   int currentPollNum =Integer.parseInt(rs.getString(1));
			   String pollPoster = rs.getString(2);
			   String isCurrent = rs.getString(3);
			   String PollName = rs.getString(4);
			   int Partakers = Integer.parseInt(rs.getString(5));
			   String pollType = rs.getString(6);
			   int EndTotal;
			   if(rs.getString(7)!=null)
				   EndTotal = Integer.parseInt(rs.getString(7));
			   else
				   EndTotal = -1;
			   System.out.println(EndTotal);
			   Date EndDate;
			   if(rs.getString(8)!=null)
				   EndDate = Date.valueOf(rs.getString(8));
			   else
				   EndDate = null;
			   System.out.println(EndDate);
			   int Params = Integer.parseInt(rs.getString(10));
			   System.out.println(Params);
			   String Question = rs.getString(11);
			   System.out.println(Question);
			   String Description = rs.getString(12);
			   System.out.println(Description);
			   boolean isRadio = Boolean.parseBoolean(rs.getString(13));
			   boolean gotTags = false;
			   ArrayList<String> answers = new ArrayList<String>();
			   int i = 14;
			   while (i < 24 && rs.getString(i)!=null){
				   //System.out.println("DEBUG: ANSWERChoice " + (i-13)+ ": " +rs.getString(i));
				   answers.add(rs.getString(i));
				   i++;
			   }
			   ArrayList<Integer> totals = new ArrayList<Integer>();
			   i = 24;
			   while (i < 34 && rs.getString(i)!=null){
				   //System.out.println("DEBUG: TOTANSWER " + (i-23)+ ": "+rs.getString(i));
				   totals.add(Integer.parseInt(rs.getString(i)));
				   i++;
			   }
			   
			   ArrayList<Tag> tag = new ArrayList<Tag>();
			   ArrayList<PollTaker> pollers = new ArrayList<PollTaker>();
			   String poller = "";
			   while(next && currentPollNum == Integer.parseInt(rs.getString(1)) ){ 
				   int pollNum = 0;
				   String publicAnswer = null;
				   ArrayList<Integer> userans = new ArrayList<Integer>();
				   if(poller!=null && !poller.equals(rs.getString(34))){
					   poller = rs.getString(34);
					   if(poller==null){
						   poller = "";
					   }
					   if(rs.getString(35)==null)
						   pollNum = 0;
					   else
						   pollNum = Integer.parseInt(rs.getString(35));
					   
					   publicAnswer = rs.getString(36);
					   i = 37;
					   
					   while(i < 47 && rs.getString(i)!=null){
						   //System.out.println("DEBUG: USERANSWER " +(i-36) + ": " +rs.getString(i));
						   userans.add(Integer.parseInt(rs.getString(i)));
						   i++;
					   }
					   //System.out.println("DEBUG: PollTaker: " + poller);
					   pollers.add(new PollTaker(poller, pollNum, Boolean.parseBoolean(publicAnswer), userans));
				   }
				   
				   String PollTag = rs.getString(47);
				   //System.out.println("DEBUG: PollTag: " + PollTag);
				   if(PollTag!=null && !gotTags){
					   do{ 
							   //System.out.println("DEBUG: TAG: " + rs.getString(49));
							   tag.add(new Tag(rs.getString(49), Integer.parseInt(rs.getString(48)), Integer.parseInt(rs.getString(47))));
							   
							   try{
								   next = rs.next();
							   }
							   catch(SQLException se){
								   next = false;
							   }						  
							   
					   }
					   while(next && !rs.getString(47).equals(PollTag)  
							   && rs.getString(2).equals(pollPoster)
							   && Integer.parseInt(rs.getString(1))==(currentPollNum));
				   }
				   else{
					   System.out.println("before next");
					   try{
					   next = rs.next();
					   }
					   catch(SQLException se){
						   next = false;
					   }
					   System.out.println("after next");
				   }
				   gotTags = true;
			   }
			   Answer ans = new Answer(answers, totals, isRadio);
			   PollData polldata= new PollData(ans, pollers, Params);
			   singlePoll = new Poll(PollName, currentPollNum, Question, isCurrent, EndDate,
						Description, EndTotal,pollType,polldata, pollPoster,
						 tag, Partakers);

			   }
		return singlePoll;
		} catch (SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * This will return an arraylist of all polls. All of them
	 */
	public static ArrayList<Poll> getPolls() {
		try{ 
		   String publicPollsQuery= "SELECT * FROM Polls p"
		   	+ " LEFT JOIN PollData ON PollData.PollNum = p.PollNum" + 
		   " LEFT JOIN PollTaker ON PollTaker.PollNum = PollData.PollNum"+
		   " LEFT JOIN PollTags ON PollTags.PollNum = p.PollNum" +
		   " ORDER BY p.pollNum, PollTaker.Username, PollTags.TagNum;";
		   
		   statement = dbc.createStatement();
		   System.out.println(publicPollsQuery);
		   ResultSet rs = statement.executeQuery(publicPollsQuery);
		   System.out.println(publicPollsQuery);
		   ArrayList<Poll> publicPolls = new ArrayList<Poll>();
		   
		   boolean next = rs.next();
		   while (next) {
			   int currentPollNum =Integer.parseInt(rs.getString(1));
			   String pollPoster = rs.getString(2);
			   String isCurrent = rs.getString(3);
			   String PollName = rs.getString(4);
			   int Partakers = Integer.parseInt(rs.getString(5));
			   String pollType = rs.getString(6);
			   int EndTotal;
			   if(rs.getString(7)!=null)
				   EndTotal = Integer.parseInt(rs.getString(7));
			   else
				   EndTotal = -1;
			   System.out.println(EndTotal);
			   Date EndDate;
			   if(rs.getString(8)!=null)
				   EndDate = Date.valueOf(rs.getString(8));
			   else
				   EndDate = null;
			   System.out.println(EndDate);
			   int Params = Integer.parseInt(rs.getString(10));
			   System.out.println(Params);
			   String Question = rs.getString(11);
			   System.out.println(Question);
			   String Description = rs.getString(12);
			   System.out.println(Description);
			   boolean isRadio = Boolean.parseBoolean(rs.getString(13));
			   boolean gotTags = true;
			   ArrayList<String> answers = new ArrayList<String>();
			   int i = 14;
			   while (i < 24 && rs.getString(i)!=null){
				   //System.out.println("DEBUG: ANSWERChoice " + (i-13)+ ": " +rs.getString(i));
				   answers.add(rs.getString(i));
				   i++;
			   }
			   ArrayList<Integer> totals = new ArrayList<Integer>();
			   i = 24;
			   while (i < 34 && rs.getString(i)!=null){
				   //System.out.println("DEBUG: TOTANSWER " + (i-23)+ ": "+rs.getString(i));
				   totals.add(Integer.parseInt(rs.getString(i)));
				   i++;
			   }
			   
			   ArrayList<Tag> tag = new ArrayList<Tag>();
			   ArrayList<PollTaker> pollers = new ArrayList<PollTaker>();
			   String poller = "";
			   while(next && currentPollNum == Integer.parseInt(rs.getString(1)) ){ 
				   int pollNum = 0;
				   String publicAnswer = null;
				   ArrayList<Integer> userans = new ArrayList<Integer>();
				   if(poller!=null && !poller.equals(rs.getString(34))){
					   poller = rs.getString(34);
					   if(poller==null){
						   poller = "";
					   }
					   if(rs.getString(35)==null)
						   pollNum = 0;
					   else
						   pollNum = Integer.parseInt(rs.getString(35));
					   
					   publicAnswer = rs.getString(36);
					   i = 37;
					   
					   while(i < 47 && rs.getString(i)!=null){
						   //System.out.println("DEBUG: USERANSWER " +(i-36) + ": " +rs.getString(i));
						   userans.add(Integer.parseInt(rs.getString(i)));
						   i++;
					   }
					   //System.out.println("DEBUG: PollTaker: " + poller);
					   pollers.add(new PollTaker(poller, pollNum, Boolean.parseBoolean(publicAnswer), userans));
				   }
				   
				   String PollTag = rs.getString(47);
				   //System.out.println("DEBUG: PollTag: " + PollTag);
				   if(PollTag!=null && !gotTags){
					   do{ 
							   //System.out.println("DEBUG: TAG: " + rs.getString(49));
							   tag.add(new Tag(rs.getString(49), Integer.parseInt(rs.getString(48)), Integer.parseInt(rs.getString(47))));
							   
							   try{
								   next = rs.next();
							   }
							   catch(SQLException se){
								   next = false;
							   }
							   
					   }
					   while(next && !rs.getString(47).equals(PollTag) 
							   && rs.getString(2).equals(pollPoster)
							   && Integer.parseInt(rs.getString(1))==(currentPollNum));
				   }
				   else{
					   System.out.println("before next");
					   try{
					   next = rs.next();
					   }
					   catch(SQLException se){
						   next = false;
					   }
					   System.out.println("after next");
				   }
				   gotTags = true;
			   }
			   Answer ans = new Answer(answers, totals, isRadio);
			   PollData polldata= new PollData(ans, pollers, Params);
			   publicPolls.add(new Poll(PollName, currentPollNum, Question, isCurrent, EndDate,
						Description, EndTotal,pollType,polldata, pollPoster,
						 tag, Partakers));
			   }
		   return publicPolls;
		   
		} catch (SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * This will return an arraylist of all public polls
	 */
	public static ArrayList<Poll> getPublicPolls(){
		try{
		   String publicPollsQuery= "SELECT * FROM Polls p"
		   	+ " LEFT JOIN PollData ON PollData.PollNum = p.PollNum" + 
		   " LEFT JOIN PollTaker ON PollTaker.PollNum = PollData.PollNum"+
		   " LEFT JOIN PollTags ON PollTags.PollNum = p.PollNum" +
		   " WHERE p.PollType = 'public' " +
		   " ORDER BY p.pollNum, PollTaker.Username, PollTags.TagNum;";
		   
		   statement = dbc.createStatement();
		   System.out.println(publicPollsQuery);
		   ResultSet rs = statement.executeQuery(publicPollsQuery);
		   System.out.println(publicPollsQuery);
		   ArrayList<Poll> publicPolls = new ArrayList<Poll>();
		   
		   boolean next = rs.next();
		   while (next) {
			   int currentPollNum =Integer.parseInt(rs.getString(1));
			   String pollPoster = rs.getString(2);
			   String isCurrent = rs.getString(3);
			   String PollName = rs.getString(4);
			   int Partakers = Integer.parseInt(rs.getString(5));
			   String pollType = rs.getString(6);
			   int EndTotal;
			   if(rs.getString(7)!=null)
				   EndTotal = Integer.parseInt(rs.getString(7));
			   else
				   EndTotal = -1;
			   System.out.println(EndTotal);
			   Date EndDate;
			   if(rs.getString(8)!=null)
				   EndDate = Date.valueOf(rs.getString(8));
			   else
				   EndDate = null;
			   System.out.println(EndDate);
			   int Params = Integer.parseInt(rs.getString(10));
			   System.out.println(Params);
			   String Question = rs.getString(11);
			   System.out.println(Question);
			   String Description = rs.getString(12);
			   System.out.println(Description);
			   boolean isRadio = Boolean.parseBoolean(rs.getString(13));
			   boolean gotTags = true;
			   ArrayList<String> answers = new ArrayList<String>();
			   int i = 14;
			   while (i < 24 && rs.getString(i)!=null){
				   //System.out.println("DEBUG: ANSWERChoice " + (i-13)+ ": " +rs.getString(i));
				   answers.add(rs.getString(i));
				   i++;
			   }
			   ArrayList<Integer> totals = new ArrayList<Integer>();
			   i = 24;
			   while (i < 34 && rs.getString(i)!=null){
				   //System.out.println("DEBUG: TOTANSWER " + (i-23)+ ": "+rs.getString(i));
				   totals.add(Integer.parseInt(rs.getString(i)));
				   i++;
			   }
			   
			   ArrayList<Tag> tag = new ArrayList<Tag>();
			   ArrayList<PollTaker> pollers = new ArrayList<PollTaker>();
			   String poller = "";
			   while(next && currentPollNum == Integer.parseInt(rs.getString(1)) ){ 
				   int pollNum = 0;
				   String publicAnswer = null;
				   ArrayList<Integer> userans = new ArrayList<Integer>();
				   if(poller!=null && !poller.equals(rs.getString(34))){
					   poller = rs.getString(34);
					   if(poller==null){
						   poller = "";
					   }
					   if(rs.getString(35)==null)
						   pollNum = 0;
					   else
						   pollNum = Integer.parseInt(rs.getString(35));
					   
					   publicAnswer = rs.getString(36);
					   i = 37;
					   
					   while(i < 47 && rs.getString(i)!=null){
						   //System.out.println("DEBUG: USERANSWER " +(i-36) + ": " +rs.getString(i));
						   userans.add(Integer.parseInt(rs.getString(i)));
						   i++;
					   }
					   System.out.println("DEBUG: PollTaker: " + poller);
					   pollers.add(new PollTaker(poller, pollNum, Boolean.parseBoolean(publicAnswer), userans));
				   }
				   
				   String PollTag = rs.getString(47);
				   //System.out.println("DEBUG: PollTag: " + PollTag);
				   if(PollTag!=null && !gotTags){
					   do{ 
							   //System.out.println("DEBUG: TAG: " + rs.getString(49));
							   tag.add(new Tag(rs.getString(49), Integer.parseInt(rs.getString(48)), Integer.parseInt(rs.getString(47))));
							   
							   try{
								   next = rs.next();
							   }
							   catch(SQLException se){
								   next = false;
							   }
							   
					   }
					   while(next &&!rs.getString(47).equals(PollTag) 
							   && rs.getString(2).equals(pollPoster)
							   && Integer.parseInt(rs.getString(1))==(currentPollNum));
				   }
				   else{
					   System.out.println("before next");
					   try{
					   next = rs.next();
					   }
					   catch(SQLException se){
						   next = false;
					   }
					   System.out.println("after next");
				   }
				   gotTags = true;
			   }
			   Answer ans = new Answer(answers, totals, isRadio);
			   PollData polldata= new PollData(ans, pollers, Params);
			   publicPolls.add(new Poll(PollName, currentPollNum, Question, isCurrent, EndDate,
						Description, EndTotal,pollType,polldata, pollPoster,
						 tag, Partakers));
			   }
		   return publicPolls;
		} catch (SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * Given a pollnum it will return the number of people who have taken that poll
	 */
	public static int pollTakerCount(int pollNum){
		int toReturn = 0;
		try {
			String searchQuery = "SELECT * FROM PollTaker WHERE PollNum = ?;";
			PreparedStatement statement = dbc.prepareStatement(searchQuery);
			statement.setInt(1, pollNum);
			ResultSet rs = statement.executeQuery();
			while (rs.next()){
				toReturn = toReturn + 1;
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return toReturn;
	}
	
	/*
	 * Given a pollnum will return the end total count needed for the poll to roll 
	 * over from current to not current.
	 */
	public static int endTotalCount(int pollNum){
		int toReturn = 0;
		try {
			String searchQuery = "SELECT EndTotal FROM Polls WHERE PollNum = ?;";
			PreparedStatement statement = dbc.prepareStatement(searchQuery);
			statement.setInt(1, pollNum);
			ResultSet rs = statement.executeQuery();
			if(rs.next()){
				toReturn = rs.getInt(1);
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return toReturn;
	}
	
	/*
	 * This function is used for the newsletter and will return an arraylist 
	 * that contains the email addresses of every user. Right now, we have set it so 
	 * that it will only send it to me. I don't want to risk the gmail account being 
	 * flagged as a spam account and it getting shut down. When we present I can 
	 * change it so that it will send out a mass mail
	 */
	public static ArrayList<String> getAllEmails(){
		ArrayList<String> toReturn = new ArrayList<String>();
		//This is the query that will change
		String emailQuery = "SELECT Email FROM RUser WHERE Username = 'Nick';";
		try {
			statement = dbc.createStatement();
			ResultSet rs = statement.executeQuery(emailQuery);
			while(rs.next()){
				toReturn.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return toReturn;
	}
	
	/*
	 * This will return the number of polls that currently exist on the platform. This 
	 * is shown the the user when they create a poll or edit it.
	 */
	public static int getTotalPolls() {
		try{
			String totalPolls = "SELECT COUNT(pollNum) from Polls;";
			statement = dbc.createStatement();
			ResultSet rs = statement.executeQuery(totalPolls);
			if(rs.next()){
				return Integer.parseInt(rs.getString(1));
			}
			return 0;
		} catch (SQLException e){
			e.printStackTrace();
		}
		return 0;
	}
	
	/*
	 * Based on a username, will return an arraylist of every poll that user has
	 * created.
	 */
	public static ArrayList<Poll> getMyPolls(String username){
		try{
		   username = "'" + username + "'";
		   String PollsQuery= "SELECT * FROM Polls p" +
				   " LEFT join PollData ON PollData.PollNum = p.PollNum" +
				   " LEFT JOIN PollTaker ON PollTaker.PollNum = PollData.PollNum" +
				   " LEFT JOIN PollTags ON PollTags.PollNum = p.PollNum" +
				   " where p.Username = " + username +
				   " ORDER BY p.pollNum, PollTaker.Username;";
		   ResultSet rs = statement.executeQuery(PollsQuery);
		   ArrayList<Poll> myPolls = new ArrayList<Poll>();

		   
		   boolean next = rs.next();
		   while (next) {
			   int currentPollNum =Integer.parseInt(rs.getString(1));
			   String pollPoster = rs.getString(2);
			   String isCurrent = rs.getString(3);
			   String PollName = rs.getString(4);
			   int Partakers = Integer.parseInt(rs.getString(5));
			   String pollType = rs.getString(6);
			   int EndTotal;
			   if(rs.getString(7)!=null)
				   EndTotal = Integer.parseInt(rs.getString(7));
			   else
				   EndTotal = -1;
			   System.out.println(EndTotal);
			   Date EndDate;
			   if(rs.getString(8)!=null)
				   EndDate = Date.valueOf(rs.getString(8));
			   else
				   EndDate = null;
			   System.out.println(EndDate);
			   int Params = Integer.parseInt(rs.getString(10));
			   System.out.println(Params);
			   String Question = rs.getString(11);
			   System.out.println(Question);
			   String Description = rs.getString(12);
			   System.out.println(Description);
			   boolean isRadio = Boolean.parseBoolean(rs.getString(13));
			   boolean gotTags = false;
			   ArrayList<String> answers = new ArrayList<String>();
			   int i = 14;
			   while (i < 24 && rs.getString(i)!=null){
				   //System.out.println("DEBUG: ANSWERChoice " + (i-13)+ ": " +rs.getString(i));
				   answers.add(rs.getString(i));
				   i++;
			   }
			   ArrayList<Integer> totals = new ArrayList<Integer>();
			   i = 24;
			   while (i < 34 && rs.getString(i)!=null){
				   //System.out.println("DEBUG: TOTANSWER " + (i-23)+ ": "+rs.getString(i));
				   totals.add(Integer.parseInt(rs.getString(i)));
				   i++;
			   }
			   
			   ArrayList<Tag> tag = new ArrayList<Tag>();
			   ArrayList<PollTaker> pollers = new ArrayList<PollTaker>();
			   String poller = "";
			   while(next && currentPollNum == Integer.parseInt(rs.getString(1)) ){ 
				   int pollNum = 0;
				   String publicAnswer = null;
				   ArrayList<Integer> userans = new ArrayList<Integer>();
				   if(poller!=null && !poller.equals(rs.getString(34))){
					   poller = rs.getString(34);
					   if(poller==null){
						   poller = "";
					   }
					   if(rs.getString(35)==null)
						   pollNum = 0;
					   else
						   pollNum = Integer.parseInt(rs.getString(35));
					   
					   publicAnswer = rs.getString(36);
					   i = 37;
					   
					   while(i < 47 && rs.getString(i)!=null){
						   //System.out.println("DEBUG: USERANSWER " +(i-36) + ": " +rs.getString(i));
						   userans.add(Integer.parseInt(rs.getString(i)));
						   i++;
					   }
					   System.out.println("DEBUG: PollTaker: " + poller);
					   pollers.add(new PollTaker(poller, pollNum, Boolean.parseBoolean(publicAnswer), userans));
				   }
				   
				   String PollTag = rs.getString(47);
				   //System.out.println("DEBUG: PollTag: " + PollTag);
				   if(PollTag!=null && !gotTags){
					   do{ 
							   //System.out.println("DEBUG: TAG: " + rs.getString(49));
							   tag.add(new Tag(rs.getString(49), Integer.parseInt(rs.getString(48)), Integer.parseInt(rs.getString(47))));
							   
							   try{
								   next = rs.next();
							   }
							   catch(SQLException se){
								   next = false;
							   }
							   
					   }
					   while(next
							   && !rs.getString(47).equals(PollTag) 
							   && rs.getString(2).equals(pollPoster)
							   && Integer.parseInt(rs.getString(1))==(currentPollNum));
				   }
				   else{
					   System.out.println("before next");
					   try{
					   next = rs.next();
					   }
					   catch(SQLException se){
						   next = false;
					   }
					   System.out.println("after next");
				   }
				   gotTags = true;
			   }
			   Answer ans = new Answer(answers, totals, isRadio);
			   PollData polldata= new PollData(ans, pollers, Params);
			   myPolls.add(new Poll(PollName, currentPollNum, Question, isCurrent, EndDate,
						Description, EndTotal,pollType,polldata, pollPoster,
						 tag, Partakers));
			   }
		   return myPolls;
		} catch (SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * Will return the number of polls created by the user
	 */
	public static int getMyPollsCount(String username){
		int toReturn = 0;
		try{
			String searchQuery = "SELECT COUNT(*) FROM Polls WHERE Username = ?;";
			PreparedStatement statement = dbc.prepareStatement(searchQuery);
			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();
			if(rs.next()){
				toReturn = rs.getInt(1);
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return toReturn;
	}
	
	/*
	 * Returns the result set will all of the information on a poll.
	 */
	public static ResultSet resultSetPoll(String pollNum){
		ResultSet failure = null;
		try {
			String searchQuery = "SELECT * FROM Polls p JOIN PollData on PollData.PollNum = p.PollNum WHERE p.PollNum = ?;";
			PreparedStatement statement = dbc.prepareStatement(searchQuery);
			statement.setInt(1, Integer.parseInt(pollNum));
			ResultSet rs = statement.executeQuery();
			return rs;
		} catch (SQLException e){
			e.printStackTrace();
		}
		return failure;
	}
	
	/*
	 * This will return the number of polls the user has voted in
	 */
	public static int getMyPollsVoted(String username){
		int toReturn = 0;
		try{ 
			String searchQuery = "SELECT COUNT(*) FROM PollTaker WHERE Username = ?;";
			PreparedStatement statement = dbc.prepareStatement(searchQuery);
			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();
			if(rs.next()){
				toReturn = rs.getInt(1);
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return toReturn;
	}
	
	/*
	 * Will return the name of the poll that the userr created that has 
	 * the most votes
	 */
	public static String getMyPollsMostVoted(String username){
		String toReturn = "";
		try{
			String searchQuery = "SELECT PollName, MAX(Partakers) FROM Polls WHERE Username = ? GROUP BY PollName desc;";
			PreparedStatement statement = dbc.prepareStatement(searchQuery);
			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();
			if(rs.next()){
				toReturn = rs.getString(1);
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return toReturn;
	}
	
	/*
	 * This function will return an Admin object based on a passed in 
	 * admin username
	 */
	public static Administrator getAdmin(String username) {
		try{
			String user = "'" + username + "'";
			String adminQuery = "SELECT * FROM AdminUser WHERE Username = " + user + ";";
			Statement statement = dbc.createStatement();
			System.out.println(adminQuery);
			rs = statement.executeQuery(adminQuery);
			
			String email = "";
			ArrayList<ReportedQuestion> rq = new ArrayList<ReportedQuestion>();
			
			if(rs.next()){
				email = rs.getString(2);
			}
			
			String reportedQuestionsQuery = "SELECT * FROM ReportedQuestions;";
			Statement statement2 = dbc.createStatement();
			rs = statement2.executeQuery(reportedQuestionsQuery);
			
			while(rs.next()){
				int PollNum = Integer.parseInt(rs.getString(1));
				String reporter = rs.getString(2);
				String Question = rs.getString(3);
				String description = rs.getString(4);
				String pollName = rs.getString(5);
				rq.add(new ReportedQuestion(PollNum, reporter, Question, description, pollName));
			}
			
			return new Administrator(username, email, rq);
		} catch (SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * Returns true or false depending on whether or not the poll is current.
	 */
	public static Boolean isCurrent(int pollNum){
		try{
			String searchQuery = "SELECT isCurrent FROM Polls WHERE PollNum = ?;";
			PreparedStatement statement = dbc.prepareStatement(searchQuery);
			statement.setInt(1, pollNum);
			ResultSet rs = statement.executeQuery();
			if (rs.next()){
				String check = rs.getString(1);
				if (check.equals("1"))
					return true;
				else
					return false;
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Based on a pollId, will return the description of that poll
	 * @param pollId
	 * @return
	 */
	public static String getPollDescription(String pollId) {
		try{
			String toReturn = "";
			String searchQuery = "SELECT Description FROM PollData WHERE PollNum= '"+pollId+"';";
			statement = dbc.createStatement();
			rs = statement.executeQuery(searchQuery);
			if (rs.next()){
				toReturn = rs.getString(1);
			}
			else
				toReturn = "null";
			return toReturn;
		} catch (SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * Dunno
	 */
	public static Answer getUserAnswer(int pollNum, String username){
		return null;
	}
	
	/*
	 * This function will add an answer to a poll in the DB given the index of the answer
	 * to increment as well as the pollId
	 */
	public static void addAnswer(int index, int pollNum)  {
		try{
			String column = "";
			switch(index){
			
			case 1: column = "TotalOne";
					break;
			case 2: column = "TotalTwo";
					break;
			case 3: column = "TotalThree";
					break;
			case 4: column = "TotalFour";
					break;
			case 5: column = "TotalFive";
					break;
			case 6: column = "TotalSix";
					break;
			case 7: column = "TotalSeven";
					break;
			case 8: column = "TotalEight";
					break;
			case 9: column = "TotalNine";
					break;
			case 10:column = "TotalTen";
					break;
			}
			
			
			String addAnswerQuery = "UPDATE Polls p JOIN PollData on PollData.PollNum = p.PollNum" +
									" SET " + column + " = " + column + " +1" +
									" where p.PollNum = " + pollNum + ";";
			
			statement = dbc.createStatement();
			statement.executeUpdate(addAnswerQuery);
		} catch (SQLException e){
			e.printStackTrace();
		}
	}

	/*
	 * This function will increment the number of partakers on a poll
	 */
	public static void addPartaker(int pollNum) {
		try{
			String updatePartakersQuery = "Update Polls Set Partakers=Partakers+1 WHERE PollNum = " + pollNum + " ;";
			statement.executeUpdate(updatePartakersQuery);
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	/*
	 * Deletes the account and wipes all information about them. Including the polls they
	 * have created and the polls they have voted in and the groups they were a part of.
	 */
	public static void deleteAccount(String username) {
		try {
			//Need to delete all of this users polls
			//First get all of the pollNums for them.
			String searchQuery = "SELECT PollNum FROM Polls WHERE Username = ?;";
			PreparedStatement searchStatement = dbc.prepareStatement(searchQuery);
			searchStatement.setString(1, username);
			ResultSet rs = searchStatement.executeQuery();
			ArrayList<String> pollNums = new ArrayList<String>();
			while (rs.next()){
				pollNums.add(rs.getString(1));
			}
			//Delete all associated Usergroups first by getting the groupNums from poll group
			String pollGroupHunt = "SELECT GroupNum FROM PollGroup WHERE AdminUsername = ?;";
			PreparedStatement sss = dbc.prepareStatement(pollGroupHunt);
			sss.setString(1, username);
			ArrayList<String> groupNums = new ArrayList<String>();
			ResultSet rsa = sss.executeQuery();
			while (rsa.next()){
				groupNums.add(rsa.getString(1));
			}
			for(int i=0; i< groupNums.size(); i++){
				String deleteUserGroup = "DELETE FROM UserGroup WHERE GroupNum = ?;";
				PreparedStatement skl = dbc.prepareStatement(deleteUserGroup);
				skl.setInt(1, Integer.parseInt(groupNums.get(i)));
				skl.execute();
			}
			//Delete PollGroups
			for(int i=0; i< groupNums.size(); i++){
				String deleteUserGroup = "DELETE FROM PollGroup WHERE GroupNum = ?;";
				PreparedStatement skl = dbc.prepareStatement(deleteUserGroup);
				skl.setInt(1, Integer.parseInt(groupNums.get(i)));
				skl.execute();
			}
			//Delete all pollTakers 
			for(int i = 0; i < pollNums.size(); i++){
				String dq1 = "DELETE FROM PollTaker WHERE PollNum = ?;";
				PreparedStatement ds1 = dbc.prepareStatement(dq1);
				ds1.setInt(1,Integer.parseInt(pollNums.get(i)));
				ds1.execute();
			}
			//Delete all PollData
			for(int i=0; i < pollNums.size(); i++){
				String dq1 = "DELETE FROM PollData WHERE PollNum = ?;";
				PreparedStatement ds1 = dbc.prepareStatement(dq1);
				ds1.setInt(1,Integer.parseInt(pollNums.get(i)));
				ds1.execute();
			}
			//Delete From Polls
			for(int i=0; i < pollNums.size(); i++){
				String dq1 = "DELETE FROM Polls WHERE PollNum = ?;";
				PreparedStatement ds1 = dbc.prepareStatement(dq1);
				ds1.setInt(1,Integer.parseInt(pollNums.get(i)));
				ds1.execute();
			}
			String deleteQuery = "DELETE FROM RUser WHERE Username = ?;";
			PreparedStatement statement;
			statement = dbc.prepareStatement(deleteQuery);
			statement.setString(1, username);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Will update an account with all of the valued information
	 */
	public static void updateAccount(String queryName, String username, String email, String password){
		if (password.equals("")){
			try {
				String updateQuery = "UPDATE RUser SET Email = ? WHERE RUser.Username = ?;";
				PreparedStatement statement = dbc.prepareStatement(updateQuery);
				statement.setString(1, email);
				statement.setString(2, queryName);
				statement.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				String updateQuery = "UPDATE RUser SET Email = ?, Pword = ? WHERE RUser.Username = ?;";
				PreparedStatement statement = dbc.prepareStatement(updateQuery);
				statement.setString(1, email);
				statement.setString(2, password);
				statement.setString(3, queryName);
				statement.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * Modifys the poll so that it is no longer current.
	 */
	public static void cancelPoll(int pollNum){
		try{
			String updateQuery = "UPDATE Polls SET isCurrent = 0 WHERE Polls.PollNum = ?;";
			PreparedStatement statement = dbc.prepareStatement(updateQuery);
			statement.setInt(1, pollNum);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Inserts the message into the feedback table so that the admins can see it later
	 */
	public static void sendFeedback(String textarea){
		try{
			String insertQuery = "INSERT INTO Feedback (Message) VALUES (?);";
			PreparedStatement statement = dbc.prepareStatement(insertQuery);
			statement.setString(1, textarea);
			statement.execute();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * This will return an arraylist of strings that hold every single 
	 * entry in the feedback table
	 * @return
	 */
	public static ArrayList<String> getFeedback(){
		ArrayList<String> toReturn = new ArrayList<String>();
		try{
			String searchQuery = "SELECT Message FROM Feedback;";
			PreparedStatement statement = dbc.prepareStatement(searchQuery);
			ResultSet rs = statement.executeQuery();
			while (rs.next()){
				toReturn.add(rs.getString(1));
			}
		} catch (SQLException e){
			e.printStackTrace();;
		}
		return toReturn;
	}
	
	/*
	 * Inserts the support ticket into the DB
	 */
	public static void sendSupportTicket(String textarea, String username){
		try{
			String insertQuery = "INSERT INTO SupportTicket (Message, TicketUsername) VALUES (?,?);";
			PreparedStatement statement = dbc.prepareStatement(insertQuery);
			statement.setString(1,textarea);
			statement.setString(2, username);
			statement.execute();
		} catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	/*
	 * Will return an arraylist with all of the information on the support tickets
	 */
	public static ArrayList<Administrator> getSupportTickets(){
		ArrayList<Administrator> toReturn = new ArrayList<Administrator>();
		try{
			String searchQuery = "SELECT TicketNum, Message, TicketUsername FROM SupportTicket;";
			PreparedStatement statement = dbc.prepareStatement(searchQuery);
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				Administrator admin = new Administrator();
				admin.setUsername(rs.getString(3));
				admin.setPassword(rs.getString(1));
				admin.setEmail(rs.getString(2));
				toReturn.add(admin);
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return toReturn;
	}
	
	/*
	 * This function will modify the poll with the infromation that is pased in
	 */
	public static void updatePoll(int pollNum, String pollName, String pollQuestion, 
			String pollDescription, String pollType, int endTotal) {
		try {
			String updateQuery = "UPDATE Polls JOIN PollData ON Polls.PollNum = PollData.PollNum AND Polls.PollNum = ? " +
					"SET Polls.PollName = ?, Polls.PollType = ?, PollData.Question = ?, PollData.Description = ?, Polls.EndTotal = ?;";
			PreparedStatement statement = dbc.prepareStatement(updateQuery);
			statement.setInt(1, pollNum);
			statement.setString(2, pollName);
			statement.setString(3, pollType);
			statement.setString(4, pollQuestion);
			statement.setString(5, pollDescription);
			statement.setInt(6,  endTotal);
			System.out.println("HECKIN: " + statement.executeUpdate());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Will return the poll that is public, current, and holds the most votes. Does 
	 * NOT change on a date change
	 * @return
	 */
	public static Poll getPollOfTheDay(){
		Poll toReturn = null;
		try{
			String searchQuery = "SELECT * FROM Polls WHERE PollType='public' ORDER BY Partakers desc;";
			PreparedStatement statement = dbc.prepareStatement(searchQuery);
			ResultSet rs = statement.executeQuery();
			if (rs.next()){
				toReturn = new Poll(rs.getInt(1));
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return toReturn;
	}
	
	/**
	 * Will return all active public polls
	 * @return
	 */
	public static ArrayList<Integer> getActivePublicPolls(){
		ArrayList<Integer> toReturn = new ArrayList<Integer>();
		try {
			String searchQuery = "SELECT PollNum FROM Polls WHERE PollType='public' and isCurrent = 1;";
			PreparedStatement statement = dbc.prepareStatement(searchQuery);
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				toReturn.add(rs.getInt(1));
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return toReturn;
	}
	
	/**
	 * Will check if a poll is current or not. If it is, and it has passed the 
	 * end total it will flip it. Else it will just continue
	 * @param pollNum
	 */
	public static void checkCurrent(int pollNum){
		try{
			
			String checkQuery = "SELECT * FROM Polls WHERE PollNum = ?";
			PreparedStatement statement1 = dbc.prepareStatement(checkQuery);
			statement1.setInt(1, pollNum);
			ResultSet rs1 = statement1.executeQuery();
			int endTotal = 0;
			boolean noEndTotal = false;
			if(rs1.next()){
				if(rs1.getString(7)!=null){
					endTotal = rs1.getInt(7);
				}
				else{
					noEndTotal = true;
				}
			}
			
			if(!noEndTotal){
				String countQuery = "SELECT * FROM PollTaker WHERE PollNum = ?";
				PreparedStatement statement2 = dbc.prepareStatement(countQuery);
				statement2.setInt(1, pollNum);
				ResultSet rs2 = statement2.executeQuery();
				int pollTakerCount = 0;
				while (rs2.next()){
					pollTakerCount = pollTakerCount+1;
				}
				
				if(pollTakerCount == endTotal || pollTakerCount >= endTotal){
					String updateQuery = "UPDATE Polls SET isCurrent = ? WHERE PollNum = ?;";
					PreparedStatement statement3 = dbc.prepareStatement(updateQuery);
					statement3.setInt(1, 0);
					statement3.setInt(2, pollNum);
					statement3.execute();
				}
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	/*
	 * Performs the forgotten password opertation
	 */
	public static void forgotPassword(String email){
		String password = "";
		try {
			Random rand = new Random();
			int randomNumber = rand.nextInt(100000);
			password = "Password" + String.valueOf(randomNumber);
			String updateQuery = "UPDATE RUser SET Pword = ? WHERE Email = ?;";
			PreparedStatement statement = dbc.prepareStatement(updateQuery);
			statement.setString(1, password);
			statement.setString(2, email);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//Sends the email
		String info = "Hey user,\n\nWe recieved a request for a forgotten password.\n\nWe've changed your password to the following, '"+password+"'.\n\nGet in touch if you have any other issues!\n\t-easyPoll Team";
		Email.sendMail(email, "Forgot Your Password?", info);
	}

	/**
	 * Will add a polltaker <- Matts code
	 * @param pollTaker
	 */
	public static void addPollTaker(PollTaker pollTaker) {
		try{
			String insertQuery = "INSERT INTO PollTaker VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";
			PreparedStatement statement = dbc.prepareStatement(insertQuery);
			statement.setInt(2, pollTaker.getPollNum());
			statement.setString(1, pollTaker.getUsername());
			statement.setBoolean(3, pollTaker.getPublicAnswers());
			
			int counter = 0;
			for(int i = 4; i < pollTaker.getUserAnswers().size() +4; i++){
				statement.setInt(i, pollTaker.getUserAnswers().get(counter));
				counter++;
			}
			while(counter < 10){
				statement.setNull(counter+4, java.sql.Types.INTEGER);
				counter++;
			}				
			
			statement.execute();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}

	/**
	 * Will add a reported question to the DB so that admins can see it at a later time.
	 * Will also return the username of the person who reported it. It will also return 
	 * the number of people who reported it which is really cool
	 * @param username
	 * @param pollNum
	 */
	public static void addReportedQuestion(String username, int pollNum) {
		String getPollQuery = "select * from Polls join PollData ON PollData.PollNum = Polls.PollNum" +
							" where Polls.pollNum = " + pollNum + ";";
		try{
		Statement statement = dbc.createStatement();
		rs = statement.executeQuery(getPollQuery);
		
		String PollName = "";
		String Question = "";
		String Description = "";
	
		if(rs.next()){
			PollName = rs.getString(4);
			if(PollName == null){
				PollName = "";
			}
			Question = rs.getString(11);
			if(Question == null){
				Question = "";
			}
			Description = rs.getString(12);	
			if(Description == null){
				Description = "";
			}
		}
		
		String addReportedQuestion = "INSERT INTO ReportedQuestions" +
									" VALUES(?, ?, ?, ?, ?);";
		System.out.println(addReportedQuestion);			
		PreparedStatement statement2 = dbc.prepareStatement(addReportedQuestion);
		statement2.setInt(1, pollNum);
		statement2.setString(2, username);
		statement2.setString(3, Question);
		statement2.setString(4, Description);
		statement2.setString(5, PollName);
		statement2.execute();
		}
		catch(SQLException se){
			System.out.println(se);
		}
	}
	
	/*
	 * Will insert an additional admin into the DB. The email is generated 
	 * for the admin
	 */
	public static void createAdmin(String username, String password) {
		try{
			String newUsername = username + "(Admin)";
			String email = username+"@easypoll.com";
			String insertQuery = "INSERT INTO AdminUser (Username, Email, Pword) VALUES (?,?,?);";
			PreparedStatement statement = dbc.prepareStatement(insertQuery);
			statement.setString(1, newUsername);
			statement.setString(2, email);
			statement.setString(3, password);
			statement.execute();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	/*
	 * Will ensure that the login information is valid, if it is it will
	 * return an Admin object
	 */
	public static Administrator adminLogin(String email, String password) {
		try {
			Administrator tempAdmin = new Administrator();
			email = "'" + email + "'";
			password = "'" + password + "'";
			   
			String loginQuery = "SELECT Username FROM AdminUser WHERE Email = " + email + 
						" AND Pword = " + password + ";";
			statement = dbc.createStatement();
			rs = statement.executeQuery(loginQuery);
			if(rs.next()){
				System.out.println("User Logged in: " + rs.getString(1));
				tempAdmin.setUsername(rs.getString(1));
			}
			else
				tempAdmin.setUsername("");
			return tempAdmin;
		} catch (SQLException e){
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * Inserts the poll into the DB
	 */
	public static void addPoll(Poll poll, String username, ArrayList<String> answerOptions) {
		// Insert the user into the database
		
				try{
					String insertPollsQuery = "INSERT INTO Polls (Username, isCurrent, PollName, Partakers, PollType, EndTotal) "
							+ "VALUES (?,?,?,?,?,?);";
					PreparedStatement statement = dbc.prepareStatement(insertPollsQuery);
					statement.setString(1, username);
					statement.setBoolean(2, true);
					statement.setString(3, poll.getPollName());
					statement.setInt(4, 0);
					statement.setString(5, poll.getPollType());
					statement.setInt(6, poll.getEndTotal());
					statement.execute();
					
					
					String insertPollDataQuery = "INSERT INTO PollData(PollNum, Question, Description, Params, isRadio, AnsOne, AnsTwo, AnsThree, "
							+ "AnsFour, AnsFive, AnsSix, AnsSeven, AnsEight, AnsNine, AnsTen, "
							+ "TotalOne, TotalTwo, TotalThree, TotalFour, TotalFive, TotalSix, TotalSeven, TotalEight, "
							+ "TotalNine, TotalTen ) VALUES ((SELECT LAST_INSERT_ID()),?,?, ?, true,?,?,?,?,?,?,?,?,?,?, 0, 0, 0,0,0,0,0,0,0,0);";
					PreparedStatement statement2 = dbc.prepareStatement(insertPollDataQuery);
					statement2.setString(1, poll.getPollQuestion());
					statement2.setString(2, poll.getPollDescription());
					statement2.setInt(3, answerOptions.size());
	
					int counter = 0;
				    for(int i = 4; i < answerOptions.size() +4; i++){
				    	statement2.setString(i, answerOptions.get(counter));
				    	counter++;
				    }
				    while(counter < 10){
				    	statement2.setNull(counter+4, java.sql.Types.VARCHAR);
				    	counter++;
				    }
	
					statement2.execute();
					System.out.println("Successful insertion");
				}
				catch(SQLException se){
					System.out.println(se);
				}
	}
	
	/*
	 * Deletes the poll from the DB given a PollNum
	 */
	public static void deletePoll(int pollNum){
		try {
			String dd = "DELETE FROM PollTags WHERE PollNum = ?;";
			PreparedStatement ss = dbc.prepareStatement(dd);
			ss.setInt(1, pollNum);
			ss.execute();
			String deletePollTaker = "DELETE FROM PollTaker WHERE PollNum = ?;";
			PreparedStatement statement1 = dbc.prepareStatement(deletePollTaker);
			statement1.setInt(1, pollNum);
			statement1.execute();
			String deletePollData = "DELETE FROM PollData WHERE PollNum = ?;";
			PreparedStatement statement2 = dbc.prepareStatement(deletePollData);
			statement2.setInt(1, pollNum);
			statement2.execute();
			String getGroupNum = "SELECT GroupNum FROM PollGroup WHERE PollNum = ?;";
			PreparedStatement statement5 = dbc.prepareStatement(getGroupNum);
			statement5.setInt(1, pollNum);
			ResultSet rs = statement5.executeQuery();
			String num = "15000";
			if(rs.next()){
				num = rs.getString(1);
			}
			String deleteUserGroup = "DELETE FROM UserGroup WHERE GroupNum = ?;";
			PreparedStatement statement6 = dbc.prepareStatement(deleteUserGroup);
			statement6.setInt(1, Integer.parseInt(num));
			statement6.execute();
			String deletePollGroup = "DELETE FROM PollGroup WHERE GroupNum = ?;";
			PreparedStatement statement7 = dbc.prepareStatement(deletePollGroup);
			statement7.setInt(1,Integer.parseInt(num));
			statement7.execute();
			String deletePolls = "DELETE FROM Polls WHERE PollNum = ?;";
			PreparedStatement statement3 = dbc.prepareStatement(deletePolls);
			statement3.setInt(1, pollNum);
			statement3.execute();
			String deleteReportedQuestions = "DELETE FROM ReportedQuestions WHERE PollNum = ?;";
			PreparedStatement statement4 = dbc.prepareStatement(deleteReportedQuestions);
			statement4.setInt(1, pollNum);
			statement4.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Inserts the group into the DB
	 */
	public static void createGroup(String groupName, int pollNum, String username){
		try{
			String insertQuery = "INSERT INTO PollGroup (PollNum, GroupName, AdminUsername) VALUES (?,?,?);";
			PreparedStatement statement = dbc.prepareStatement(insertQuery);
			statement.setInt(1, pollNum);
			statement.setString(2, groupName);
			statement.setString(3, username);
			statement.execute();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	/*
	 * Removes the group from the DB
	 */
	public static void deleteGroup(String groupNum){
		try{
			String deleteQuery = "DELETE FROM PollGroup WHERE PollGroup.GroupNum = ?;";
			PreparedStatement statement = dbc.prepareStatement(deleteQuery);
			statement.setInt(1, Integer.valueOf(groupNum));
			statement.execute();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	/*
	 * Will return an object of the group poll I can't type what am I even 
	 * doing. I can't even think right now.
	 */
	public static Group getPollGroup(int groupNum){
		try{
			Group toReturn = new Group();
			String searchQuery = "SELECT * FROM PollGroup WHERE GroupNum = ?;";
			PreparedStatement statement = dbc.prepareStatement(searchQuery);
			statement.setInt(1, groupNum);
			ResultSet rs = statement.executeQuery();
			if (rs.next()){
				toReturn.setGroupName(rs.getString(3));
				toReturn.setGroupID(rs.getInt(1));
				toReturn.setGroupPoll(new Poll(rs.getInt(2)));
				toReturn.setAdmin(rs.getString(4));
			}
			return toReturn;
		} catch (SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * Adds the user to the group
	 */
	public static void addUserToGroup(String username, String groupNum){
		try{
			String insertQuery = "INSERT INTO UserGroup (Username, GroupNum) VALUES (?,?);";
			PreparedStatement statement = dbc.prepareStatement(insertQuery);
			statement.setString(1, username);
			statement.setInt(2, Integer.valueOf(groupNum));
			statement.execute();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Deletes the user from the group
	 * @param username
	 * @param groupNum
	 */
	public static void deleteUserFromGroup(String username, String groupNum){
		try{
			String deleteQuery = "DELETE FROM UserGroup WHERE Username = ? and GroupNum = ?;";
			PreparedStatement statement = dbc.prepareStatement(deleteQuery);
			statement.setString(1, username);
			statement.setInt(2, Integer.valueOf(groupNum));
			statement.execute();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	/*
	 * Will return an arraylist of RUser objects that make up a group
	 */
	public static ArrayList<RUser> getGroupMembers(String groupNum){
		try{
			ArrayList<RUser> toReturn = new ArrayList<RUser>();
			String searchQuery = "SELECT Username FROM UserGroup WHERE GroupNum = ?";
			PreparedStatement statement = dbc.prepareStatement(searchQuery);
			statement.setInt(1, Integer.valueOf(groupNum));
			ResultSet rs = statement.executeQuery();
			while (rs.next()){
				RUser toAdd = new RUser();
				toAdd.setUsername(rs.getString(1));
				toReturn.add(toAdd);
			}
			return toReturn;
		} catch (SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	/*
	 * Will return the poll groups for a given user. Will return an 
	 * arraylist of group objects belonging to that user.
	 */
	public static ArrayList<Group> getYourPollGroups(String username){
		ArrayList<Group> toReturn = new ArrayList<Group>();
		try{
			String searchQuery = "SELECT GroupNum, PollNum, GroupName, AdminUsername FROM PollGroup WHERE AdminUsername = ?;";
			PreparedStatement statement = dbc.prepareStatement(searchQuery);
			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				toReturn.add(new Group(rs.getInt(1), rs.getString(4), new Poll(rs.getInt(2)), rs.getString(3)));
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return toReturn;
	}
	
	/*
	 * Will return all of a users private polls. These are not public polls
	 * thank you.
	 */
	public static ArrayList<Poll> getYourPrivatePolls(String username){
		ArrayList<Poll> toReturn = new ArrayList<Poll>();
		try {
			String searchQuery = "SELECT PollNum FROM Polls WHERE Username = ? AND PollType = 'private';";
			PreparedStatement statement = dbc.prepareStatement(searchQuery);
			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();
			while (rs.next()){
				Poll poll = new Poll(Integer.parseInt(rs.getString(1)));
				toReturn.add(poll);
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return toReturn;
	}
	
	/**
	 * Will return all of the groups that a user has been invited to. This does 
	 * not include groups created by the user.
	 * @param username
	 * @return
	 */
	public static ArrayList<Group> getYourInvitedGroups(String username){
		ArrayList<Group> toReturn = new ArrayList<Group>();
		try{
			String searchQuery = "SELECT GroupNum FROM UserGroup WHERE Username = ?;";
			PreparedStatement statement = dbc.prepareStatement(searchQuery);
			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();
			ArrayList<String> groupNums = new ArrayList<String>();
			while (rs.next()){
				groupNums.add(rs.getString(1));
			}
			for(int i=0; i< groupNums.size(); i++){
				toReturn.add(RUser.getGroup(Integer.valueOf(groupNums.get(i))));
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return toReturn;
	}
}
