package com.test.test;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	public ModelAndView home() {
		return new ModelAndView("home", "command", new User());
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
		User a = (User)request.getSession().getAttribute("token");
		model.addAttribute("username", a.getUsername());
		
		return new ModelAndView("mypolls", "command", new User());
	}
	
	@RequestMapping(value = "/greeting", method = RequestMethod.GET)
	public ModelAndView greeting(@ModelAttribute("SpringWeb")User user, ModelMap model,
			HttpServletRequest request){
		//Add casting so we can treat this as a User object
		User a = (User)request.getSession().getAttribute("token");
		model.addAttribute("username", a.getUsername());
		
		//TEST
		System.out.println("Username: " + a.getUsername());
		System.out.println("Password: " + a.getPassword());
		return new ModelAndView("greeting");
	}
	
	@RequestMapping(value = "/communitypolls", method = RequestMethod.GET)
	public ModelAndView community(@ModelAttribute("SpringWeb")User user, ModelMap model,
			HttpServletRequest request){
		return new ModelAndView("communitypolls");
	}
	
	@RequestMapping(value = "/createpoll", method = RequestMethod.GET)
	public ModelAndView createpoll(@ModelAttribute("SpringWeb")User user, ModelMap model,
			HttpServletRequest request){
		return new ModelAndView("createpoll");
	}
	
	@RequestMapping(value = "/createpollfunction", method = RequestMethod.POST)
	public @ResponseBody ModelAndView createpollfunction(@ModelAttribute("SpringWeb")Poll poll, ModelMap model,
			HttpServletRequest request){
		System.out.println("Starting function");
		System.out.println(poll.getPollName());
		System.out.println(poll.getPollQuestion());
		System.out.println(poll.getAnswerType());
		System.out.println(poll.getPollView());
		System.out.println(poll.getAnswer());
		
		return new ModelAndView("createpoll");
	}
	
	@RequestMapping(value = "/singlepoll", method = RequestMethod.GET)
	public ModelAndView singlePoll(@ModelAttribute("SpringWeb")User user, ModelMap model,
			HttpServletRequest request){
		model.addAttribute("red", 1);
		model.addAttribute("green", 2);
		model.addAttribute("blue",null);
		return new ModelAndView("singlepoll");
	}
}
