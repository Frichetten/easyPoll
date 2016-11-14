package com.test.test;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class HomeController {
	  
	//Create DBConnection
	Connection dbc = DBConnection.getConnection();

	//Root mapping
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView user() {
		return new ModelAndView("index", "command", new User());
	}
	
	//Root mapping
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home(@ModelAttribute("SpringWeb")User user, ModelMap model,
			HttpServletRequest request) {
		//Confirming Login Status
		User a = (User)request.getSession().getAttribute("token");
		if (a == null){
			System.out.println("User not logged in");
			//Login Modifier
			String login = "<a href='../navbar-static-top/' data-toggle='modal' data-target='#login-modal'>Login</a>";
			String signup = "<a href='../navbar-fixed-top/' data-toggle='modal' data-target='#create-account-modal'>Signup</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signup);
		}
		else{
			System.out.println("Logged in as " + a.getUsername());
			//model.addAttribute("username", a.getUsername());
			String login = "<a href='/test/profile'>"+a.getUsername()+"</a>";
			String signout = "<a href='test/signout' >Sign Out</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signout);
		}
		
		return new ModelAndView("home", "command", new User());
	}
	
	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public ModelAndView about(@ModelAttribute("SpringWeb")User user, ModelMap model,
			HttpServletRequest request) {
		//Confirming Login Status
		User a = (User)request.getSession().getAttribute("token");
		if (a == null){
			System.out.println("User not logged in");
			//Login Modifier
			String login = "<a href='../navbar-static-top/' data-toggle='modal' data-target='#login-modal'>Login</a>";
			String signup = "<a href='../navbar-fixed-top/' data-toggle='modal' data-target='#create-account-modal'>Signup</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signup);
		}
		else{
			System.out.println("Logged in as " + a.getUsername());
			//model.addAttribute("username", a.getUsername());
			String login = "<a href='/test/profile'>"+a.getUsername()+"</a>";
			String signout = "<a href='test/signout' >Sign Out</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signout);
		}
		
		return new ModelAndView("about", "command", new User());
	}
	
	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public ModelAndView contact(@ModelAttribute("SpringWeb")User user, ModelMap model,
			HttpServletRequest request) {
		//Confirming Login Status
		User a = (User)request.getSession().getAttribute("token");
		if (a == null){
			System.out.println("User not logged in");
			//Login Modifier
			String login = "<a href='../navbar-static-top/' data-toggle='modal' data-target='#login-modal'>Login</a>";
			String signup = "<a href='../navbar-fixed-top/' data-toggle='modal' data-target='#create-account-modal'>Signup</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signup);
		}
		else{
			System.out.println("Logged in as " + a.getUsername());
			//model.addAttribute("username", a.getUsername());
			String login = "<a href='/test/profile'>"+a.getUsername()+"</a>";
			String signout = "<a href='test/signout' >Sign Out</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signout);
		}
		
		return new ModelAndView("contact", "command", new User());
	}
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView profile(@ModelAttribute("SpringWeb")User user, ModelMap model,
			HttpServletRequest request) {
		//Confirming Login Status
		User a = (User)request.getSession().getAttribute("token");
		if (a == null){
			System.out.println("User not logged in");
			//Login Modifier
			String login = "<a href='../navbar-static-top/' data-toggle='modal' data-target='#login-modal'>Login</a>";
			String signup = "<a href='../navbar-fixed-top/' data-toggle='modal' data-target='#create-account-modal'>Signup</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signup);
		}
		else{
			System.out.println("Logged in as " + a.getUsername());
			//model.addAttribute("username", a.getUsername());
			String login = "<a href='/test/profile'>"+a.getUsername()+"</a>";
			String signout = "<a href='test/signout' >Sign Out</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signout);
		}
		
		return new ModelAndView("userprofile", "command", new User());
	}
	   
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public ModelAndView addUser(@ModelAttribute("SpringWeb")User user, ModelMap model,
			HttpServletRequest request) throws SQLException {
		//Authentication
		String username = "'"+user.getUsername()+"'";
		String password = "'"+user.getPassword()+"'";
		String loginQuery = "SELECT Username FROM RUser WHERE Username = " + username + 
				"AND Pword = " + password;
		Statement statement = dbc.createStatement();
		ResultSet rs = statement.executeQuery(loginQuery);
		if (rs.next()){
			System.out.println("User Logged in: " + rs.getString(1));
			//If Authentication successful
			//Add these attributes to the model so they will appear
			model.addAttribute("username", user.getUsername());
			model.addAttribute("password", user.getPassword());
			request.getSession().setAttribute("token", user);
			
			User a = (User)request.getSession().getAttribute("token");
			if (a == null){
				System.out.println("User not logged in");
				//Login Modifier
				String login = "<a href='../navbar-static-top/' data-toggle='modal' data-target='#login-modal'>Login</a>";
				String signup = "<a href='../navbar-fixed-top/' data-toggle='modal' data-target='#create-account-modal'>Signup</a>";
				model.addAttribute("login", login);
				model.addAttribute("signup", signup);
			}
			else{
				System.out.println("Logged in as " + a.getUsername());
				//model.addAttribute("username", a.getUsername());
				String login = "<a href='/test/profile'>"+a.getUsername()+"</a>";
				String signout = "<a href='/test/signout' >Sign Out</a>";
				model.addAttribute("login", login);
				model.addAttribute("signup", signout);
			}
			return new ModelAndView("home", "command", new User());
		}
		else {
			System.out.println("Failure To Login");
			return new ModelAndView("index", "command", new User());
		}
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView register(@ModelAttribute("SpringWeb")User user, ModelMap model,
			HttpServletRequest request) throws SQLException{
		//Check if user exists in database
		String username = "'"+user.getUsername()+"'";
		String password = "'"+user.getPassword()+"'";
		String email = "'"+user.getEmail()+"'";
		
		String loginQuery = "SELECT Username FROM RUser WHERE Username = " + username;
		Statement statement = dbc.createStatement();
		ResultSet rs = statement.executeQuery(loginQuery);
		if (rs.next()){
			System.out.println("User already exists");
		}
		else{
			//Insert the user into the database
			String insertUserQuery = "insert into RUser (Username, Pword, Email) Values ("+username+","+password+","+email+")";
			Statement st2 = dbc.createStatement();
			st2.execute(insertUserQuery);
		}
		return new ModelAndView("home", "command", new User());
	}
	
	@RequestMapping(value = "/mypolls", method = RequestMethod.GET)
	public ModelAndView mypolls(@ModelAttribute("SpringWeb")User user, ModelMap model,
			HttpServletRequest request) throws SQLException{
		//Confirming Login status
		User a = (User)request.getSession().getAttribute("token");
		if (a == null){
			return home(user, model, request);
		}
		else{
			System.out.println("Logged in as " + a.getUsername());
			String login = "<a href='/profile'>"+a.getUsername()+"</a>";
			String signout = "<a href='test/signout' >Sign Out</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signout);
		}
		model.addAttribute("username", a.getUsername());
		String username = a.getUsername();
		
		//Add attributes to the model that are Polls
		String searchQuery = "SELECT * FROM Polls p JOIN PollData on PollData.PollNum = p.PollNum " +
				"WHERE p.Username = '"+username+"' and p.isCurrent = 1";
		Statement statement = dbc.createStatement();
		ResultSet rs = statement.executeQuery(searchQuery);
		ArrayList<String> toShow = new ArrayList<String>();
		ArrayList<String> toCache = new ArrayList<String>();
		ArrayList<String> toDesc = new ArrayList<String>();
		while(rs.next()){
			toCache.add(rs.getString(1));
			toShow.add(rs.getString(4));
			toDesc.add(rs.getString(10));
		}
		
		for (int i =0; i< toShow.size(); i++){
			model.addAttribute("Title"+String.valueOf(i), toShow.get(i));
			model.addAttribute("id"+String.valueOf(i), toCache.get(i));
			model.addAttribute("desc"+String.valueOf(i), toDesc.get(i));
		}
		
		return new ModelAndView("mypolls", "command", new User());
	}
	
	@RequestMapping(value = "/communitypolls", method = RequestMethod.GET)
	public ModelAndView community(@ModelAttribute("SpringWeb")User user, ModelMap model,
			HttpServletRequest request){
		//Confirming Login Status
		User a = (User)request.getSession().getAttribute("token");
		if (a == null){
			System.out.println("User not logged in");
			//Login Modifier
			String login = "<a href='../navbar-static-top/' data-toggle='modal' data-target='#login-modal'>Login</a>";
			String signup = "<a href='../navbar-fixed-top/' data-toggle='modal' data-target='#create-account-modal'>Signup</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signup);
		}
		else{
			System.out.println("Logged in as " + a.getUsername());
			String login = "<a href='/profile'>"+a.getUsername()+"</a>";
			String signout = "<a href='test/signout' >Sign Out</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signout);
		}
				
		return new ModelAndView("communitypolls");
	}
	
	@RequestMapping(value = "/createpoll", method = RequestMethod.GET)
	public ModelAndView createpoll(@ModelAttribute("SpringWeb")User user, ModelMap model,
			HttpServletRequest request){
		return new ModelAndView("createpoll");
	}
	
	@RequestMapping(value = "/createpollfunction", method = RequestMethod.POST)
	public @ResponseBody ModelAndView createpollfunction(@ModelAttribute("SpringWeb")Poll poll, ModelMap model,
			HttpServletRequest request) throws SQLException{
		System.out.println("Starting function");
		User a = (User)request.getSession().getAttribute("token");
		model.addAttribute("username", a.getUsername());
		System.out.println(poll.getPollName());
		System.out.println(poll.getPollQuestion());
		System.out.println(poll.getPollDescription());
		System.out.println(poll.getAnswerType());
		System.out.println(poll.getPub());
		System.out.println(poll.getAnswer());
		
		//Splitting answers by comma delimeters
		String[] answers = poll.getAnswer().split(",");
		String[] answersArray = new String[10];
		for (int i=0; i < 10; i++){
			if (i < answers.length)
				answersArray[i] = answers[i];
			else
				answersArray[i] = null;
		}
		
		//Insert the user into the database
		String insertPollsQuery = "INSERT INTO Polls (Username, isCurrent, PollName, Partakers, PollType) " + 
				"VALUES ('" +a.getUsername()+ "',1,'"+poll.getPollName()+"',0,'"+poll.getPub()+"');";
		Statement st2 = dbc.createStatement();
		st2.execute(insertPollsQuery);
		String insertPollDataQuery = "INSERT INTO PollData(PollNum, Question, Description, Params, isRadio, AnsOne, AnsTwo, AnsThree, " +
		"AnsFour, AnsFive, AnsSix, AnsSeven, AnsEight, AnsNine, AnsTen, " + 
		"TotalOne, TotalTwo, TotalThree, TotalFour, TotalFive, TotalSix, TotalSeven, TotalEight, " +
		"TotalNine, TotalTen) VALUES ((SELECT LAST_INSERT_ID()), '"+poll.getPollQuestion()+"', '"+poll.getPollDescription()+"', "+answers.length+", true, '"+answersArray[0]+"', " +
		"'"+answersArray[1]+"' , '"+answersArray[2]+"' , '"+answersArray[3]+"' , '"+answersArray[4]+"' ," +
		"'"+answersArray[5]+"', '"+answersArray[6]+"' , '"+answersArray[7]+"' , '"+answersArray[8]+"' , " +
		"'"+answersArray[9]+"'"+ 
		", 0, 0, 0,0,0,0,0,0,0,0);";
		st2.execute(insertPollDataQuery);
		System.out.println("Successful insertion");
		
		return new ModelAndView("createpoll");
	}
	
	@RequestMapping(value = "/singlepoll/{pollId}", method = RequestMethod.GET)
	public ModelAndView singlePoll(@ModelAttribute("SpringWeb")User user, ModelMap model,
			HttpServletRequest request, @PathVariable String pollId) throws SQLException{
		//Confirming Login Status
		User a = (User)request.getSession().getAttribute("token");
		if (a == null){
			System.out.println("User not logged in");
			//Login Modifier
			String login = "<a href='../navbar-static-top/' data-toggle='modal' data-target='#login-modal'>Login</a>";
			String signup = "<a href='../navbar-fixed-top/' data-toggle='modal' data-target='#create-account-modal'>Signup</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signup);
		}
		else{
			System.out.println("Logged in as " + a.getUsername());
			//model.addAttribute("username", a.getUsername());
			String login = "<a href='/profile'>"+a.getUsername()+"</a>";
			String signout = "<a href='/test/signout' >Sign Out</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signout);
		}
		
		//Before doing anything, we need to confirm that they havent voted yet
		String check = "SELECT * FROM PollTaker WHERE Username = '"+a.getUsername()+"' and PollNum = "+pollId+";";
		Statement checkStatement = dbc.createStatement();
		ResultSet checkRS = checkStatement.executeQuery(check);
		//If YES, This user has already voted
		//Else, they have not!
		if(checkRS.next()){
			return singlePollData(new Answer(), model, request, pollId);
		}
		
		//Getting Column names and username
		System.out.println("Starting dynamic url");
		String searchQuery = "SELECT * FROM Polls p JOIN PollData on PollData.PollNum = p.PollNum " +
				"WHERE p.PollNum = " + pollId + ";";
		Statement statement = dbc.createStatement();
		ResultSet rs = statement.executeQuery(searchQuery);
		if (rs.next()){
			model.addAttribute("posterUsername", rs.getString(2));
			model.addAttribute("pollName", rs.getString(4));
			model.addAttribute("pollQuestion", rs.getString(9));
			//Creating builder
			String builder = "";
			String[] options = new String[10];
			String[] values = new String[10];
			for(int i =0; i< Integer.valueOf(rs.getString(8)); i++){
				builder = builder + "<div class='radio'><label><input type='radio' name='answer' id='Private' value='"+String.valueOf(i+1)+"'/>"+rs.getString(12+i)+"</label></div>";
				options[i] = rs.getString(12+i);
				values[i] = rs.getString(22+i);
			}
			String optionsList = "";
			String valuesList = "";
			for(int i=0; i < Integer.valueOf(rs.getString(8)); i++){
				optionsList = optionsList + "'"+options[i]+"',";
				valuesList = valuesList + "'"+values[i]+"',";
			}
			model.addAttribute("optionsList", optionsList);
			model.addAttribute("valuesList", valuesList);
			model.addAttribute("builder", builder);
			model.addAttribute("pollID", pollId);
		}
		
		return new ModelAndView("singlepoll");
	}
	
	@RequestMapping(value = "/singlepolldata/{pollId}", method = RequestMethod.GET)
	public ModelAndView singlePollData(@ModelAttribute("SpringWeb")Answer answer, ModelMap model,
			HttpServletRequest request, @PathVariable String pollId) throws SQLException{
		//Confirming Login Status
		User a = (User)request.getSession().getAttribute("token");
		if (a == null){
			System.out.println("User not logged in");
			//Login Modifier
			String login = "<a href='../navbar-static-top/' data-toggle='modal' data-target='#login-modal'>Login</a>";
			String signup = "<a href='../navbar-fixed-top/' data-toggle='modal' data-target='#create-account-modal'>Signup</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signup);
		}
		else{
			System.out.println("Logged in as " + a.getUsername());
			//model.addAttribute("username", a.getUsername());
			String login = "<a href='/profile'>"+a.getUsername()+"</a>";
			String signout = "<a href='test/signout' >Sign Out</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signout);
		}
		
		//Getting Column names and username
		System.out.println("singlepolldataaaa");
		//If answer equals null, do nothing
		//Else put that in the DB
		if (answer.getAnswer() == null){
			System.out.println("We are coming without giving an answer");
		}
		else{
			System.out.println("We are giving an answer " + answer.getAnswer());
			String column = "";
			String ans = answer.getAnswer();
			if (ans.equals("1"))
				column = "TotalOne";
			else if (ans.equals("2"))
				column = "TotalTwo";
			else if (ans.equals("3"))
				column = "TotalThree";
			else if (ans.equals("4"))
				column = "TotalFour";
			else if (ans.equals("5"))
				column = "TotalFive";
			else if (ans.equals("6"))
				column = "TotalSix";
			else if (ans.equals("7"))
				column = "TotalSeven";
			else if (ans.equals("8"))
				column = "TotalEight";
			else if (ans.equals("9"))
				column = "TotalNine";
			else if (ans.equals("10"))
				column = "TotalTen";
			String updateQuery = "UPDATE Polls p JOIN PollData on PollData.PollNum = p.PollNum SET "+column+" = "+column+" + 1 WHERE p.PollNum = "+pollId+";";
			Statement statement = dbc.createStatement();
			statement.execute(updateQuery);
			String insertQuery = "INSERT INTO PollTaker (Username, PollNum) VALUES('"+a.getUsername()+"', "+pollId+");";
			statement.execute(insertQuery);
			System.out.println("Update complete");
		}
		
		String searchQuery = "SELECT * FROM Polls p JOIN PollData on PollData.PollNum = p.PollNum " +
				"WHERE p.PollNum = " + pollId + ";";
		Statement statement = dbc.createStatement();
		ResultSet rs = statement.executeQuery(searchQuery);
		if (rs.next()){
			model.addAttribute("posterUsername", rs.getString(2));
			model.addAttribute("pollName", rs.getString(4));
			model.addAttribute("pollQuestion", rs.getString(9));
			//Creating builder
			String builder = "";
			String[] options = new String[10];
			String[] values = new String[10];
			for(int i =0; i< Integer.valueOf(rs.getString(8)); i++){
				options[i] = rs.getString(12+i);
				values[i] = rs.getString(22+i);
			}
			String optionsList = "";
			String valuesList = "";
			for(int i=0; i < Integer.valueOf(rs.getString(8)); i++){
				optionsList = optionsList + "'"+options[i]+"',";
				valuesList = valuesList + "'"+values[i]+"',";
			}
			model.addAttribute("optionsList", optionsList);
			model.addAttribute("valuesList", valuesList);
		}
		
		return new ModelAndView("singlepolldata");
	}
	
	@RequestMapping(value = "/signout", method = RequestMethod.GET)
	public ModelAndView signout(@ModelAttribute("SpringWeb")User user, ModelMap model,
			HttpServletRequest request){
		//Setting token to null so that the user no longer exist
		request.getSession().setAttribute("token", null);
		
		return home(user, model, request);
	}
}
