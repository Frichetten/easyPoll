package com.test.test;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
//@SessionAttributes("token")
public class HomeController {
	
	//Root mapping
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView user() {
		return new ModelAndView("user", "command", new User());
	}
	   
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public ModelAndView addUser(@ModelAttribute("SpringWeb")User user, ModelMap model,
			HttpServletRequest request) {
		model.addAttribute("username", user.getUsername());
		model.addAttribute("password", user.getPassword());
		request.getSession().setAttribute("token", user.getUsername());
		return new ModelAndView("result");
	}
	
	@RequestMapping(value = "/greeting", method = RequestMethod.GET)
	public ModelAndView greeting(@ModelAttribute("SpringWeb")User user, ModelMap model,
			HttpServletRequest request){
		model.addAttribute("username", request.getSession().getAttribute("token"));
		System.out.println(request.getSession().getAttribute("token"));
		return new ModelAndView("greeting");
	}
    
    
}
