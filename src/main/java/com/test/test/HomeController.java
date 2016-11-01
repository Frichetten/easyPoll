package com.test.test;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
//@SessionAttributes("token")
public class HomeController {
	
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
