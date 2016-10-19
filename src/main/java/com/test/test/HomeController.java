package com.test.test;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
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
		//In the "result" page
		if(user.getUsername().equals("Administrator") && user.getPassword().equals("easyPoll")){
		model.addAttribute("username", user.getUsername());
		model.addAttribute("password", user.getPassword());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		//This token will be the session attribute
		request.getSession().setAttribute("token", user);
<<<<<<< HEAD
		return new ModelAndView("home");
=======
		return new ModelAndView("result");
		}
		else{
			return new ModelAndView("user", "command", new User());
		}
>>>>>>> 5d7d0e6f96d9ac686be53cb123824611bfbf7439
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
}
