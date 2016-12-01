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

	/**
	 * Root Mapping: This will return the landing page
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView user() {
		return new ModelAndView("index", "command", new RUser());
	}

	/**
	 * This will return the home page, where the user can perform a variety of tasks
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home(@ModelAttribute("SpringWeb") RUser user, 
			ModelMap model, HttpServletRequest request) {
		// Confirming Login Status
		RUser a = (RUser) request.getSession().getAttribute("token");
		if (a == null) {
			//If they are not logged in
			System.out.println("RUser not logged in");
			String login = "<a href='../navbar-static-top/' data-toggle='modal' data-target='#login-modal'>Login</a>";
			String signup = "<a href='../navbar-fixed-top/' data-toggle='modal' data-target='#create-account-modal'>Signup</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signup);
		} else {
			//Confirmed they are logged in
			System.out.println("Logged in as " + a.getUsername());
			String login = "<a href='/test/profile'>" + a.getUsername() + "</a>";
			String signout = "<a href='/test/signout' >Sign Out</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signout);
		}
		
		//Populate the most recent public polls at the bottom of the page
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
	
	/**
	 * This is the "about" page. Will tell the users a little bit about the project.
	 * It will also show the developer names.
	 */
	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public ModelAndView about(@ModelAttribute("SpringWeb") RUser user, ModelMap model, 
			HttpServletRequest request) {
		// Confirming Login Status
		RUser a = (RUser) request.getSession().getAttribute("token");
		if (a == null) {
			//If they are not logged in
			System.out.println("RUser not logged in");
			String login = "<a href='../navbar-static-top/' data-toggle='modal' data-target='#login-modal'>Login</a>";
			String signup = "<a href='../navbar-fixed-top/' data-toggle='modal' data-target='#create-account-modal'>Signup</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signup);
		} else {
			//Confirmed login
			System.out.println("Logged in as " + a.getUsername());
			String login = "<a href='/test/profile'>" + a.getUsername() + "</a>";
			String signout = "<a href='/test/signout' >Sign Out</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signout);
		}

		return new ModelAndView("about", "command", new RUser());
	}
	
	/**
	 * This is the "contact" page. Here we list the developers and their email addresses.
	 */
	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public ModelAndView contact(@ModelAttribute("SpringWeb") RUser user, ModelMap model, 
			HttpServletRequest request) {
		// Confirming Login Status
		RUser a = (RUser) request.getSession().getAttribute("token");
		if (a == null) {
			//If not logged in
			System.out.println("RUser not logged in");
			String login = "<a href='../navbar-static-top/' data-toggle='modal' data-target='#login-modal'>Login</a>";
			String signup = "<a href='../navbar-fixed-top/' data-toggle='modal' data-target='#create-account-modal'>Signup</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signup);
		} else {
			//Confirmed login
			System.out.println("Logged in as " + a.getUsername());
			String login = "<a href='/test/profile'>" + a.getUsername() + "</a>";
			String signout = "<a href='/test/signout' >Sign Out</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signout);
		}

		return new ModelAndView("contact", "command", new RUser());
	}

	/**
	 * This will return the "Profile" page. Here the user can perform a number 
	 * of actions, as well as view their account statistics.
	 */
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView profile(@ModelAttribute("SpringWeb") RUser user, ModelMap model, 
			HttpServletRequest request) {
		// Confirming Login Status
		RUser a = (RUser) request.getSession().getAttribute("token");
		if (a == null) {
			//If not logged in
			System.out.println("RUser not logged in");
			String login = "<a href='../navbar-static-top/' data-toggle='modal' data-target='#login-modal'>Login</a>";
			String signup = "<a href='../navbar-fixed-top/' data-toggle='modal' data-target='#create-account-modal'>Signup</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signup);
		} else {
			//Confirmed login
			System.out.println("Logged in as " + a.getUsername());
			String login = "<a href='/test/profile'>" + a.getUsername() + "</a>";
			String signout = "<a href='/test/signout' >Sign Out</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signout);
		}
		
		//Getting the number of polls this user has created
		int counter = RUser.getMyPollsCount(a.getUsername());
		model.addAttribute("numPolls", counter);

		//Getting the number of votes the user has cast
		int votedCounter = RUser.getMyPollsVoted(a.getUsername());
		model.addAttribute("numVoted", votedCounter);

		//Getting the poll the user created that has the most votes
		String mostVoted = RUser.getMyPollsMostVoted(a.getUsername());
		model.addAttribute("fave", mostVoted);

		//Adding the username to the front end
		model.addAttribute("userName", a.getUsername());
		
		//Adding the email to the front end
		model.addAttribute("email",a.getEmail());
		
		return new ModelAndView("userprofile", "command", new RUser());
	}

	/**
	 * This function is responsible for user logins. It will take the user input 
	 * during the login process, and will attempt to log them in. Regardless of 
	 * success, it will return them to the previous page.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public RedirectView addRUser(@ModelAttribute("SpringWeb") RUser user, ModelMap model, 
			HttpServletRequest request) {
		// Authentication
		RUser ruser = new RUser();
		System.out.println("RUser email: " + user.getEmail());
		System.out.println("RUser password: " + user.getPassword());
		
		//Check to make sure that the user is valid.
		ruser = RUser.verifyUser(user.getEmail(), user.getPassword());
		
		//Check if RUser 
		if(!ruser.getUsername().equals("")){
			//Logging user in
			System.out.println("RUser Logged in: " + ruser.getUsername());
			model.addAttribute("username", ruser.getUsername());
			request.getSession().setAttribute("token", ruser);
			
			
			RUser a = (RUser) request.getSession().getAttribute("token");
			if (a == null) {
				//If not logged in
				System.out.println("RUser not logged in");
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
				//Confirmed login
				String login = "<a href='/test/profile'>" + a.getUsername() + "</a>";
				String signout = "<a href='/test/signout' >Sign Out</a>";
				model.addAttribute("login", login);
				model.addAttribute("signup", signout);
			}
		}
		else{
			//There was a failure in the login process
			System.out.println("RUser failed to login");
			String referer = request.getHeader("Referer");
			RedirectView redirect = new RedirectView(referer);
		    redirect.setExposeModelAttributes(false);
		    //Redirects to previous page
		    return redirect;
		}
		//This is a special case when the user is coming from the landing page to 
		//Take them to the home
		String referer = request.getHeader("Referer");
		if (referer.equals("http://localhost:8080/test/")){
			RedirectView redirect = new RedirectView("http://localhost:8080/test/home");
			redirect.setExposeModelAttributes(false);
			return redirect;
		}
		//Standard case of redirect to the previous page
		else {
			RedirectView redirect = new RedirectView(referer);
			redirect.setExposeModelAttributes(false);
			return redirect;
		}
	}
	
	/**
	 * This is the function to "register" a new user, i.e create their account.
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public RedirectView register(@ModelAttribute("SpringWeb") RUser user, 
			ModelMap model, HttpServletRequest request){
		// Check if user exists in database
		String username = user.getUsername();
		String password = user.getPassword();
		String email = user.getEmail();
		
		//We are checking if that user has already been taken. Every RUser's 
		//Username can only appear once and it will serve as a unique identifier
		if(RUser.checkUser(username)){
			System.out.println("RUser already exists");
		} else {
			// That username is not take, so we can insert them into the DB
			RUser.createRUser(username,password,email);
		}
		RUser toAdd = new RUser();
		toAdd.setUsername(user.getUsername());
		toAdd.setEmail(user.getEmail());
		toAdd.setPassword(user.getPassword());
		return addRUser(toAdd, model, request);
	}
	
	/**
	 * This is for each individual users "MyPolls" page. Here they can see what polls 
	 * have as well as create a poll.
	 */
	@RequestMapping(value = "/mypolls", method = RequestMethod.GET)
	public ModelAndView mypolls(@ModelAttribute("SpringWeb") RUser user, ModelMap model, 
			HttpServletRequest request) {
		// Confirming Login status
		RUser a = (RUser) request.getSession().getAttribute("token");
		if (a == null) {
			//Not logged in, kick them to the home page
			return home(user, model, request);
		} else {
			//Confirmed login - show the data
			System.out.println("Logged in as " + a.getUsername());
			String login = "<a href='/test/profile'>" + a.getUsername() + "</a>";
			String signout = "<a href='/test/signout' >Sign Out</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signout);
		}
		
		//Adding the username to the front end
		model.addAttribute("username", a.getUsername());
		
		//Grabbing a users polls by their username
		String username = a.getUsername();
		ArrayList<Poll> myPolls = RUser.getMyPolls(username);

		// Add attributes to the model that are Polls
		ArrayList<String> toShow = new ArrayList<String>();
		ArrayList<String> toCache = new ArrayList<String>();
		ArrayList<String> toDesc = new ArrayList<String>();
		
		//Cycle through the myPolls array and give it information
		for (int i = 0; i < myPolls.size(); i++) {
			toCache.add(Integer.toString(myPolls.get(i).getPollNum()));
			toShow.add(myPolls.get(i).getPollName());
			toDesc.add(myPolls.get(i).getPollDescription());
		}
		
		//Create the thyme leaf object that will push the object to the front
		String thyme = "";
		for (int j =(toShow.size()-1); j >= 0; j--){
			thyme = thyme + "<tr><td>"+toShow.get(j)+"</td><td hidden='true'>"+toCache.get(j)+"</td><td>"+toDesc.get(j)+"</td></tr>";
		}
		model.addAttribute("polls", thyme);
		
		return new ModelAndView("mypolls", "command", new RUser());
	}

	/**
	 * This function is responsible for the "CommunityPolls" which are viewable by 
	 * all users, even anonymous. Here they can see all public polls as well as 
	 * see the poll of the day.
	 */
	@RequestMapping(value = "/communitypolls", method = RequestMethod.GET)
	public ModelAndView community(@ModelAttribute("SpringWeb") RUser user, 
			ModelMap model, HttpServletRequest request) {
		// Confirming Login Status
		RUser a = (RUser) request.getSession().getAttribute("token");
		if (a == null) {
			//If not logged in
			System.out.println("RUser not logged in");
			String login = "<a href='../navbar-static-top/' data-toggle='modal' data-target='#login-modal'>Login</a>";
			String signup = "<a href='../navbar-fixed-top/' data-toggle='modal' data-target='#create-account-modal'>Signup</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signup);
		} else {
			//Confirmed login
			System.out.println("Logged in as " + a.getUsername());
			String login = "<a href='/test/profile'>" + a.getUsername() + "</a>";
			String signout = "<a href='/test/signout' >Sign Out</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signout);
		}
		
		//Grab all public polls
		ArrayList<Poll> pollArr = RUser.getPublicPolls();
		
		//Putting that info about public polls into the thyme leaf tag
		//That will ship it to the front end
		String thyme = "";
		for (int i =(pollArr.size()-1); i >= 0; i--){
			thyme = thyme + "<tr><td>"+pollArr.get(i).getPollName()+"</td><td hidden='true'>"+pollArr.get(i).getPollNum()+"</td><td>"+pollArr.get(i).getPollDescription()+"</td><td>"+pollArr.get(i).getPollPoster()+"</td></tr>";
		}
		model.addAttribute("polls", thyme);
		
		//Grabbing the poll of the day and shipping to the front
		String pollOfTheDay = "";
		Poll potd = Poll.getPollOfTheDay();
		pollOfTheDay = "<tr><td>"+potd.getPollName()+"</td><td hidden='true'>"+potd.getPollNum()+"</td><td>"+potd.getPollDescription()+"</td><td>"+potd.getPollPoster()+"</td></tr>";
		model.addAttribute("pollOfTheDay", pollOfTheDay);
		
		return new ModelAndView("communitypolls", "command", new RUser());
	}

	/**
	 * This function will show the page that can be used to createpoll. It is from 
	 * here theat the user can salidify the action by submitting that poll.
	 */
	@RequestMapping(value = "/createpoll", method = RequestMethod.GET)
	public ModelAndView createpoll(@ModelAttribute("SpringWeb") RUser user, 
			ModelMap model, HttpServletRequest request) {
		//This is for esthetic reasons, we want to tell the user how many polls have
		//Been created on the platform
		int num = Poll.getTotalPoll();
		model.addAttribute("numberPolls", String.valueOf(num));
		
		RUser a = (RUser) request.getSession().getAttribute("token");
		if (a == null) {
			//If not logged in
			System.out.println("RUser not logged in");
			String login = "<a href='../navbar-static-top/' data-toggle='modal' data-target='#login-modal'>Login</a>";
			String signup = "<a href='../navbar-fixed-top/' data-toggle='modal' data-target='#create-account-modal'>Signup</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signup);
		} else {
			//Confirmed login
			System.out.println("Logged in as " + a.getUsername());
			String login = "<a href='/test/profile'>" + a.getUsername() + "</a>";
			String signout = "<a href='/test/signout' >Sign Out</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signout);
		}
		
		return new ModelAndView("createpoll", "command" ,new RUser());
	}

	/**
	 * This is the function that will actually create and insert the poll into 
	 * the database. 
	 */
	@RequestMapping(value = "/createpollfunction", method = RequestMethod.POST)
	public @ResponseBody ModelAndView createpollfunction(@ModelAttribute("SpringWeb") Poll poll, ModelMap model,
			HttpServletRequest request) {
		//Getting the username and pushing it to the front end
		RUser a = (RUser) request.getSession().getAttribute("token");
		model.addAttribute("username", a.getUsername());
		
		//In case they didn't set and end total set it to 10 (they have to)
		if (poll.getEndTotal() == 0)
			poll.setEndTotal(10);

		// Splitting answers by comma delimiters
		String[] answers = poll.getAnswerParams().split(",");
		ArrayList<String> answersArray = new ArrayList<String>();
		for (int i = 0; i < answers.length; i++) {
			if (i < answers.length)
				answersArray.add(answers[i]);
		}
		
		//Adding the poll to the user and pushing to the DB
		RUser.addPoll(poll, a.getUsername(), answersArray);
		
		return mypolls(new RUser(), model, request);
	}

	/**
	 * This is the primary function to view a poll. Here they can vote in polls 
	 * as well as recommend the poll to a friend or report it. This is the place 
	 * where any sort of redirect should send because the logic to move to the poll 
	 * data view will be decided here.
	 */
	@RequestMapping(value = "/singlepoll/{pollId}", method = RequestMethod.GET)
	public ModelAndView singlePoll(@ModelAttribute("SpringWeb") RUser user, ModelMap model, 
			HttpServletRequest request, @PathVariable String pollId){
		//Grabbing the admin object in session
		Administrator ad = (Administrator) request.getSession().getAttribute("admintoken");
		RUser a = (RUser) request.getSession().getAttribute("token");
		if (ad != null){
			//This is is the Admin has logged in
			System.out.println("Logged in as " + ad.getUsername());
			String login = "<a href='/test/profile'>" + ad.getUsername() + "</a>";
			String signout = "<a href='/test/signout' >Sign Out</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signout);
			model.addAttribute("hide","hidden='true'");
			model.addAttribute("creatorHide","hidden='true'");
		}
		else if (a == null) {
			//This is if the user is anonymous
			System.out.println("RUser not logged in");
			String login = "<a href='../navbar-static-top/' data-toggle='modal' data-target='#login-modal'>Login</a>";
			String signup = "<a href='../navbar-fixed-top/' data-toggle='modal' data-target='#create-account-modal'>Signup</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signup);
			model.addAttribute("hide","hidden='true'");
			model.addAttribute("creatorHide","hidden='true'");
		} else {
			//This is for the standard registered user. 
			System.out.println("Logged in as " + a.getUsername());
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
		
		//Need to identify if it is anonymous user
		String toPut = "";
		if (a == null){
			toPut = request.getRemoteAddr();
		}
		else {
			toPut = a.getUsername();
		}
		
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
		
		//Confirming if the poll is still current
		if(poll!=null){
			//If isCurrent is 0 show data
			if(poll.getIsCurrent().equals("0")){
				return singlePollData(new Answer(), model, request, pollId);
			}
		}
		
		//Here we are pushing information to the front end
		if (poll!=null) {
			//Pushing username to front
			model.addAttribute("posterUsername", poll.getPollPoster());
			
			//Pushing poll name to the front
			model.addAttribute("pollName", poll.getPollName());
			
			//Pushing the question to the front
			model.addAttribute("pollQuestion", poll.getPollQuestion());
			
			// Creating builder, which will be sent as a thyme leaf
			String builder = "";
			String[] options = new String[10];
			int[] values = new int[10];
			for (int i = 0; i < poll.getPollData().getAnswer().getAnswerOptions().size(); i++) {
				builder = builder + "<div class='radio'><label><input type='radio' name='answer' id='Private' value='"
						+ String.valueOf(i + 1) + "'/>" + poll.getPollData().getAnswer().getAnswerOptions().get(i) + "</label></div>";
				options[i] = poll.getPollData().getAnswer().getAnswerOptions().get(i);
				values[i] = poll.getPollData().getAnswer().getAnswerChosen().get(i);
			}
			
			//Building The options and values to push to the front
			String optionsList = "";
			String valuesList = "";
			for (int i = 0; i < poll.getPollData().getParams(); i++) {
				optionsList = optionsList + "'" + options[i] + "',";
				valuesList = valuesList + "'" + values[i] + "',";
			}
			
			//Pushing poll description to front
			model.addAttribute("pollDesc",poll.getPollDescription());
			
			//Pushing option list
			model.addAttribute("optionsList", optionsList);
			
			//Pushing values list 
			model.addAttribute("valuesList", valuesList);
			
			//Pushing the builder string to the front
			model.addAttribute("builder", builder);
			
			//Pushing the pollId to the front
			model.addAttribute("pollID", pollId);
			
			//Grabbing the pollTakerCount and endtotalCount To push to front
			int pollTakerCount = Poll.pollTakerCount(Integer.parseInt(pollId));
			int endTotalCount = Poll.endTotalCount(Integer.parseInt(pollId));
			model.addAttribute("counts", String.valueOf(pollTakerCount)+"/"+String.valueOf(endTotalCount)+" Votes Cast");
			
			//If there is a equal or higher count then the poll is closed
			if (pollTakerCount >= endTotalCount){
				model.addAttribute("isCurrent", "Poll is Closed");
			}
			//Else you can vote and it is ongoing
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

	/**
	 * This will actually display the results of the poll to the viewer so that 
	 * they can view them.
	 */
	@RequestMapping(value = "/singlepolldata/{pollId}", method = RequestMethod.POST)
	public ModelAndView singlePollData(@ModelAttribute("SpringWeb") Answer answer, ModelMap model,
			HttpServletRequest request, @PathVariable String pollId){
		// Confirming Login Status
		RUser a = (RUser) request.getSession().getAttribute("token");
		if (a == null) {
			//If not logged in
			String login = "<a href='../navbar-static-top/' data-toggle='modal' data-target='#login-modal'>Login</a>";
			String signup = "<a href='../navbar-fixed-top/' data-toggle='modal' data-target='#create-account-modal'>Signup</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signup);
		} else {
			//Confirmed login
			System.out.println("Logged in as " + a.getUsername());
			String login = "<a href='/test/profile'>" + a.getUsername() + "</a>";
			String signout = "<a href='/test/signout' >Sign Out</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signout);
		}
		
		// If answer equals null, do nothing
		// Else put that in the DB;
		if (answer.getAnswer() == null) {
			System.out.println("We are coming without giving an answer");
		} else {
			System.out.println("We are giving an answer " + answer.getAnswer());
			Poll poll = DBQuery.getPoll(Integer.parseInt(pollId));
			int ans = Integer.parseInt(answer.getAnswer());
			
			//update answer totals
			poll.getPollData().getAnswer().addAnswerChosen(ans-1, poll.getPollNum());
			
			//update partakers in poll
			poll.addPartaker();
			
			//This will identify if the user is anonymous or not
			String toPut = "";
			if (a == null){
				toPut = request.getRemoteAddr();
			}
			else{
				toPut = a.getUsername();
			}
			
			//Incrementing the selected answer
			ArrayList<Integer> userAnswer = new ArrayList<Integer>();
			for(int i = 1; i < poll.getPollData().getAnswer().getAnswerChosen().size()+1; i++){
				if(i == ans){
					userAnswer.add(1);
				}
				else
					userAnswer.add(0);
			}
			
			//Adding the poll taker
			poll.getPollData().addPollTaker(toPut, poll.getPollNum(),false, userAnswer);
			String toSend = "";
			if (a == null){
				//If anonymous send a fake email
				toSend = "red@nomailhaeinf.com";
			}
			else{
				//Send them a thank you email
				toSend = a.getEmail();
			}
			
			//Sending an email
			String info = "Thank you for voting in the poll: " + poll.getPollName() + "! With your support, that poll will become more popular.\n\nThanks!\n\t-easyPoll Team";
			ExecutorService executor = Executors.newFixedThreadPool(2);
			
			//creating the future task
			FutureTask futureTask_1 = new FutureTask((Callable) new CallableSendMail(toSend, "Thank you for voting", info));
			executor.execute(futureTask_1);
		}

		//Getting the poll from the DB to display to the user
		ResultSet rs = Poll.resultSetPoll(pollId);
		try {
			if (rs.next()) {
				//Push the username to the front
				model.addAttribute("posterUsername", rs.getString(2));
				
				//Push the pollName to the front
				model.addAttribute("pollName", rs.getString(4));
				
				//Push the question to the front
				model.addAttribute("pollQuestion", rs.getString(11));
				
				//Fetching the options
				ArrayList<String> options = new ArrayList<String>();
				String[] values = new String[10];
				for (int i = 0; i < Integer.valueOf(rs.getString(10)); i++) {
					options.add(rs.getString(14 + i));
					values[i] = rs.getString(24 + i);
				}
				
				//Filling the optionslist and values list to push to the front
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
				
				//Pushing the optionslist to the front
				model.addAttribute("optionsList", optionsList);
				
				//Pushing the valueslist to the front
				model.addAttribute("valuesList", valuesList);
				
				//Pushing the description to the front
				model.addAttribute("pollDesc",DBQuery.getPollDescription(pollId));
				
				//Pushing the PollID
				model.addAttribute("pollID", pollId);
				
				//Fetch the pollTakerCount and the endTotalCount and push to the front
				int pollTakerCount = Poll.pollTakerCount(Integer.parseInt(pollId));
				int endTotalCount = Poll.endTotalCount(Integer.parseInt(pollId));
				model.addAttribute("counts", String.valueOf(pollTakerCount)+"/"+String.valueOf(endTotalCount)+" Votes Cast");
				
				//Check fi the poll is current and determining if it is ongoing or not
				if (Poll.isCurrent(Integer.parseInt(pollId))){
					model.addAttribute("isCurrent", "Poll is Ongoing");
				}
				else{
					model.addAttribute("isCurrent", "Poll is Closed");
				}
				
				//If they are the creator of the poll we display an extra feature
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
		} catch (SQLException e){
			e.printStackTrace();
		}

		return new ModelAndView("singlepolldata");
	}

	/**
	 * This is the signout function and it will signout the user, either admin or RUser
	 */
	@RequestMapping(value = "/signout", method = RequestMethod.GET)
	public String signout(@ModelAttribute("SpringWeb")RUser user, ModelMap model,
			HttpServletRequest request) {
		//Setting token to null so that the user no longer exist
		request.getSession().setAttribute("token", null);
		request.getSession().setAttribute("admintoken", null);
		
		//Redirect to the previous page
		String referer = "http://localhost:8080/test/home";
	    return "redirect:"+ referer;
	}
	
	/**
	 * This function will register an admin if they know the secret password
	 */
	@RequestMapping(value = "/adminregister", method = RequestMethod.POST)
	public String adminRegister(@ModelAttribute("SpringWeb")Administrator admin, ModelMap model,
		HttpServletRequest request) {
		Administrator logAdmin = null;
		
		//This is the secret key needed to become an admin. easyPollAdmin
		if (!admin.getEmail().equals("easyPollAdmin"))
			return "redirect:http://localhost:8080/test/admin";
		
		//Creating the admin as well as verifying them so that they are logged in
		Administrator.createAdmin(admin.getUsername(), admin.getPassword());
		logAdmin = Administrator.verifyAdmin(admin.getUsername()+"@easypoll.com", admin.getPassword());
		
		//Appending this new admin to the session
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
	
	/**
	 * This is the admin login process. Can only be called from the admin portal page
	 */
	@RequestMapping(value = "/adminLogin", method = RequestMethod.POST)
	public String adminLogin(@ModelAttribute("SpringWeb")Administrator admin, ModelMap model,
		HttpServletRequest request) {
		Administrator logAdmin = null;
		
		//Verifying the admin.
		logAdmin = Administrator.verifyAdmin(admin.getEmail(),admin.getPassword());
		
		
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
	
	/**
	 * This is the admin portal. Here they can do several different functions such as 
	 * view support tickets or view feedback, send a newsletter or view reported 
	 * questions from the community.
	 */
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView admin(@ModelAttribute("SpringWeb")Administrator admin, ModelMap model,
		HttpServletRequest request) {
		Administrator a = (Administrator) request.getSession().getAttribute("admintoken");
		// Confirming Login Status
		
		Administrator ad;
		if (a == null){
			//If anonymous
			System.out.println("RUser not logged in");
			String login = "<a href='../navbar-static-top/' data-toggle='modal' data-target='#login-modal'>Login</a>";
			String signup = "<a href='../navbar-fixed-top/' data-toggle='modal' data-target='#create-account-modal'>Signup</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signup);
			model.addAttribute("hide","hidden='true'");
		} else {
			//If logged in normally.
			System.out.println("Logged in as " + a.getUsername());
			String login = "<a href='#'>" + a.getUsername() + "</a>";
			String signout = "<a href='/test/signout' >Sign Out</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signout);
			ad = new Administrator(a.getUsername());
			
			//First we need to make sure that multiple reported questions are 
			//consolidated on the front end. To do this we will use a HashMap
			//And treat the pollNum as the primary key.
			ArrayList<ReportedQuestion> pollArr = ad.getReportedQuestions();
			HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
			for(ReportedQuestion rp : pollArr){
				Integer i = map.get(rp.getPollNum());
				if (i == null)
					map.put(rp.getPollNum(),1);
				else 
					map.put(rp.getPollNum(),i+1);
			}
			
			//Now we can actually create the reported polls to go to the front end
			ArrayList<ReportedQuestion> polls = new ArrayList<ReportedQuestion>();
			ArrayList<Integer> poller = new ArrayList<Integer>();
			for(ReportedQuestion rp : pollArr){
				if (!poller.contains(rp.getPollNum())){
					polls.add(rp);
					poller.add(rp.getPollNum());
				}
			}
			
			//Building the polls to push to the front
			String thyme = "";
			for (int i =(polls.size()-1); i >= 0; i--){
				thyme = thyme + "<tr><td>"+polls.get(i).getPollName()+"</td><td hidden='true'>"+polls.get(i).getPollNum()+"</td><td>"+polls.get(i).getPollDescription()+"</td><td>"+polls.get(i).getUsername()+"</td><td>"+map.get(polls.get(i).getPollNum())+"</td></tr>";
			}
			
			//Building the feedback to push to the front
			String feedback = "";
			ArrayList<String> messages = Administrator.getFeedback();
			for (int i=0; i< messages.size(); i++){
				feedback = feedback + "<tr><td>"+messages.get(i)+"</td></tr>";
			}
			
			//Building the support tickets to push to the front.
			String supportTicket = "";
			ArrayList<Administrator> tickets = Administrator.getSupportTickets();
			System.out.println("This is the size of support ticket: " + tickets.size());
			for (int i=0; i < tickets.size(); i++){
				supportTicket = supportTicket + "<tr><td>"+tickets.get(i).getPassword()+"</td><td>"+tickets.get(i).getUsername()+"</td><td>"+tickets.get(i).getEmail()+"</td></tr>";
			}
			
			//Pushing support tickets to push to the front
			model.addAttribute("supportTicket", supportTicket);
			
			//Pushing feedback to push to the front
			model.addAttribute("feedback", feedback);
			
			//Pushing the polls to the front
			model.addAttribute("polls", thyme);
			
			//Pushing the hiden attribute to the front. If they are admins they should
			//be able to see the functions after they have logged in.
			model.addAttribute("hide","");
		}
		
		//This is to see if they just send a newsletter
		Boolean newsCheck = (Boolean)request.getSession().getAttribute("newsletter");
		if (newsCheck != null){
			model.addAttribute("sentNewsletter", "Newsletter Sent!");
			request.getSession().setAttribute("newsletter", null);
		}
		
		return new ModelAndView("admin", "command", new RUser());
	}
	
	/**
	 * Recommend function will send an email to a user to recommend the poll to them. 
	 */
	@RequestMapping(value = "/recommend", method = RequestMethod.POST)
	public RedirectView recommendPoll(@ModelAttribute("SpringWeb")Email email, ModelMap model,
		HttpServletRequest request) {
		// Confirming Login Status
		RUser a = (RUser)request.getSession().getAttribute("token");
		if (a == null){
			//If not logged in
			String referer = request.getHeader("Referer");
			RedirectView redirect = new RedirectView(referer);
		    redirect.setExposeModelAttributes(false);
		    return redirect;
		} else {
			//Confirming login
			String login = "<a href='#'>" + a.getUsername() + "</a>";
			String signout = "<a href='/test/signout' >Sign Out</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signout);
		}
		
		//Here we are actually bulding the email that we will send to the user 
		String info = "To whom it may concern,\n\nA friend of yours is interested in getting you opinion on "
				+ "a poll! Follow the link to learn more...\n\n" + request.getHeader("Referer") + "\n\nGot "
				+ "a question you'd like to ask a vibrant community of polltakers? Visit us at easyPoll.com\n\n "
				+ "All the best!\n\t-easyPoll Team";
		
		//Here we open a thread to send the email
		ExecutorService executor = Executors.newFixedThreadPool(2);
		@SuppressWarnings({ "rawtypes", "unchecked" })
		FutureTask futureTask_1 = new FutureTask((Callable) new CallableSendMail(email.getAddress(), "You've been invited to a poll!", info));
		executor.execute(futureTask_1);
		
		//Returning to the previous page
		String referer = request.getHeader("Referer");
		RedirectView redirect = new RedirectView(referer);
	    redirect.setExposeModelAttributes(false);
	    return redirect;
	}
	
	/**
	 * Sending the newsletter to the relevant parties (everybody)
	 */
	@RequestMapping(value = "/sendnewsletter", method = RequestMethod.POST)
	public String sendnewsletter(@RequestParam("textarea")String textarea, ModelMap model,
		HttpServletRequest request) {
		//The text area is the info recieved in the text area object
		request.getSession().setAttribute("newsletter", true);
		
		//Spinning up the thread to send the email to everyone
		ExecutorService executor = Executors.newFixedThreadPool(2);
		FutureTask futureTask_1 = new FutureTask((Callable) new CallableSendMassMail(textarea));
		executor.execute(futureTask_1);
		
		//Return to the previous page
		String referer = request.getHeader("Referer");
	    return "redirect:"+ referer;
	}
	
	/**
	 * This is the function that will be called when we report a question to the admins
	 */
	@RequestMapping(value = "/report", method = RequestMethod.POST)
	public String report(@ModelAttribute("SpringWeb")RUser user, ModelMap model,
		HttpServletRequest request) {
		//The user has to be logged in so we are fetching that info.
		RUser a = (RUser) request.getSession().getAttribute("token");
		
		//We need to get the pollNum of the previous page so that we can report it
		String referer = request.getHeader("Referer");
		int index = referer.lastIndexOf("/");
		int pollNum = Integer.valueOf(referer.substring(index+1));
		
		//Using that pollnum we will report the question 
		ReportedQuestion.addReportedQuestion(a.getUsername(), pollNum);
		
		//Return to the previous page
	    return "redirect:"+referer;
	}
	
	/**
	 * This is the function that will let you edit the poll. To be clear it will not do the work 
	 * of actually modifying the poll. It will only show the page needed to perform the operation.
	 */
	@RequestMapping(value = "/editPoll/{pollId}", method = RequestMethod.POST)
	public ModelAndView editpoll(@ModelAttribute("SpringWeb")RUser user, ModelMap model,
		HttpServletRequest request, @PathVariable String pollId) {
		// Confirming Login Status, this person must be the poll creator
		RUser a = (RUser)request.getSession().getAttribute("token");
		if (a == null){
			//If not logged in
			System.out.println("RUser not logged in");
		    return new ModelAndView("redirect:http://localhost:8080/test/home");
		} else {
			//Confirmed login
			System.out.println("Logged in as " + a.getUsername());
			String login = "<a href='#'>" + a.getUsername() + "</a>";
			String signout = "<a href='/test/signout' >Sign Out</a>";
			model.addAttribute("login", login);
			model.addAttribute("signup", signout);
		}
		
		//Get the total number of polls (similar to the create poll page)
		int num = Poll.getTotalPoll();
		
		//Push the number of polls to the front
		model.addAttribute("numberPolls", String.valueOf(num));
		
		//Push the pollnum to the front
		model.addAttribute("pollId", pollId);
		
		//Based on the poll Id get the poll object
		Poll poll = new Poll(Integer.valueOf(pollId));
		
		//Push the pollName to the front
		model.addAttribute("pollName", poll.getPollName());
		
		//Push the question to the front
		model.addAttribute("pollQuestion", poll.getPollQuestion());
		
		//Push the description to the front
		model.addAttribute("pollDesc", poll.getPollDescription());
		
		//Push the end total to the front
		model.addAttribute("endTotal", poll.getEndTotal());
		
		//This may not be necessary (its not, keeping it for safety)
		//But if the poll type is not specified it will default to public
		//If it is specified set it properly
		if(poll.getPollType() == null){
			model.addAttribute("isPublic", "checked='checked'");
		}
		else if (poll.getPollType().equals("public"))
			model.addAttribute("isPublic", "checked='checked'");
		else
			model.addAttribute("isPrivate", "checked='checked'");
		
		//Return the view for editpoll
	    return new ModelAndView("editpoll", "command", new RUser());
	}
	
	@RequestMapping(value = "/updatePoll/{pollId}", method = RequestMethod.POST)
	public View updatePoll(@ModelAttribute("SpringWeb")Poll poll, ModelMap model,
		HttpServletRequest request, @PathVariable String pollId) {
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
		HttpServletRequest request, @PathVariable String pollId) {
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
		HttpServletRequest request) {
		
		RUser.forgotPassword(email);
		
		RedirectView redirect = null;
		redirect = new RedirectView("/test/home");
	    redirect.setExposeModelAttributes(false);
	    return redirect;
	}
	
	@RequestMapping(value = "/deleteaccount", method = RequestMethod.POST)
	public View deleteAccount(@ModelAttribute("SpringWeb")Poll poll, ModelMap model,
		HttpServletRequest request) {
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
		HttpServletRequest request, @PathVariable String pollId) {
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
		HttpServletRequest request) {
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
		HttpServletRequest request, @PathVariable String pollId, HttpServletResponse response) throws IOException{
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
			HttpServletRequest request) {
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
			ModelMap model, HttpServletRequest request) {
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
			ModelMap model, HttpServletRequest request){
		
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
