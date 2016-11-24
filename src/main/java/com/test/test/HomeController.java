package com.test.test;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class HomeController {

	// Create DBConnection
	Connection dbc = DBConnection.getConnection();
	
	@Resource
	WebServiceContext context;

	// Root mapping
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView user() {
		return new ModelAndView("index", "command", new User());
	}

	// Root mapping
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home(@ModelAttribute("SpringWeb") User user, ModelMap model, HttpServletRequest request) throws SQLException {
		// Confirming Login Status
		User a = (User) request.getSession().getAttribute("token");
		if (a == null) {
			System.out.println("User not logged in");
			// Login Modifier
			String login = "<a href='../navbar-static-top/' data-toggle='modal' data-target='#login-modal'>Login</a>";
			String signup = "<a href='../navbar-fixed-top/' data-toggle='modal' data-target='#create-account-modal'>Signup</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signup);
		} else {
			System.out.println("Logged in as " + a.getUsername());
			// model.addAttribute("username", a.getUsername());
			String login = "<a href='/test/profile'>" + a.getUsername() + "</a>";
			String signout = "<a href='/test/signout' >Sign Out</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signout);
		}
		
		ArrayList<Poll> polls = User.getPolls();
		if (polls.size() > 3){
			for(int i = (polls.size()-1), j = 0; i > (polls.size()-5); i--, j++){
				model.addAttribute("title"+String.valueOf(j),polls.get(i).getPollName());
				model.addAttribute("pollDesc"+String.valueOf(j),polls.get(i).getPollDescription());
				model.addAttribute("pollId"+String.valueOf(j),polls.get(i).getPollNum());
			}
		}
		
		return new ModelAndView("home", "command", new User());
	}
	
	//Code for the group page of the site
	@RequestMapping(value = "/group", method = RequestMethod.GET)
	public ModelAndView group(@ModelAttribute("SpringWeb") User user, ModelMap model, HttpServletRequest request) {
		User a = (User) request.getSession().getAttribute("token");
		if (a == null) {
			System.out.println("User not logged in");
			// Login Modifier
			String login = "<a href='../navbar-static-top/' data-toggle='modal' data-target='#login-modal'>Login</a>";
			String signup = "<a href='../navbar-fixed-top/' data-toggle='modal' data-target='#create-account-modal'>Signup</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signup);
		} else {
			System.out.println("Logged in as " + a.getUsername());
			// model.addAttribute("username", a.getUsername());
			String login = "<a href='/test/profile'>" + a.getUsername() + "</a>";
			String signout = "<a href='/test/signout' >Sign Out</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signout);
		}
		
		return new ModelAndView("group", "command", new User());
	}

	//Code for the groupmanager pager
	@RequestMapping(value = "/groupmanager", method = RequestMethod.GET)
	public ModelAndView groupmanager(@ModelAttribute("SpringWeb") User user, ModelMap model, HttpServletRequest request) {
		User a = (User) request.getSession().getAttribute("token");
		if (a == null) {
			System.out.println("User not logged in");
			// Login Modifier
			String login = "<a href='../navbar-static-top/' data-toggle='modal' data-target='#login-modal'>Login</a>";
			String signup = "<a href='../navbar-fixed-top/' data-toggle='modal' data-target='#create-account-modal'>Signup</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signup);
		} else {
			System.out.println("Logged in as " + a.getUsername());
			// model.addAttribute("username", a.getUsername());
			String login = "<a href='/test/profile'>" + a.getUsername() + "</a>";
			String signout = "<a href='/test/signout' >Sign Out</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signout);
		}

		return new ModelAndView("groupmanager", "command", new User());
	}

	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public ModelAndView about(@ModelAttribute("SpringWeb") User user, ModelMap model, HttpServletRequest request) {
		// Confirming Login Status
		User a = (User) request.getSession().getAttribute("token");
		if (a == null) {
			System.out.println("User not logged in");
			// Login Modifier
			String login = "<a href='../navbar-static-top/' data-toggle='modal' data-target='#login-modal'>Login</a>";
			String signup = "<a href='../navbar-fixed-top/' data-toggle='modal' data-target='#create-account-modal'>Signup</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signup);
		} else {
			System.out.println("Logged in as " + a.getUsername());
			// model.addAttribute("username", a.getUsername());
			String login = "<a href='/test/profile'>" + a.getUsername() + "</a>";
			String signout = "<a href='/test/signout' >Sign Out</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signout);
		}

		return new ModelAndView("about", "command", new User());
	}

	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public ModelAndView contact(@ModelAttribute("SpringWeb") User user, ModelMap model, HttpServletRequest request) {
		// Confirming Login Status
		User a = (User) request.getSession().getAttribute("token");
		if (a == null) {
			System.out.println("User not logged in");
			// Login Modifier
			String login = "<a href='../navbar-static-top/' data-toggle='modal' data-target='#login-modal'>Login</a>";
			String signup = "<a href='../navbar-fixed-top/' data-toggle='modal' data-target='#create-account-modal'>Signup</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signup);
		} else {
			System.out.println("Logged in as " + a.getUsername());
			// model.addAttribute("username", a.getUsername());
			String login = "<a href='/test/profile'>" + a.getUsername() + "</a>";
			String signout = "<a href='/test/signout' >Sign Out</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signout);
		}

		return new ModelAndView("contact", "command", new User());
	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView profile(@ModelAttribute("SpringWeb") User user, ModelMap model, HttpServletRequest request)
			throws SQLException {
		// Confirming Login Status
		User a = (User) request.getSession().getAttribute("token");
		if (a == null) {
			System.out.println("User not logged in");
			// Login Modifier
			String login = "<a href='../navbar-static-top/' data-toggle='modal' data-target='#login-modal'>Login</a>";
			String signup = "<a href='../navbar-fixed-top/' data-toggle='modal' data-target='#create-account-modal'>Signup</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signup);
		} else {
			System.out.println("Logged in as " + a.getUsername());
			// model.addAttribute("username", a.getUsername());
			String login = "<a href='/test/profile'>" + a.getUsername() + "</a>";
			String signout = "<a href='/test/signout' >Sign Out</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signout);
		}

		String numPollsQuery = "Select * FROM Polls WHERE Username = " + "'" + a.getUsername() + "';";
		Statement statement = dbc.createStatement();
		ResultSet rs = statement.executeQuery(numPollsQuery);

		int counter = 0;

		while (rs.next()) {
			counter++;
		}

		model.addAttribute("numPolls", counter);

		String votedQuery = "SELECT * FROM PollTaker WHERE Username = " + "'" + a.getUsername() + "'";

		rs = statement.executeQuery(votedQuery);

		counter = 0;

		while (rs.next()) {
			counter++;
		}

		model.addAttribute("numVoted", counter);

		rs = statement.executeQuery(numPollsQuery);
		if (rs.next()) {
			int best = Integer.parseInt(rs.getString("Partakers"));
			String name = rs.getString("PollName");
			while (rs.next()) {
				int voters = Integer.parseInt(rs.getString("Partakers"));
				if (voters > best) {
					name = rs.getString("PollName");
				}
			}

			model.addAttribute("fave", name);

			model.addAttribute("userName", a.getUsername());

			String emailQuery = "SELECT * FROM RUser WHERE Username = " + "'" + a.getUsername() + "'";
			rs = statement.executeQuery(emailQuery);

			if (rs.next()) {
				model.addAttribute("email",rs.getString("Email"));
			}
		}
		return new ModelAndView("userprofile", "command", new User());
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("SpringWeb") User user, ModelMap model, HttpServletRequest request)
			throws SQLException {
		// Authentication
		User ruser = new User();
		System.out.println("User email: " + user.getEmail());
		System.out.println("User password: " + user.getPassword());
		ruser = User.verifyUser(user.getEmail(), user.getPassword());
		
		//ResultSet rs = statement.executeQuery(loginQuery);
			if(!ruser.getUsername().equals("")){
				System.out.println("User Logged in: " + ruser.getUsername());
				// If Authentication successful
				// Add these attributes to the model so they will appear
				model.addAttribute("username", ruser.getUsername());
				request.getSession().setAttribute("token", ruser);
	
				User a = (User) request.getSession().getAttribute("token");
				if (a == null) {
					System.out.println("User not logged in");
					// Login Modifier
					String login = "<a href='../navbar-static-top/' data-toggle='modal' data-target='#login-modal'>Login</a>";
					String signup = "<a href='../navbar-fixed-top/' data-toggle='modal' data-target='#create-account-modal'>Signup</a>";
					model.addAttribute("login", login);
					model.addAttribute("signup", signup);
					System.out.println("Failure To Login");
					String referer = request.getHeader("Referer");
				    return "redirect:"+ referer;
				}
				 else {
					System.out.println("Logged in as " + a.getUsername());
					// model.addAttribute("username", a.getUsername());
					String login = "<a href='/test/profile'>" + a.getUsername() + "</a>";
					String signout = "<a href='/test/signout' >Sign Out</a>";
					model.addAttribute("login", login);
					model.addAttribute("signup", signout);
				}
			}
			else{
				System.out.println("User failed to login");
				String referer = request.getHeader("Referer");
			    return "redirect:"+ referer;
			}
			String referer = request.getHeader("Referer");
		    return "redirect:"+ referer;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView register(@ModelAttribute("SpringWeb") User user, ModelMap model, HttpServletRequest request)
			throws SQLException {
		// Check if user exists in database
		String username = "'" + user.getUsername() + "'";
		String password = "'" + user.getPassword() + "'";
		String email = "'" + user.getEmail() + "'";

		String loginQuery = "SELECT Username FROM RUser WHERE Username = " + username;
		Statement statement = dbc.createStatement();
		ResultSet rs = statement.executeQuery(loginQuery);
		if (rs.next()) {
			System.out.println("User already exists");
		} else {
			// Insert the user into the database	
			String insertUserQuery = "insert into RUser (Username, Pword, Email) Values (" + username + "," + password
					+ "," + email + ")";
			Statement st2 = dbc.createStatement();
			st2.execute(insertUserQuery);
		}
		return new ModelAndView("home", "command", new User());
	}

	@RequestMapping(value = "/mypolls", method = RequestMethod.GET)
	public ModelAndView mypolls(@ModelAttribute("SpringWeb") User user, ModelMap model, HttpServletRequest request)
			throws SQLException {
		// Confirming Login status
		User a = (User) request.getSession().getAttribute("token");
		if (a == null) {
			return home(user, model, request);
		} else {
			System.out.println("Logged in as " + a.getUsername());
			String login = "<a href='/test/profile'>" + a.getUsername() + "</a>";
			String signout = "<a href='/test/signout' >Sign Out</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signout);
		}
		model.addAttribute("username", a.getUsername());
		String username = a.getUsername();
		
		ArrayList<Poll> myPolls = User.getMyPolls(username);

		// Add attributes to the model that are Polls
		
		ArrayList<String> toShow = new ArrayList<String>();
		ArrayList<String> toCache = new ArrayList<String>();
		ArrayList<String> toDesc = new ArrayList<String>();
		
		for (int i = 0; i < myPolls.size(); i++) {
			toCache.add(Integer.toString(myPolls.get(i).getPollNum()));
			toShow.add(myPolls.get(i).getPollName());
			toDesc.add(myPolls.get(i).getPollDescription());
		}
		String thyme = "";
		for (int j =(toShow.size()-1); j >= 0; j--){
			thyme = thyme + "<tr><td>"+toShow.get(j)+"</td><td hidden='true'>"+toCache.get(j)+"</td><td>"+toDesc.get(j)+"</td></tr>";
		}
		model.addAttribute("polls", thyme);
		return new ModelAndView("mypolls", "command", new User());
	}

	@RequestMapping(value = "/communitypolls", method = RequestMethod.GET)
	public ModelAndView community(@ModelAttribute("SpringWeb") User user, ModelMap model, HttpServletRequest request) throws SQLException {
		// Confirming Login Status
		User a = (User) request.getSession().getAttribute("token");
		if (a == null) {
			System.out.println("User not logged in");
			// Login Modifier
			String login = "<a href='../navbar-static-top/' data-toggle='modal' data-target='#login-modal'>Login</a>";
			String signup = "<a href='../navbar-fixed-top/' data-toggle='modal' data-target='#create-account-modal'>Signup</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signup);
		} else {
			System.out.println("Logged in as " + a.getUsername());
			String login = "<a href='/test/profile'>" + a.getUsername() + "</a>";
			String signout = "<a href='/test/signout' >Sign Out</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signout);
		}
		
		ArrayList<Poll> pollArr = User.getPublicPolls();
		
		
		String thyme = "";
		for (int i =(pollArr.size()-1); i >= 0; i--){
			thyme = thyme + "<tr><td>"+pollArr.get(i).getPollName()+"</td><td hidden='true'>"+pollArr.get(i).getPollNum()+"</td><td>"+pollArr.get(i).getPollDescription()+"</td><td>"+pollArr.get(i).getPollPoster()+"</td></tr>";
		}
		model.addAttribute("polls", thyme);
		
		return new ModelAndView("communitypolls", "command", new User());
	}

	@RequestMapping(value = "/createpoll", method = RequestMethod.GET)
	public ModelAndView createpoll(@ModelAttribute("SpringWeb") User user, ModelMap model, HttpServletRequest request) throws SQLException {
		int num = Poll.getTotalPoll();
		User a = (User) request.getSession().getAttribute("token");
		if (a == null) {
			System.out.println("User not logged in");
			// Login Modifier
			String login = "<a href='../navbar-static-top/' data-toggle='modal' data-target='#login-modal'>Login</a>";
			String signup = "<a href='../navbar-fixed-top/' data-toggle='modal' data-target='#create-account-modal'>Signup</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signup);
		} else {
			System.out.println("Logged in as " + a.getUsername());
			// model.addAttribute("username", a.getUsername());
			String login = "<a href='/test/profile'>" + a.getUsername() + "</a>";
			String signout = "<a href='/test/signout' >Sign Out</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signout);
		}
		model.addAttribute("numberPolls", String.valueOf(num));
		return new ModelAndView("createpoll", "command" ,new User());
	}

	@RequestMapping(value = "/createpollfunction", method = RequestMethod.POST)
	public @ResponseBody ModelAndView createpollfunction(@ModelAttribute("SpringWeb") Poll poll, ModelMap model,
			HttpServletRequest request) throws SQLException {
		System.out.println("Starting function");
		User a = (User) request.getSession().getAttribute("token");
		model.addAttribute("username", a.getUsername());
		System.out.println(poll.getPollName());
		System.out.println(poll.getPollQuestion());
		System.out.println(poll.getPollDescription());
		System.out.println(poll.getEndTotal());

		// Splitting answers by comma delimeters
		String[] answers = poll.getAnswerParams().split(",");
		ArrayList<String> answersArray = new ArrayList<String>();

		for (int i = 0; i < answers.length; i++) {
			if (i < answers.length)
				answersArray.add(answers[i]);
		}
		
		System.out.println(">>> " + answersArray.toString());
		
		User.addPoll(poll, a.getUsername(), answersArray);
		
		return mypolls(new User(), model, request);
	}

	@RequestMapping(value = "/singlepoll/{pollId}", method = RequestMethod.GET)
	public ModelAndView singlePoll(@ModelAttribute("SpringWeb") User user, ModelMap model, HttpServletRequest request,
			@PathVariable String pollId) throws SQLException {
		// Confirming Login Status
		Administrator ad = (Administrator) request.getSession().getAttribute("admintoken");
		User a = (User) request.getSession().getAttribute("token");
		if (ad != null){
			System.out.println("Logged in as " + ad.getUsername());
			// model.addAttribute("username", a.getUsername());
			String login = "<a href='/test/profile'>" + ad.getUsername() + "</a>";
			String signout = "<a href='/test/signout' >Sign Out</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signout);
			model.addAttribute("hide","hidden='true'");
			model.addAttribute("creatorHide","hidden='true'");
		}
		else if (a == null) {
			System.out.println("User not logged in");
			// Login Modifier
			String login = "<a href='../navbar-static-top/' data-toggle='modal' data-target='#login-modal'>Login</a>";
			String signup = "<a href='../navbar-fixed-top/' data-toggle='modal' data-target='#create-account-modal'>Signup</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signup);
			model.addAttribute("hide","hidden='true'");
			model.addAttribute("creatorHide","hidden='true'");
		} else {
			System.out.println("Logged in as " + a.getUsername());
			// model.addAttribute("username", a.getUsername());
			String login = "<a href='/test/profile'>" + a.getUsername() + "</a>";
			String signout = "<a href='/test/signout' >Sign Out</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signout);
			model.addAttribute("hide", "");
		}
		
		//Need to identify is anonymous user
		String toPut = "";
		if (a == null){
			toPut = request.getRemoteAddr();
		}
		else {
			toPut = a.getUsername();
		}
		
		Poll poll = DBQuery.getPoll(Integer.parseInt(pollId));
		
		// Before doing anything, we need to confirm that they havent voted yet
		//String check = "SELECT * FROM PollTaker WHERE Username = '" + toPut + "' and PollNum = " + pollId
		//		+ ";";
		//Statement checkStatement = dbc.createStatement();
		//ResultSet checkRS = checkStatement.executeQuery(check);
		// If YES, This user has already voted
		// Else, they have not!
		if(poll!=null){
			for(int i = 0; i < poll.getPollData().getPollTakers().size(); i++){
				System.out.println(poll.getPollData().getPollTakers().get(i).getUsername() + " equals " + toPut);
				if (poll.getPollData().getPollTakers().get(i).getUsername().equals(toPut)) {
					return singlePollData(new Answer(), model, request, pollId);
				}
			}
		}
		// Getting Column names and username
		System.out.println("Starting dynamic url");
		//String searchQuery = "SELECT * FROM Polls p JOIN PollData on PollData.PollNum = p.PollNum "
		//		+ "WHERE p.PollNum = " + pollId + ";";
		//Statement statement = dbc.createStatement();
		//ResultSet rs = statement.executeQuery(searchQuery);
		if (poll!=null) {
			model.addAttribute("posterUsername", poll.getPollPoster());
			model.addAttribute("pollName", poll.getPollName());
			model.addAttribute("pollQuestion", poll.getPollQuestion());
			// Creating builder
			String builder = "";
			String[] options = new String[10];
			int[] values = new int[10];
			for (int i = 0; i < poll.getPollData().getAnswer().getAnswerOptions().size(); i++) {
				builder = builder + "<div class='radio'><label><input type='radio' name='answer' id='Private' value='"
						+ String.valueOf(i + 1) + "'/>" + poll.getPollData().getAnswer().getAnswerOptions().get(i) + "</label></div>";
				options[i] = poll.getPollData().getAnswer().getAnswerOptions().get(i);
				System.out.println("DEBUG: ANSWER OPTION " + (i+1) + ": "+ poll.getPollData().getAnswer().getAnswerOptions().get(i));
				values[i] = poll.getPollData().getAnswer().getAnswerChosen().get(i);
				System.out.println("DEBUG: ANSWER VALUE " + (i+1) + ": "+ poll.getPollData().getAnswer().getAnswerChosen().get(i));
			}
			String optionsList = "";
			String valuesList = "";
			for (int i = 0; i < poll.getPollData().getParams(); i++) {
				optionsList = optionsList + "'" + options[i] + "',";
				valuesList = valuesList + "'" + values[i] + "',";
			}
			model.addAttribute("pollDesc",poll.getPollDescription());
			model.addAttribute("optionsList", optionsList);
			model.addAttribute("valuesList", valuesList);
			model.addAttribute("builder", builder);
			model.addAttribute("pollID", pollId);
			//This will display the edit button if they are the creator
			if (a != null && poll.getPollPoster().equals(a.getUsername())){
				model.addAttribute("creatorHide","");
			}
			else if (((Administrator)request.getSession().getAttribute("admintoken")) != null){
				model.addAttribute("creatorHide", "");
			}
			else{
				model.addAttribute("creatorHide","hidden='true'");
			}
		}

		return new ModelAndView("singlepoll", "command", new User());
	}

	@RequestMapping(value = "/singlepolldata/{pollId}", method = RequestMethod.GET)
	public ModelAndView singlePollData(@ModelAttribute("SpringWeb") Answer answer, ModelMap model,
			HttpServletRequest request, @PathVariable String pollId) throws SQLException {
		// Confirming Login Status
		User a = (User) request.getSession().getAttribute("token");
		if (a == null) {
			System.out.println("User not logged in");
			// Login Modifier
			String login = "<a href='../navbar-static-top/' data-toggle='modal' data-target='#login-modal'>Login</a>";
			String signup = "<a href='../navbar-fixed-top/' data-toggle='modal' data-target='#create-account-modal'>Signup</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signup);
		} else {
			System.out.println("Logged in as " + a.getUsername());
			// model.addAttribute("username", a.getUsername());
			String login = "<a href='/test/profile'>" + a.getUsername() + "</a>";
			String signout = "<a href='/test/signout' >Sign Out</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signout);
			
		}

		// Getting Column names and username
		System.out.println("singlepolldataaaa");
		// If answer equals null, do nothing
		// Else put that in the DB;
		if (answer.getAnswer() == null) {
			System.out.println("We are coming without giving an answer");
		} else {
			System.out.println("We are giving an answer " + answer.getAnswer());
			String column = "";
			Poll poll = DBQuery.getPoll(Integer.parseInt(pollId));
			int ans = Integer.parseInt(answer.getAnswer());
			
			//update answer totals
			poll.getPollData().getAnswer().addAnswerChosen(ans-1, poll.getPollNum());
			
			//update partakers in poll
			poll.addPartaker();

			String toPut = "";
			if (a == null){
				toPut = request.getRemoteAddr();
			}
			else{
				toPut = a.getUsername();
			}
			poll.getPollData().addPollTaker(toPut, poll.getPollNum(),false, poll.getPollData().getAnswer().getAnswerChosen());
			String toSend = "";
			if (a == null){
				toSend = "";
			}
			else{
				toSend = a.getEmail();
			}
			String info = "Thank you for voting in the poll: " + poll.getPollName() + "! With your support, that poll will become more popular.\n\nThanks!\n\t-easyPoll Team";
			Email.sendMail(toSend, "Thank you for voting", info);
			System.out.println("Update complete");
		}

		String searchQuery = "SELECT * FROM Polls p JOIN PollData on PollData.PollNum = p.PollNum "
				+ "WHERE p.PollNum = " + pollId + ";";
		Statement statement = dbc.createStatement();
		ResultSet rs = statement.executeQuery(searchQuery);
		if (rs.next()) {
			model.addAttribute("posterUsername", rs.getString(2));
			model.addAttribute("pollName", rs.getString(4));
			model.addAttribute("pollQuestion", rs.getString(11));
			// Creating builder
			String builder = "";
			ArrayList<String> options = new ArrayList<String>();
			String[] values = new String[10];
			for (int i = 0; i < Integer.valueOf(rs.getString(10)); i++) {
				options.add(rs.getString(14 + i));
				values[i] = rs.getString(24 + i);
			}
			String optionsList = "";
			String valuesList = "";
			for (int i = 0; i < Integer.valueOf(rs.getString(10)); i++) {
				if(i != Integer.valueOf(rs.getString(10))-1)
				{
					optionsList = optionsList + "\"" + options.get(i) + "\", ";
					valuesList = valuesList + "\"" + values[i] + "\", ";
					
				}
				else
				{
					optionsList = optionsList + "\"" + options.get(i) + "\"";
					valuesList = valuesList + "\"" + values[i] + "\"";
					
				}
				
			}
			model.addAttribute("optionsList", optionsList);
			model.addAttribute("valuesList", valuesList);
			model.addAttribute("pollDesc",DBQuery.getPollDescription(pollId));
			model.addAttribute("pollID", pollId);
			if (a != null && rs.getString(2).equals(a.getUsername())){
				model.addAttribute("creatorHide","");
			}
			else if (((Administrator)request.getSession().getAttribute("admintoken")) != null){
				model.addAttribute("creatorHide", "");
			}
			else{
				model.addAttribute("creatorHide","hidden='true'");
			}
		}

		return new ModelAndView("singlepolldata");
	}

	@RequestMapping(value = "/signout", method = RequestMethod.GET)
	public String signout(@ModelAttribute("SpringWeb")User user, ModelMap model,
			HttpServletRequest request) throws SQLException{
		//Setting token to null so that the user no longer exist
		request.getSession().setAttribute("token", null);
		request.getSession().setAttribute("admintoken", null);
		String referer = request.getHeader("Referer");
	    return "redirect:"+ referer;
	}
	
	@RequestMapping(value = "/adminLogin", method = RequestMethod.POST)
	public String adminLogin(@ModelAttribute("SpringWeb")Administrator admin, ModelMap model,
		HttpServletRequest request) throws SQLException{
		Administrator logAdmin = null;
		
		logAdmin = Administrator.verifyAdmin(admin.getEmail(),admin.getPassword());
		System.out.println(logAdmin.getUsername());
		
		if(!logAdmin.getUsername().equals("")){
			System.out.println("Admin Logged in: " + logAdmin.getUsername());
			// If Authentication successful
			// Add these attributes to the model so they will appear
			request.getSession().setAttribute("admintoken", logAdmin);
			String referer = request.getHeader("Referer");
		    return "redirect:"+ referer;
		}
		else{
			return "redirect:http://localhost:8080/test/admin";
		}
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView admin(@ModelAttribute("SpringWeb")Administrator admin, ModelMap model,
		HttpServletRequest request) throws SQLException{
		Administrator a = (Administrator) request.getSession().getAttribute("admintoken");
		// Confirming Login Status
		
		Administrator ad;
		if (a == null){
			System.out.println("User not logged in");
			// Login Modifier
			String login = "<a href='../navbar-static-top/' data-toggle='modal' data-target='#login-modal'>Login</a>";
			String signup = "<a href='../navbar-fixed-top/' data-toggle='modal' data-target='#create-account-modal'>Signup</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signup);
			model.addAttribute("hide","hidden='true'");
		} else {
			System.out.println("Logged in as " + a.getUsername());
			String login = "<a href='#'>" + a.getUsername() + "</a>";
			String signout = "<a href='/test/signout' >Sign Out</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signout);
			ad = new Administrator(a.getUsername());
			ArrayList<ReportedQuestion> pollArr = ad.getReportedQuestions();
			HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
			for(ReportedQuestion rp : pollArr){
				Integer i = map.get(rp.getPollNum());
				if (i == null)
					map.put(rp.getPollNum(),1);
				else 
					map.put(rp.getPollNum(),i+1);
			}
			ArrayList<ReportedQuestion> polls = new ArrayList<ReportedQuestion>();
			ArrayList<Integer> poller = new ArrayList<Integer>();
			for(ReportedQuestion rp : pollArr){
				if (!poller.contains(rp.getPollNum())){
					polls.add(rp);
					poller.add(rp.getPollNum());
				}
			}
			System.out.println("---- " + polls.size());
			String thyme = "";
			for (int i =(polls.size()-1); i >= 0; i--){
				thyme = thyme + "<tr><td>"+polls.get(i).getPollName()+"</td><td hidden='true'>"+polls.get(i).getPollNum()+"</td><td>"+polls.get(i).getPollDescription()+"</td><td>"+polls.get(i).getUsername()+"</td><td>"+map.get(polls.get(i).getPollNum())+"</td></tr>";
				System.out.println(pollArr.get(i).getPollNum());
			}
			model.addAttribute("polls", thyme);
			model.addAttribute("hide","");
		}
		Boolean newsCheck = (Boolean)request.getSession().getAttribute("newsletter");
		if (newsCheck != null){
			model.addAttribute("sentNewsletter", "Newsletter Sent!");
			request.getSession().setAttribute("newsletter", null);
		}
		return new ModelAndView("admin", "command", new User());
	}
	
	@RequestMapping(value = "/recommend", method = RequestMethod.POST)
	public String recommendPoll(@ModelAttribute("SpringWeb")Email email, ModelMap model,
		HttpServletRequest request) throws SQLException{
		// Confirming Login Status
		User a = (User)request.getSession().getAttribute("token");
		if (a == null){
			System.out.println("User not logged in");
			String referer = request.getHeader("Referer");
		    return "redirect:"+ referer;
		} else {
			System.out.println("Logged in as " + a.getUsername());
			String login = "<a href='#'>" + a.getUsername() + "</a>";
			String signout = "<a href='/test/signout' >Sign Out</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signout);
		}
		System.out.println(email.getAddress());
		String info = "To whom it may concern,\n\nA friend of yours is interested in getting you opinion on "
				+ "a poll! Follow the link to learn more...\n\n" + request.getHeader("Referer") + "\n\nGot "
				+ "a question you'd like to ask a vibrant community of polltakers? Visit us at easyPoll.com\n\n "
				+ "All the best!\n\t-easyPoll Team";
		System.out.println(info);
		Email.sendMail(email.getAddress(), "You've been invited to a poll!", info);
		String referer = request.getHeader("Referer");
	    return "redirect:"+ referer;
	}
	
	@RequestMapping(value = "/sendnewsletter", method = RequestMethod.POST)
	public String sendnewsletter(@RequestParam("textarea")String textarea, ModelMap model,
		HttpServletRequest request) throws SQLException{
		// Confirming Login Status
		System.out.println("Sending news letter");
		System.out.println(textarea);
		request.getSession().setAttribute("newsletter", true);
		Email.sendMassMail(textarea);
		
		String referer = request.getHeader("Referer");
	    return "redirect:"+ referer;
	}
	
	@RequestMapping(value = "/report", method = RequestMethod.POST)
	public String report(@ModelAttribute("SpringWeb")User user, ModelMap model,
		HttpServletRequest request) throws SQLException{
		// Confirming Login Status
		User a = (User) request.getSession().getAttribute("token");
		
		System.out.println("$%$%$@%$%$%$%$%$%$%$%$%$%$%$%$%$%$%$%$%$");
		String referer = request.getHeader("Referer");
		int index= referer.lastIndexOf("/");
		int pollNum = Integer.valueOf(referer.substring(index+1));
		ReportedQuestion.addReportedQuestion(a.getUsername(), pollNum);
		System.out.println(a.getUsername());
		
	    return "redirect:"+referer;
	}
	
	@RequestMapping(value = "/editPoll/{pollId}", method = RequestMethod.POST)
	public ModelAndView editpoll(@ModelAttribute("SpringWeb")User user, ModelMap model,
		HttpServletRequest request, @PathVariable String pollId) throws SQLException{
		// Confirming Login Status, this person must be the poll creator
		User a = (User)request.getSession().getAttribute("token");
		if (a == null){
			System.out.println("User not logged in");
		    return new ModelAndView("redirect:http://localhost:8080/test/home");
		} else {
			System.out.println("Logged in as " + a.getUsername());
			String login = "<a href='#'>" + a.getUsername() + "</a>";
			String signout = "<a href='/test/signout' >Sign Out</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signout);
		}
		int num = Poll.getTotalPoll();
		model.addAttribute("numberPolls", String.valueOf(num));
		model.addAttribute("pollId", pollId);
		Poll poll = new Poll(Integer.valueOf(pollId));
		model.addAttribute("pollName", poll.getPollName());
		model.addAttribute("pollQuestion", poll.getPollDescription());
		model.addAttribute("pollDesc", poll.getPollDescription());
		model.addAttribute("endTotal", poll.getEndTotal());
		if(poll.getPollType() == null){
			model.addAttribute("isPublic", "checked='checked'");
		}
		else if (poll.getPollType().equals("public"))
			model.addAttribute("isPublic", "checked='checked'");
		else
			model.addAttribute("isPrivate", "checked='checked'");
		
	    return new ModelAndView("editpoll", "command", new User());
	}
	
	@RequestMapping(value = "/updatePoll/{pollId}", method = RequestMethod.POST)
	public View updatePoll(@ModelAttribute("SpringWeb")Poll poll, ModelMap model,
		HttpServletRequest request, @PathVariable String pollId) throws SQLException{
		// Confirming Login Status, this person must be the poll creator
		User a = (User)request.getSession().getAttribute("token");
		if (a == null){
			System.out.println("User not logged in");
			 RedirectView redirect = new RedirectView("/test/home/");
			 redirect.setExposeModelAttributes(false);
			 return redirect;
		} else {
			System.out.println("Logged in as " + a.getUsername());
			String login = "<a href='#'>" + a.getUsername() + "</a>";
			String signout = "<a href='/test/signout' >Sign Out</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signout);
		}
		
		Poll.updatePoll(Integer.valueOf(pollId), poll.getPollName(), poll.getPollQuestion(), 
				poll.getPollDescription(), poll.getPollType(), poll.getEndTotal());
		
	    RedirectView redirect = new RedirectView("/test/singlepoll/"+pollId);
	    redirect.setExposeModelAttributes(false);
	    return redirect;
	}
	
	@RequestMapping(value = "/deletePoll/{pollId}", method = RequestMethod.POST)
	public View deletePoll(@ModelAttribute("SpringWeb")Poll poll, ModelMap model,
		HttpServletRequest request, @PathVariable String pollId) throws SQLException{
		// Confirming Login Status, this person must be the poll creator
		User a = (User)request.getSession().getAttribute("token");
		if (a == null){
			System.out.println("User not logged in");
			 RedirectView redirect = new RedirectView("/test/home/");
			 return redirect;
		} else {
			System.out.println("Logged in as " + a.getUsername());
			String login = "<a href='#'>" + a.getUsername() + "</a>";
			String signout = "<a href='/test/signout' >Sign Out</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signout);
		}
		Poll.deletePoll(Integer.valueOf(pollId));
		
		RedirectView redirect = null;
		if (a != null){
			redirect = new RedirectView("/test/mypolls");
		}
		else if (((Administrator)request.getSession().getAttribute("admintoken")) != null){
			redirect = new RedirectView("/test/admin");
		}
	    redirect.setExposeModelAttributes(false);
	    return redirect;
	}
	
	@RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
	public View forgotPassword(@RequestParam("email")String email, ModelMap model,
		HttpServletRequest request) throws SQLException{
		// Confirming Login Status, this person must be the poll creator
		User a = (User)request.getSession().getAttribute("token");
		if (a == null){
			System.out.println("User not logged in");
			 RedirectView redirect = new RedirectView("/test/home/");
			 return redirect;
		} else {
			System.out.println("Logged in as " + a.getUsername());
			String login = "<a href='#'>" + a.getUsername() + "</a>";
			String signout = "<a href='/test/signout' >Sign Out</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signout);
		}
		User.forgotPassword(email);
		
		RedirectView redirect = null;
		redirect = new RedirectView("/test/home");
	    redirect.setExposeModelAttributes(false);
	    return redirect;
	}
	
	@RequestMapping(value = "/deleteaccount", method = RequestMethod.POST)
	public View deleteAccount(@ModelAttribute("SpringWeb")Poll poll, ModelMap model,
		HttpServletRequest request) throws SQLException{
		// Confirming Login Status, this person must be the poll creator
		User a = (User)request.getSession().getAttribute("token");
		if (a == null){
			System.out.println("User not logged in");
			 RedirectView redirect = new RedirectView("/test/home/");
			 return redirect;
		} else {
			System.out.println("Logged in as " + a.getUsername());
			System.out.println(a.getEmail());
			String login = "<a href='#'>" + a.getUsername() + "</a>";
			String signout = "<a href='/test/signout' >Sign Out</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signout);
		}
		User.deleteAccount(a.getEmail());
		System.out.println("HELLLLLLLLLLLO");
		signout(new User(), model, request);
		System.out.println("HELLLLLLLLLLLO");
		RedirectView redirect = null;
		redirect = new RedirectView("/test/home");
	    redirect.setExposeModelAttributes(false);
	    return redirect;
	}
}
