package com.test.test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBQuery{

	static Connection dbc = DBConnection.getConnection();
	static Statement statement;
	static ResultSet rs;
	
	public static User Login(String email, String password) throws SQLException{
		User tempUser = new User();
		email = "'" + email + "'";
		password = "'" + password + "'";
		   
		String loginQuery = "SELECT Username FROM RUser WHERE Email = " + email + 
					" AND Pword = " + password + ";";
		statement = dbc.createStatement();
		rs = statement.executeQuery(loginQuery);
		if(rs.next()){
			System.out.println("User Logged in: " + rs.getString(1));
			tempUser.setUsername(rs.getString(1));
		}
		else
			tempUser.setUsername("");
		return tempUser;
	}
	
	public static ArrayList<Poll> getPolls() throws SQLException{
		   
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
			   String Partakers = rs.getString(5);
			   String pollType = rs.getString(6);
			   int EndTotal;
			   if(rs.getString(7)!=null)
				   EndTotal = Integer.parseInt(rs.getString(7));
			   else
				   EndTotal = -1;
			   System.out.println(EndTotal);
			   Date EndTime;
			   if(rs.getString(8)!=null)
				   EndTime = Date.valueOf(rs.getString(8));
			   else
				   EndTime = null;
			   System.out.println(EndTime);
			   String Params = rs.getString(10);
			   System.out.println(Params);
			   String Question = rs.getString(11);
			   System.out.println(Question);
			   String Description = rs.getString(12);
			   System.out.println(Description);
			   String isRadio = rs.getString(13);
			   boolean gotTags = false;
			   ArrayList<String> answers = new ArrayList<String>();
			   int i = 14;
			   while (i < 24 && rs.getString(i)!=null){
				   System.out.println("DEBUG: ANSWERChoice " + (i-13)+ ": " +rs.getString(i));
				   answers.add(rs.getString(i));
				   i++;
			   }
			   ArrayList<String> totals = new ArrayList<String>();
			   i = 24;
			   while (i < 34 && rs.getString(i)!=null){
				   System.out.println("DEBUG: TOTANSWER " + (i-23)+ ": "+rs.getString(i));
				   answers.add(rs.getString(i));
				   i++;
			   }
			   
			   ArrayList<Tag> tag = new ArrayList<Tag>();
			   ArrayList<PollTaker> pollers = new ArrayList<PollTaker>();
			   String poller = "";
			   while(
					   next && currentPollNum == Integer.parseInt(rs.getString(1)) ){ 
				   int pollNum = 0;
				   String publicAnswer = null;
				   ArrayList<String> userans = new ArrayList<String>();
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
						   System.out.println("DEBUG: USERANSWER " +(i-36) + ": " +rs.getString(i));
						   userans.add(rs.getString(i));
						   i++;
					   }
					   System.out.println("DEBUG: PollTaker: " + poller);
					   pollers.add(new PollTaker(poller, (pollNum), Boolean.parseBoolean(publicAnswer), userans));
				   }
				   
				   String PollTag = rs.getString(47);
				   System.out.println("DEBUG: PollTag: " + PollTag);
				   if(PollTag!=null && !gotTags){
					   do{ 
							   System.out.println("DEBUG: TAG: " + rs.getString(49));
							   tag.add(new Tag(rs.getString(49), Integer.parseInt(rs.getString(48)), Integer.parseInt(rs.getString(47))));
							   
							   try{
								   next = rs.next();
							   }
							   catch(SQLException se){
								   next = false;
							   }
							   System.out.println("DEBUG: WHOLETAG: " +rs.getString(47) +rs.getString(48) + rs.getString(49));
							   System.out.println(pollPoster + "==" + rs.getString(2));
							   System.out.println(PollTag + "==" + rs.getString(47));
							   System.out.println(next);
							   System.out.println(currentPollNum + "==" + Integer.parseInt(rs.getString(1)));
							   
					   }
					   while(!rs.getString(47).equals(PollTag) 
							   && next 
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
			   Answer ans = new Answer(answers, totals);
			   PollData polldata= new PollData(ans, pollers);
			   publicPolls.add(new Poll(PollName, currentPollNum, Question, isCurrent, 
						Description, pollType,polldata, pollPoster,
						 tag));
			   }
		   return publicPolls;
		   }
		   
	
	public static int getTotalPolls() throws SQLException{
		String totalPolls = "SELECT COUNT(pollNum) from Polls;";
		statement = dbc.createStatement();
		ResultSet rs = statement.executeQuery(totalPolls);
		
		if(rs.next()){
			
			return Integer.parseInt(rs.getString(1));
		}
		
		return 0;
	}
	
	public static ArrayList<Poll> getMyPolls(String username) throws SQLException{
			
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
			   String Partakers = rs.getString(5);
			   String pollType = rs.getString(6);
			   int EndTotal;
			   if(rs.getString(7)!=null)
				   EndTotal = Integer.parseInt(rs.getString(7));
			   else
				   EndTotal = -1;
			   System.out.println(EndTotal);
			   Date EndTime;
			   if(rs.getString(8)!=null)
				   EndTime = Date.valueOf(rs.getString(8));
			   else
				   EndTime = null;
			   System.out.println(EndTime);
			   String Params = rs.getString(10);
			   System.out.println(Params);
			   String Question = rs.getString(11);
			   System.out.println(Question);
			   String Description = rs.getString(12);
			   System.out.println(Description);
			   String isRadio = rs.getString(13);
			   boolean gotTags = false;
			   ArrayList<String> answers = new ArrayList<String>();
			   int i = 14;
			   while (i < 24 && rs.getString(i)!=null){
				   System.out.println("DEBUG: ANSWERChoice " + (i-13)+ ": " +rs.getString(i));
				   answers.add(rs.getString(i));
				   i++;
			   }
			   ArrayList<String> totals = new ArrayList<String>();
			   i = 24;
			   while (i < 34 && rs.getString(i)!=null){
				   System.out.println("DEBUG: TOTANSWER " + (i-23)+ ": "+rs.getString(i));
				   answers.add(rs.getString(i));
				   i++;
			   }
			   
			   ArrayList<Tag> tag = new ArrayList<Tag>();
			   ArrayList<PollTaker> pollers = new ArrayList<PollTaker>();
			   String poller = "";
			   while(
					   next && currentPollNum == Integer.parseInt(rs.getString(1)) ){ 
				   int pollNum = 0;
				   String publicAnswer = null;
				   ArrayList<String> userans = new ArrayList<String>();
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
						   System.out.println("DEBUG: USERANSWER " +(i-36) + ": " +rs.getString(i));
						   userans.add(rs.getString(i));
						   i++;
					   }
					   System.out.println("DEBUG: PollTaker: " + poller);
					   pollers.add(new PollTaker(poller, (pollNum), Boolean.parseBoolean(publicAnswer), userans));
				   }
				   
				   String PollTag = rs.getString(47);
				   System.out.println("DEBUG: PollTag: " + PollTag);
				   if(PollTag!=null && !gotTags){
					   do{ 
							   System.out.println("DEBUG: TAG: " + rs.getString(49));
							   tag.add(new Tag(rs.getString(49), Integer.parseInt(rs.getString(48)), Integer.parseInt(rs.getString(47))));
							   
							   try{
								   next = rs.next();
							   }
							   catch(SQLException se){
								   next = false;
							   }
							   System.out.println("DEBUG: WHOLETAG: " +rs.getString(47) +rs.getString(48) + rs.getString(49));
							   System.out.println(pollPoster + "==" + rs.getString(2));
							   System.out.println(PollTag + "==" + rs.getString(47));
							   System.out.println(next);
							   System.out.println(currentPollNum + "==" + Integer.parseInt(rs.getString(1)));
							   
					   }
					   while(!rs.getString(47).equals(PollTag) 
							   && next 
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
			   Answer ans = new Answer(answers, totals);
			   PollData polldata= new PollData(ans, pollers);
			   myPolls.add(new Poll(PollName, currentPollNum, Question, isCurrent, 
						Description, pollType,polldata, pollPoster,
						 tag));
			   }
		   return myPolls;
	}
	
	public static Administrator adminLogin(String email, String password) throws SQLException{
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
	}
	
	public static String getPollDescription(String pollId) throws SQLException{
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
	}
	
	public static Answer getUserAnswer(int pollNum, String username){
		//search polltaker table.
		return null;
	}

}
