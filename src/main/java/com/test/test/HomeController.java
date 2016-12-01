package com.test.test;

import java.io.IOException;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	
	//DELETE IF NOT NEEDED
//	@Resource
//	WebServiceContext context;

	// Root mapping
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView user() {
		return new ModelAndView("index", "command", new RUser());
	}

	// Root mapping
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home(@ModelAttribute("SpringWeb") RUser user, ModelMap model, HttpServletRequest request) throws SQLException {
		// Confirming Login Status
		RUser a = (RUser) request.getSession().getAttribute("token");
		if (a == null) {
			System.out.println("RUser not logged in");
			// Login Modifier
			String login = "<a href='../navbar-static-top/' data-toggle='modal' data-target='#login-modal'>Login</a>";
			String signup = "<a href='../navbar-fixed-top/' data-toggle='modal' data-target='#create-account-modal'>Signup</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signup);
		} else {
			System.out.println("Logged in as " + a.getUsername());
			// model.addAttribute("username", a.getRUsername());
			String login = "<a href='/test/profile'>" + a.getUsername() + "</a>";
			String signout = "<a href='/test/signout' >Sign Out</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signout);
		}
		
		ArrayList<Poll> polls = RUser.getPolls();
		if (polls.size() > 3){
			for(int i = (polls.size()-1), j = 0; i > (polls.size()-5); i--, j++){
				model.addAttribute("title"+String.valueOf(j),polls.get(i).getPollName());
				model.addAttribute("pollDesc"+String.valueOf(j),polls.get(i).getPollDescription());
				model.addAttribute("pollId"+String.valueOf(j),polls.get(i).getPollNum());
			}
		}
		
		return new ModelAndView("home", "command", new RUser());
	}

	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public ModelAndView about(@ModelAttribute("SpringWeb") RUser user, ModelMap model, HttpServletRequest request) {
		// Confirming Login Status
		RUser a = (RUser) request.getSession().getAttribute("token");
		if (a == null) {
			System.out.println("RUser not logged in");
			// Login Modifier
			String login = "<a href='../navbar-static-top/' data-toggle='modal' data-target='#login-modal'>Login</a>";
			String signup = "<a href='../navbar-fixed-top/' data-toggle='modal' data-target='#create-account-modal'>Signup</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signup);
		} else {
			System.out.println("Logged in as " + a.getUsername());
			// model.addAttribute("username", a.getRUsername());
			String login = "<a href='/test/profile'>" + a.getUsername() + "</a>";
			String signout = "<a href='/test/signout' >Sign Out</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signout);
		}

		return new ModelAndView("about", "command", new RUser());
	}

	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public ModelAndView contact(@ModelAttribute("SpringWeb") RUser user, ModelMap model, HttpServletRequest request) {
		// Confirming Login Status
		RUser a = (RUser) request.getSession().getAttribute("token");
		if (a == null) {
			System.out.println("RUser not logged in");
			// Login Modifier
			String login = "<a href='../navbar-static-top/' data-toggle='modal' data-target='#login-modal'>Login</a>";
			String signup = "<a href='../navbar-fixed-top/' data-toggle='modal' data-target='#create-account-modal'>Signup</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signup);
		} else {
			System.out.println("Logged in as " + a.getUsername());
			// model.addAttribute("username", a.getRUsername());
			String login = "<a href='/test/profile'>" + a.getUsername() + "</a>";
			String signout = "<a href='/test/signout' >Sign Out</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signout);
		}

		return new ModelAndView("contact", "command", new RUser());
	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView profile(@ModelAttribute("SpringWeb") RUser user, ModelMap model, HttpServletRequest request)
			throws SQLException {
		// Confirming Login Status
		RUser a = (RUser) request.getSession().getAttribute("token");
		if (a == null) {
			System.out.println("RUser not logged in");
			// Login Modifier
			String login = "<a href='../navbar-static-top/' data-toggle='modal' data-target='#login-modal'>Login</a>";
			String signup = "<a href='../navbar-fixed-top/' data-toggle='modal' data-target='#create-account-modal'>Signup</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signup);
		} else {
			System.out.println("Logged in as " + a.getUsername());
			// model.addAttribute("username", a.getRUsername());
			String login = "<a href='/test/profile'>" + a.getUsername() + "</a>";
			String signout = "<a href='/test/signout' >Sign Out</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signout);
		}
		
		int counter = RUser.getMyPollsCount(a.getUsername());
		model.addAttribute("numPolls", counter);

		int votedCounter = RUser.getMyPollsVoted(a.getUsername());
		model.addAttribute("numVoted", votedCounter);

		String mostVoted = RUser.getMyPollsMostVoted(a.getUsername());
		model.addAttribute("fave", mostVoted);

		model.addAttribute("userName", a.getUsername());
		
		model.addAttribute("email",a.getEmail());
		
		return new ModelAndView("userprofile", "command", new RUser());
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public RedirectView addRUser(@ModelAttribute("SpringWeb") RUser user, ModelMap model, HttpServletRequest request)
			throws SQLException {
		// Authentication
		RUser ruser = new RUser();
		System.out.println("RUser email: " + user.getEmail());
		System.out.println("RUser password: " + user.getPassword());
		ruser = RUser.verifyUser(user.getEmail(), user.getPassword());
		
			if(!ruser.getUsername().equals("")){
				System.out.println("RUser Logged in: " + ruser.getUsername());
				// If Authentication successful
				// Add these attributes to the model so they will appear
				model.addAttribute("username", ruser.getUsername());
				request.getSession().setAttribute("token", ruser);
	
				RUser a = (RUser) request.getSession().getAttribute("token");
				if (a == null) {
					System.out.println("RUser not logged in");
					// Login Modifier
					String login = "<a href='../navbar-static-top/' data-toggle='modal' data-target='#login-modal'>Login</a>";
					String signup = "<a href='../navbar-fixed-top/' data-toggle='modal' data-target='#create-account-modal'>Signup</a>";
					model.addAttribute("login", login);
					model.addAttribute("signup", signup);
					System.out.println("Failure To Login");
					String referer = request.getHeader("Referer");
					RedirectView redirect = new RedirectView(referer);
				    redirect.setExposeModelAttributes(false);
				    return redirect;
				}
				 else {
					System.out.println("Logged in as " + a.getUsername());
					// model.addAttribute("username", a.getRUsername());
					String login = "<a href='/test/profile'>" + a.getUsername() + "</a>";
					String signout = "<a href='/test/signout' >Sign Out</a>";
					model.addAttribute("login", login);
					model.addAttribute("signup", signout);
				}
			}
			else{
				System.out.println("RUser failed to login");
				String referer = request.getHeader("Referer");
				RedirectView redirect = new RedirectView(referer);
			    redirect.setExposeModelAttributes(false);
			    return redirect;
			}
			String referer = request.getHeader("Referer");
			if (referer.equals("http://localhost:8080/test/")){
				RedirectView redirect = new RedirectView("http://localhost:8080/test/home");
				redirect.setExposeModelAttributes(false);
				return redirect;
			}
			else {
				RedirectView redirect = new RedirectView(referer);
				redirect.setExposeModelAttributes(false);
				return redirect;
			}
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public RedirectView register(@ModelAttribute("SpringWeb") RUser user, ModelMap model, HttpServletRequest request)
			throws SQLException {
		// Check if user exists in database
		String username = user.getUsername();
		String password = user.getPassword();
		String email = user.getEmail();

		if(RUser.checkUser(username)){
			System.out.println("RUser already exists");
		} else {
			// Insert the user into the database	
			RUser.createRUser(username,password,email);
		}
		RUser toAdd = new RUser();
		toAdd.setUsername(user.getUsername());
		toAdd.setEmail(user.getEmail());
		toAdd.setPassword(user.getPassword());
		return addRUser(toAdd, model, request);
	}

	@RequestMapping(value = "/mypolls", method = RequestMethod.GET)
	public ModelAndView mypolls(@ModelAttribute("SpringWeb") RUser user, ModelMap model, HttpServletRequest request)
			throws SQLException {
		// Confirming Login status
		RUser a = (RUser) request.getSession().getAttribute("token");
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
		
		ArrayList<Poll> myPolls = RUser.getMyPolls(username);

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
		return new ModelAndView("mypolls", "command", new RUser());
	}

	@RequestMapping(value = "/communitypolls", method = RequestMethod.GET)
	public ModelAndView community(@ModelAttribute("SpringWeb") RUser user, ModelMap model, HttpServletRequest request) throws SQLException {
		// Confirming Login Status
		RUser a = (RUser) request.getSession().getAttribute("token");
		if (a == null) {
			System.out.println("RUser not logged in");
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
		
		ArrayList<Poll> pollArr = RUser.getPublicPolls();
		
		
		String thyme = "";
		for (int i =(pollArr.size()-1); i >= 0; i--){
			thyme = thyme + "<tr><td>"+pollArr.get(i).getPollName()+"</td><td hidden='true'>"+pollArr.get(i).getPollNum()+"</td><td>"+pollArr.get(i).getPollDescription()+"</td><td>"+pollArr.get(i).getPollPoster()+"</td></tr>";
		}
		model.addAttribute("polls", thyme);
		
		String pollOfTheDay = "";
		Poll potd = Poll.getPollOfTheDay();
		pollOfTheDay = "<tr><td>"+potd.getPollName()+"</td><td hidden='true'>"+potd.getPollNum()+"</td><td>"+potd.getPollDescription()+"</td><td>"+potd.getPollPoster()+"</td></tr>";
		model.addAttribute("pollOfTheDay", pollOfTheDay);
		
		return new ModelAndView("communitypolls", "command", new RUser());
	}

	@RequestMapping(value = "/createpoll", method = RequestMethod.GET)
	public ModelAndView createpoll(@ModelAttribute("SpringWeb") RUser user, ModelMap model, HttpServletRequest request) throws SQLException {
		int num = Poll.getTotalPoll();
		RUser a = (RUser) request.getSession().getAttribute("token");
		if (a == null) {
			System.out.println("RUser not logged in");
			// Login Modifier
			String login = "<a href='../navbar-static-top/' data-toggle='modal' data-target='#login-modal'>Login</a>";
			String signup = "<a href='../navbar-fixed-top/' data-toggle='modal' data-target='#create-account-modal'>Signup</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signup);
		} else {
			System.out.println("Logged in as " + a.getUsername());
			// model.addAttribute("username", a.getRUsername());
			String login = "<a href='/test/profile'>" + a.getUsername() + "</a>";
			String signout = "<a href='/test/signout' >Sign Out</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signout);
		}
		model.addAttribute("numberPolls", String.valueOf(num));
		return new ModelAndView("createpoll", "command" ,new RUser());
	}

	@RequestMapping(value = "/createpollfunction", method = RequestMethod.POST)
	public @ResponseBody ModelAndView createpollfunction(@ModelAttribute("SpringWeb") Poll poll, ModelMap model,
			HttpServletRequest request) throws SQLException {
		System.out.println("Starting function");
		RUser a = (RUser) request.getSession().getAttribute("token");
		model.addAttribute("username", a.getUsername());
		System.out.println(poll.getPollName());
		System.out.println(poll.getPollQuestion());
		System.out.println(poll.getPollDescription());
		System.out.println(poll.getEndTotal());
		if (poll.getEndTotal() == 0)
			poll.setEndTotal(10);

		// Splitting answers by comma delimeters
		String[] answers = poll.getAnswerParams().split(",");
		ArrayList<String> answersArray = new ArrayList<String>();

		for (int i = 0; i < answers.length; i++) {
			if (i < answers.length)
				answersArray.add(answers[i]);
		}
		
		System.out.println(">>> " + answersArray.toString());
		
		RUser.addPoll(poll, a.getUsername(), answersArray);
		
		return mypolls(new RUser(), model, request);
	}

	@RequestMapping(value = "/singlepoll/{pollId}", method = RequestMethod.GET)
	public ModelAndView singlePoll(@ModelAttribute("SpringWeb") RUser user, ModelMap model, HttpServletRequest request,
			@PathVariable String pollId) throws SQLException {
		// Confirming Login Status
		Administrator ad = (Administrator) request.getSession().getAttribute("admintoken");
		RUser a = (RUser) request.getSession().getAttribute("token");
		if (ad != null){
			System.out.println("Logged in as " + ad.getUsername());
			// model.addAttribute("username", a.getRUsername());
			String login = "<a href='/test/profile'>" + ad.getUsername() + "</a>";
			String signout = "<a href='/test/signout' >Sign Out</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signout);
			model.addAttribute("hide","hidden='true'");
			model.addAttribute("creatorHide","hidden='true'");
		}
		else if (a == null) {
			System.out.println("RUser not logged in");
			// Login Modifier
			String login = "<a href='../navbar-static-top/' data-toggle='modal' data-target='#login-modal'>Login</a>";
			String signup = "<a href='../navbar-fixed-top/' data-toggle='modal' data-target='#create-account-modal'>Signup</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signup);
			model.addAttribute("hide","hidden='true'");
			model.addAttribute("creatorHide","hidden='true'");
		} else {
			System.out.println("Logged in as " + a.getUsername());
			// model.addAttribute("username", a.getRUsername());
			String login = "<a href='/test/profile'>" + a.getUsername() + "</a>";
			String signout = "<a href='/test/signout' >Sign Out</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signout);
			model.addAttribute("hide", "");
		}
		
		//Check the isCurrent status
		Poll poll = DBQuery.getPoll(Integer.parseInt(pollId));
		Poll.checkCurrent(Integer.parseInt(pollId));
		poll = DBQuery.getPoll(Integer.parseInt(pollId));
		
		//Need to identify is anonymous user
		String toPut = "";
		if (a == null){
			toPut = request.getRemoteAddr();
		}
		else {
			toPut = a.getUsername();
		}
		
		// Before doing anything, we need to confirm that they havent voted yet
		//String check = "SELECT * FROM PollTaker WHERE RUsername = '" + toPut + "' and PollNum = " + pollId
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
		if(poll!=null){
			//If isCurrent is 0 show data
			if(poll.getIsCurrent().equals("0")){
				return singlePollData(new Answer(), model, request, pollId);
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
			int pollTakerCount = Poll.pollTakerCount(Integer.parseInt(pollId));
			int endTotalCount = Poll.endTotalCount(Integer.parseInt(pollId));
			model.addAttribute("counts", String.valueOf(pollTakerCount)+"/"+String.valueOf(endTotalCount)+" Votes Cast");
			if (pollTakerCount >= endTotalCount){
				model.addAttribute("isCurrent", "Poll is Closed");
			}
			else{
				model.addAttribute("isCurrent", "Poll is Ongoing");
			}
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

		return new ModelAndView("singlepoll", "command", new RUser());
	}

	@RequestMapping(value = "/singlepolldata/{pollId}", method = RequestMethod.POST)
	public ModelAndView singlePollData(@ModelAttribute("SpringWeb") Answer answer, ModelMap model,
			HttpServletRequest request, @PathVariable String pollId) throws SQLException {
		// Confirming Login Status
		RUser a = (RUser) request.getSession().getAttribute("token");
		if (a == null) {
			System.out.println("RUser not logged in");
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
		
		System.out.println("singlepolldataaaa");
		// If answer equals null, do nothing
		// Else put that in the DB;
		if (answer.getAnswer() == null) {
			System.out.println("We are coming without giving an answer");
		} else {
			System.out.println("We are giving an answer " + answer.getAnswer());
			//String column = "";
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
			
			ArrayList<Integer> userAnswer = new ArrayList<Integer>();
			for(int i = 1; i < poll.getPollData().getAnswer().getAnswerChosen().size()+1; i++){
				if(i == ans){
					userAnswer.add(1);
				}
				else
					userAnswer.add(0);
			}
			
			poll.getPollData().addPollTaker(toPut, poll.getPollNum(),false, userAnswer);
			String toSend = "";
			if (a == null){
				toSend = "red@nomailhaeinf.com";
			}
			else{
				toSend = a.getEmail();
			}
			String info = "Thank you for voting in the poll: " + poll.getPollName() + "! With your support, that poll will become more popular.\n\nThanks!\n\t-easyPoll Team";
			ExecutorService executor = Executors.newFixedThreadPool(2);
			
			//Email.sendMail(toSend, "Thank you for voting", info);
			FutureTask futureTask_1 = new FutureTask((Callable) new CallableSendMail(toSend, "Thank you for voting", info));
			executor.execute(futureTask_1);
			System.out.println("Update complete");
		}

		ResultSet rs = Poll.resultSetPoll(pollId);
		if (rs.next()) {
			model.addAttribute("posterUsername", rs.getString(2));
			model.addAttribute("pollName", rs.getString(4));
			model.addAttribute("pollQuestion", rs.getString(11));
			// Creating builder
			//String builder = "";
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
			int pollTakerCount = Poll.pollTakerCount(Integer.parseInt(pollId));
			int endTotalCount = Poll.endTotalCount(Integer.parseInt(pollId));
			model.addAttribute("counts", String.valueOf(pollTakerCount)+"/"+String.valueOf(endTotalCount)+" Votes Cast");
			if (Poll.isCurrent(Integer.parseInt(pollId))){
				model.addAttribute("isCurrent", "Poll is Ongoing");
			}
			else{
				model.addAttribute("isCurrent", "Poll is Closed");
			}
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
	public String signout(@ModelAttribute("SpringWeb")RUser user, ModelMap model,
			HttpServletRequest request) throws SQLException{
		//Setting token to null so that the user no longer exist
		request.getSession().setAttribute("token", null);
		request.getSession().setAttribute("admintoken", null);
		String referer = "http://localhost:8080/test/home";
	    return "redirect:"+ referer;
	}
	
	@RequestMapping(value = "/adminregister", method = RequestMethod.POST)
	public String adminRegister(@ModelAttribute("SpringWeb")Administrator admin, ModelMap model,
		HttpServletRequest request) throws SQLException {
		Administrator logAdmin = null;
		
		System.out.println("$$$$$$$ " + admin.getUsername());
		System.out.println("$$$$$$$ " + admin.getPassword());
		System.out.println("$$$$$$$ " + admin.getEmail());
		
		if (!admin.getEmail().equals("easyPollAdmin"))
			return "redirect:http://localhost:8080/test/admin";
		
		Administrator.createAdmin(admin.getUsername(), admin.getPassword());
		logAdmin = Administrator.verifyAdmin(admin.getUsername()+"@easypoll.com", admin.getPassword());
		
		if(!logAdmin.getUsername().equals("")){
			System.out.println("Admin Logged in: " + logAdmin.getUsername());
			request.getSession().setAttribute("admintoken", logAdmin);
			String referer = request.getHeader("Referer");
		    return "redirect:"+ referer;
		}
		else{
			return "redirect:http://localhost:8080/test/admin";
		}
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
			System.out.println("RUser not logged in");
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
			
			String thyme = "";
			for (int i =(polls.size()-1); i >= 0; i--){
				thyme = thyme + "<tr><td>"+polls.get(i).getPollName()+"</td><td hidden='true'>"+polls.get(i).getPollNum()+"</td><td>"+polls.get(i).getPollDescription()+"</td><td>"+polls.get(i).getUsername()+"</td><td>"+map.get(polls.get(i).getPollNum())+"</td></tr>";
			}
			
			String feedback = "";
			ArrayList<String> messages = Administrator.getFeedback();
			for (int i=0; i< messages.size(); i++){
				feedback = feedback + "<tr><td>"+messages.get(i)+"</td></tr>";
			}
			
			String supportTicket = "";
			ArrayList<Administrator> tickets = Administrator.getSupportTickets();
			System.out.println("This is the size of support ticket: " + tickets.size());
			for (int i=0; i < tickets.size(); i++){
				supportTicket = supportTicket + "<tr><td>"+tickets.get(i).getPassword()+"</td><td>"+tickets.get(i).getUsername()+"</td><td>"+tickets.get(i).getEmail()+"</td></tr>";
			}
			
			model.addAttribute("supportTicket", supportTicket);
			model.addAttribute("feedback", feedback);
			model.addAttribute("polls", thyme);
			model.addAttribute("hide","");
		}
		Boolean newsCheck = (Boolean)request.getSession().getAttribute("newsletter");
		if (newsCheck != null){
			model.addAttribute("sentNewsletter", "Newsletter Sent!");
			request.getSession().setAttribute("newsletter", null);
		}
		return new ModelAndView("admin", "command", new RUser());
	}
	
	@RequestMapping(value = "/recommend", method = RequestMethod.POST)
	public RedirectView recommendPoll(@ModelAttribute("SpringWeb")Email email, ModelMap model,
		HttpServletRequest request) throws SQLException{
		// Confirming Login Status
		RUser a = (RUser)request.getSession().getAttribute("token");
		if (a == null){
			System.out.println("RUser not logged in");
			String referer = request.getHeader("Referer");
			RedirectView redirect = new RedirectView(referer);
		    redirect.setExposeModelAttributes(false);
		    return redirect;
		} else {
			System.out.println("Logged in as " + a.getUsername());
			String login = "<a href='#'>" + a.getUsername() + "</a>";
			String signout = "<a href='/test/signout' >Sign Out</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signout);
		}
		//System.out.println(email.getAddress());
		String info = "To whom it may concern,\n\nA friend of yours is interested in getting you opinion on "
				+ "a poll! Follow the link to learn more...\n\n" + request.getHeader("Referer") + "\n\nGot "
				+ "a question you'd like to ask a vibrant community of polltakers? Visit us at easyPoll.com\n\n "
				+ "All the best!\n\t-easyPoll Team";
		System.out.println(info);
		//Email.sendMail(email.getAddress(), "You've been invited to a poll!", info);
		ExecutorService executor = Executors.newFixedThreadPool(2);
		@SuppressWarnings({ "rawtypes", "unchecked" })
		FutureTask futureTask_1 = new FutureTask((Callable) new CallableSendMail(email.getAddress(), "You've been invited to a poll!", info));
		executor.execute(futureTask_1);
		String referer = request.getHeader("Referer");
		RedirectView redirect = new RedirectView(referer);
	    redirect.setExposeModelAttributes(false);
	    return redirect;

	}
	
	@RequestMapping(value = "/sendnewsletter", method = RequestMethod.POST)
	public String sendnewsletter(@RequestParam("textarea")String textarea, ModelMap model,
		HttpServletRequest request) throws SQLException{
		// Confirming Login Status
		System.out.println("Sending news letter");
		System.out.println(textarea);
		request.getSession().setAttribute("newsletter", true);
		ExecutorService executor = Executors.newFixedThreadPool(2);
		FutureTask futureTask_1 = new FutureTask((Callable) new CallableSendMassMail(textarea));
		executor.execute(futureTask_1);
		
		String referer = request.getHeader("Referer");
	    return "redirect:"+ referer;
	}
	
	@RequestMapping(value = "/report", method = RequestMethod.POST)
	public String report(@ModelAttribute("SpringWeb")RUser user, ModelMap model,
		HttpServletRequest request) throws SQLException{
		// Confirming Login Status
		RUser a = (RUser) request.getSession().getAttribute("token");
		
		System.out.println("$%$%$@%$%$%$%$%$%$%$%$%$%$%$%$%$%$%$%$%$");
		String referer = request.getHeader("Referer");
		int index= referer.lastIndexOf("/");
		int pollNum = Integer.valueOf(referer.substring(index+1));
		ReportedQuestion.addReportedQuestion(a.getUsername(), pollNum);
		System.out.println(a.getUsername());
		
	    return "redirect:"+referer;
	}
	
	@RequestMapping(value = "/editPoll/{pollId}", method = RequestMethod.POST)
	public ModelAndView editpoll(@ModelAttribute("SpringWeb")RUser user, ModelMap model,
		HttpServletRequest request, @PathVariable String pollId) throws SQLException{
		// Confirming Login Status, this person must be the poll creator
		RUser a = (RUser)request.getSession().getAttribute("token");
		if (a == null){
			System.out.println("RUser not logged in");
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
		model.addAttribute("pollQuestion", poll.getPollQuestion());
		model.addAttribute("pollDesc", poll.getPollDescription());
		model.addAttribute("endTotal", poll.getEndTotal());
		if(poll.getPollType() == null){
			model.addAttribute("isPublic", "checked='checked'");
		}
		else if (poll.getPollType().equals("public"))
			model.addAttribute("isPublic", "checked='checked'");
		else
			model.addAttribute("isPrivate", "checked='checked'");
		
	    return new ModelAndView("editpoll", "command", new RUser());
	}
	
	@RequestMapping(value = "/updatePoll/{pollId}", method = RequestMethod.POST)
	public View updatePoll(@ModelAttribute("SpringWeb")Poll poll, ModelMap model,
		HttpServletRequest request, @PathVariable String pollId) throws SQLException{
		// Confirming Login Status, this person must be the poll creator
		RUser a = (RUser)request.getSession().getAttribute("token");
		if (a == null){
			System.out.println("RUser not logged in");
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
		Administrator ad = (Administrator) request.getSession().getAttribute("admintoken");
		RUser a = (RUser)request.getSession().getAttribute("token");
		if (ad != null){
			//Admin is here
			System.out.println("Logged in as " + ad.getUsername());
			String login = "<a href='#'>" + ad.getUsername() + "</a>";
			String signout = "<a href='/test/signout' >Sign Out</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signout);
		}
		else if (a == null){
			System.out.println("RUser not logged in");
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
		if (ad != null){
			redirect = new RedirectView("/test/admin");
		}
		else if (a != null){
			redirect = new RedirectView("/test/mypolls");
		}
	    redirect.setExposeModelAttributes(false);
	    return redirect;
	}
	
	@RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
	public View forgotPassword(@RequestParam("email")String email, ModelMap model,
		HttpServletRequest request) throws SQLException{
		
		RUser.forgotPassword(email);
		
		RedirectView redirect = null;
		redirect = new RedirectView("/test/home");
	    redirect.setExposeModelAttributes(false);
	    return redirect;
	}
	
	@RequestMapping(value = "/deleteaccount", method = RequestMethod.POST)
	public View deleteAccount(@ModelAttribute("SpringWeb")Poll poll, ModelMap model,
		HttpServletRequest request) throws SQLException{
		// Confirming Login Status, this person must be the poll creator
		RUser a = (RUser)request.getSession().getAttribute("token");
		if (a == null){
			System.out.println("RUser not logged in");
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
		RUser.deleteAccount(a.getUsername());
		System.out.println("HELLLLLLLLLLLO");
		signout(new RUser(), model, request);
		System.out.println("HELLLLLLLLLLLO");
		RedirectView redirect = null;
		redirect = new RedirectView("/test/home");
	    redirect.setExposeModelAttributes(false);
	    return redirect;
	}
	
	@RequestMapping(value = "/cancelPoll/{pollId}", method = RequestMethod.POST)
	public View cancelPoll(@ModelAttribute("SpringWeb")Poll poll, ModelMap model,
		HttpServletRequest request, @PathVariable String pollId) throws SQLException{
		// Confirming Login Status, this person must be the poll creator
		RUser a = (RUser)request.getSession().getAttribute("token");
		if (a == null){
			System.out.println("RUser not logged in");
			 RedirectView redirect = new RedirectView("/test/home/");
			 return redirect;
		} else {
			System.out.println("Logged in as " + a.getUsername());
			String login = "<a href='#'>" + a.getUsername() + "</a>";
			String signout = "<a href='/test/signout' >Sign Out</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signout);
		}
		Poll.cancelPoll(Integer.valueOf(pollId));
		
		RedirectView redirect = null;
		if (a != null){
			redirect = new RedirectView("/test/mypolls");
		}
	    redirect.setExposeModelAttributes(false);
	    return redirect;
	}
	
	@RequestMapping(value = "/updateaccount", method = RequestMethod.POST)
	public View updateAccount(@ModelAttribute("SpringWeb")RUser user, ModelMap model,
		HttpServletRequest request) throws SQLException{
		// Confirming Login Status, this person must be the poll creator
		RUser a = (RUser)request.getSession().getAttribute("token");
		if (a == null){
			System.out.println("RUser not logged in");
			 RedirectView redirect = new RedirectView("/test/home/");
			 return redirect;
		} else {
			System.out.println("Logged in as " + a.getUsername());
			String login = "<a href='#'>" + a.getUsername() + "</a>";
			String signout = "<a href='/test/signout' >Sign Out</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signout);
		}
		RUser.updateAccount(a.getUsername(), user.getUsername(), user.getEmail(), user.getPassword());
		
		RedirectView redirect = null;
		if (a != null){
			redirect = new RedirectView("/test/profile");
		}
	    redirect.setExposeModelAttributes(false);
	    return redirect;
	}
	
	@RequestMapping(value = "/downloadPoll/{pollId}", method = RequestMethod.POST)
	public View downloadpoll(@ModelAttribute("SpringWeb")RUser user, ModelMap model,
		HttpServletRequest request, @PathVariable String pollId, HttpServletResponse response) throws SQLException, IOException{
		// Confirming Login Status, this person must be the poll creator
		RUser a = (RUser)request.getSession().getAttribute("token");
		if (a == null){
			System.out.println("RUser not logged in");
			 RedirectView redirect = new RedirectView("/test/home/");
			 return redirect;
		} else {
			System.out.println("Logged in as " + a.getUsername());
			String login = "<a href='#'>" + a.getUsername() + "</a>";
			String signout = "<a href='/test/signout' >Sign Out</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signout);
		}
		Poll poll = new Poll(Integer.valueOf(pollId));
		
		response.setContentType("text/csv");
		String reportName = "CSV_Report_Name.csv";
		response.setHeader("Content-disposition", "attachment;filename="+reportName);
		
		String outputString = "";
		for(int i = 0; i < poll.getPollData().getAnswer().getAnswerOptions().size(); i++){
			outputString = outputString + poll.getPollData().getAnswer().getAnswerOptions().get(i) + ",";
		}
		outputString = outputString + "\n";
		for(int i = 0; i < poll.getPollData().getAnswer().getAnswerOptions().size(); i++){
			outputString = outputString + poll.getPollData().getAnswer().getAnswerChosen().get(i) + ",";
		}
		response.getOutputStream().print(outputString);
 
		response.getOutputStream().flush();
		
		RedirectView redirect = null;
		if (a != null){
			redirect = new RedirectView("/test/singlepolldata/"+pollId);
		}
	    redirect.setExposeModelAttributes(false);
	    return redirect;
	}
	
	//Code for the groupmanager page
	@RequestMapping(value = "/groupmanager", method = RequestMethod.GET)
	public ModelAndView groupmanager(@ModelAttribute("SpringWeb") RUser user, ModelMap model, 
			HttpServletRequest request) throws SQLException {
		RUser a = (RUser) request.getSession().getAttribute("token");
		if (a == null) {
			return home(new RUser(), model, request);
		} else {
			System.out.println("Logged in as " + a.getUsername());
			// model.addAttribute("username", a.getRUsername());
			String login = "<a href='/test/profile'>" + a.getUsername() + "</a>";
			String signout = "<a href='/test/signout' >Sign Out</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signout);
		}
		
		ArrayList<Group> pollArr = Group.getYourPollGroups(a.getUsername());
		System.out.println("######## " + pollArr.size());
		String thyme = "";
		for (int i =(pollArr.size()-1); i >= 0; i--){
			thyme = thyme + "<tr><td>"+pollArr.get(i).getGroupName()+"</td><td hidden='true'>"+pollArr.get(i).getGroupID()+"</td></tr>";
		}
		model.addAttribute("polls", thyme);
		
		ArrayList<Group> invitedGroups = Group.getYourInvitedGroups(a.getUsername());
		String invitedThyme = "";
		for (int i =(invitedGroups.size()-1); i >= 0; i--){
			invitedThyme = invitedThyme + "<tr><td>"+invitedGroups.get(i).getGroupName()+"</td><td hidden='true'>"+invitedGroups.get(i).getGroupID()+"</td></tr>";
		}
		model.addAttribute("invitedPolls", invitedThyme);
		
		ArrayList<Poll> yourPolls = Poll.getYourPrivatePolls(a.getUsername());
		String privatePolls = "";
		for (int i=(yourPolls.size()-1); i>= 0; i--){
			privatePolls = privatePolls + "<tr><td align='left'>"+yourPolls.get(i).getPollName()+"</td><td hidden='true'>"+yourPolls.get(i).getPollNum()+"</td><td align='left'>"+yourPolls.get(i).getPollDescription()+"</td></tr>";
		}
		model.addAttribute("yourPolls", privatePolls);
		
		return new ModelAndView("groupmanager", "command", new RUser());
	}

	@RequestMapping(value = "/creategroup/{pollNum}/{groupName}", method = RequestMethod.GET)
	public View createGroup(@PathVariable String groupName, @PathVariable String pollNum,
			ModelMap model, HttpServletRequest request) throws SQLException{
		RUser a = (RUser) request.getSession().getAttribute("token");
		
		Group.createGroup(groupName, Integer.valueOf(pollNum), a.getUsername());
		
		RedirectView redirect = null;
		redirect = new RedirectView("/test/groupmanager");
	    redirect.setExposeModelAttributes(false);
	    return redirect;
	}
	
	//Code for the group page of the site
	@RequestMapping(value = "/group/{groupNum}", method = RequestMethod.GET)
	public ModelAndView group(@ModelAttribute("SpringWeb") RUser user, ModelMap model, 
			HttpServletRequest request, @PathVariable String groupNum) {
		RUser a = (RUser) request.getSession().getAttribute("token");
		if (a == null) {
			System.out.println("RUser not logged in");
			// Login Modifier
			String login = "<a href='../navbar-static-top/' data-toggle='modal' data-target='#login-modal'>Login</a>";
			String signup = "<a href='../navbar-fixed-top/' data-toggle='modal' data-target='#create-account-modal'>Signup</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signup);
		} else {
			System.out.println("Logged in as " + a.getUsername());
			// model.addAttribute("username", a.getRUsername());
			String login = "<a href='/test/profile'>" + a.getUsername() + "</a>";
			String signout = "<a href='/test/signout' >Sign Out</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signout);
		}
		Group pollGroup = RUser.getGroup(Integer.valueOf(groupNum));
		System.out.println(pollGroup.getGroupName());
		System.out.println(pollGroup.getGroupPoll().getPollName());
		model.addAttribute("groupNameAndPollName", pollGroup.getGroupName() + " Voting on: " + pollGroup.getGroupPoll().getPollName());
		
		ArrayList<RUser> groupMembers = RUser.getGroupMembers(groupNum);
		String thyme = "";
		
		if (a.getUsername().equals(pollGroup.getAdmin())){
			model.addAttribute("creatorHide", "");
		}
		else
			model.addAttribute("creatorHide", "hidden='true'");
		
		model.addAttribute("groupNum",groupNum);
		model.addAttribute("pollNum", pollGroup.getGroupPoll().getPollNum());
		if (a != null){
			if(a.getUsername().equals(pollGroup.getAdmin())){
				model.addAttribute("access","accep");
				for (int i =(groupMembers.size()-1); i >= 0; i--){
					thyme = thyme + "<tr><td>"+groupMembers.get(i).getUsername()+"</td><td><form:form method='POST' action='/test/deleteuserfromgroup'><input type='text' id='groupNum' name='groupNUM' value='"+groupNum+"' hidden='true'/><input type='text' id='username' name='usernameString' value='"+groupMembers.get(i).getUsername()+"' hidden='true'/><input type='submit' id='deleteRUser' class='btn btn-success' value='Delete RUser'></form:form></td></tr>";
				}
				model.addAttribute("tableHeader", "Delete RUser");
			}
			else{
				model.addAttribute("access","s");
				for (int i =(groupMembers.size()-1); i >= 0; i--){
					thyme = thyme + "<tr><td>"+groupMembers.get(i).getUsername()+"</td></tr>";
				}
				model.addAttribute("tableHeader", "");
			}
		}
		else
			model.addAttribute("access","s");
		model.addAttribute("groupMembers", thyme);
		
		return new ModelAndView("group", "command", new RUser());
	}
	
	@RequestMapping(value = "/addusertogroup", method = RequestMethod.POST)
	public View addRUserToGroup(@RequestParam("usernameString")String username, @RequestParam("groupNUM")String groupNum,
			ModelMap model, HttpServletRequest request) {
		
		Group.addUserToGroup(username, groupNum);
		
		RedirectView redirect = null;
		redirect = new RedirectView("/test/group/"+groupNum);
	    redirect.setExposeModelAttributes(false);
	    return redirect;
	}
	
	@RequestMapping(value = "/deleteuserfromgroup/{groupNum}/{username}", method = RequestMethod.GET)
	public View deleteRUserFromGroup(@PathVariable String groupNum,@PathVariable String username,
			ModelMap model, HttpServletRequest request) throws SQLException{
		
		Group.deleteUserFromGroup(username, groupNum);
		
		RedirectView redirect = null;
		redirect = new RedirectView("/test/group/"+groupNum);
	    redirect.setExposeModelAttributes(false);
	    return redirect;
	}
	
	@RequestMapping(value = "/deletegroup/{groupNum}", method = RequestMethod.POST)
	public View deleteGroup(@PathVariable String groupNum,
			ModelMap model, HttpServletRequest request) {
		
		System.out.println("Group to be deleted: " + groupNum);
		Group.deleteGroup(groupNum);
		
		RedirectView redirect = null;
		redirect = new RedirectView("/test/groupmanager");
	    redirect.setExposeModelAttributes(false);
	    return redirect;
	}
	
	@RequestMapping(value = "/sendfeedback", method = RequestMethod.POST)
	public View sendFeedback(@RequestParam("feedbackText")String textarea,
			ModelMap model, HttpServletRequest request) {
	
		Administrator.sendFeedback(textarea);
		
		RedirectView redirect = null;
		redirect = new RedirectView("/test/home");
	    redirect.setExposeModelAttributes(false);
	    return redirect;
	}
	
	@RequestMapping(value = "/sendsupportticket", method = RequestMethod.POST)
	public View sendSupportTicket(@RequestParam("supportText")String textarea,
			ModelMap model, HttpServletRequest request) {
		RUser a = (RUser) request.getSession().getAttribute("token");
		Administrator.sendSupportTicket(textarea,a.getUsername());
		
		RedirectView redirect = null;
		redirect = new RedirectView("/test/profile");
	    redirect.setExposeModelAttributes(false);
	    return redirect;
	}
	
	@RequestMapping(value = "/random", method = RequestMethod.GET)
	public View randomPoll(ModelMap model, HttpServletRequest request) {
		Random rand = new Random();
		ArrayList<Integer> pollNums = Poll.getActivePublicPolls();
		int selector = rand.nextInt(pollNums.size());
		int randomInt = pollNums.get(selector);
		
		RedirectView redirect = null;
		redirect = new RedirectView("/test/singlepoll/"+randomInt);
	    redirect.setExposeModelAttributes(false);
	    return redirect;
	}
}
