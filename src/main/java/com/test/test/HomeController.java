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
import org.springframework.web.servlet.ModelAndView;


import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
//@SessionAttributes("token")
public class HomeController {
	
	// The Environment class serves as the property holder
	  // and stores all the properties loaded by the @PropertySource
	  @Autowired
	  private Environment env;
	  
	//Root mapping
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView user() {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/easyPoll";
			Connection connection = DriverManager.getConnection(url,"root","password");
			System.out.println("We're in");
			String sql = "select Username from RUser";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			if (rs.next())
				System.out.println(rs.getString(1));
			else
				System.out.println("no rows");
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("index", "command", new User());
	}
	
	
	
	//Root mapping
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home() {
		return new ModelAndView("home", "command", new User());
	}
	   
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public ModelAndView addUser(@ModelAttribute("SpringWeb")User user, ModelMap model,
			HttpServletRequest request) {
		//Add these attributes to the model so they will appear
		model.addAttribute("username", user.getUsername());
		model.addAttribute("password", user.getPassword());
		//Firebase nick = new Firebase("https://testproject-9f072.firebaseio.com");
		
	    //nick.child("Users/User/Password").addValueEventListener(new ValueEventListener() {
	    //    @Override
	    //    public void onDataChange(DataSnapshot snapshot) {
	    //      System.out.println(snapshot.getValue());  //prints "Do you have data? You'll love Firebase."
	    //    }
	    //    @Override public void onCancelled(FirebaseError error) { }
	    //  });
		//This token will be the session attribute
		request.getSession().setAttribute("token", user);
		return new ModelAndView("home");
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
	
	@RequestMapping(value = "/singlepoll", method = RequestMethod.GET)
	public ModelAndView singlePoll(@ModelAttribute("SpringWeb")User user, ModelMap model,
			HttpServletRequest request){
		return new ModelAndView("singlepoll");
	}
}
