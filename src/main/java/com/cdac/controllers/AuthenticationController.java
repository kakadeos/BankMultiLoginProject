package com.cdac.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cdac.bean.User;
import com.cdac.service.IBankService;

@Controller
public class AuthenticationController {
	
	@Autowired
	IBankService iBankService;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String indexPage(Model model) {
		model.addAttribute("user", new User());
		return "index";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("user") User user, HttpSession session) {
		User userFound = iBankService.login(user);
		if(userFound == null) {
			return new ModelAndView("index","message","Please check credentials. No user found");
		}
		else if(userFound.getRole().equals("USER"))
		{
			session.setAttribute("user", user);
			ModelAndView modelAndView = new ModelAndView("user");
			return modelAndView;
		}
		else if(userFound.getRole().equals("EMPLOYEE"))
		{
			session.setAttribute("user", user);
			ModelAndView modelAndView = new ModelAndView("employee");
			return modelAndView;
		}
		else if(userFound.getRole().equals("MANAGER"))
		{
			session.setAttribute("user", user);
			ModelAndView modelAndView = new ModelAndView("manager");
			return modelAndView;
		}
		else {
			return new ModelAndView("index","message","Please check credentials. No user found");
		}
	}
	
	@RequestMapping(value = "/logout", method=RequestMethod.GET)
	public String logoutUser() {
		return "redirect://";		
	}

}
