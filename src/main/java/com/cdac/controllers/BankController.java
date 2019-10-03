package com.cdac.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cdac.bean.User;
import com.cdac.service.IBankService;

@Controller
public class BankController {
	
	@Autowired
	IBankService iBankService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String indexPage(Model model) {
		model.addAttribute("user", new User());
		return "index";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute("user") User user) {
		User userFound = iBankService.login(user);
		System.out.println("**********");
		System.out.println(userFound);
		
		if(userFound.getRole().equals("USER"))
		{
			return "user";
		}
		else if(userFound.getRole().equals("EMPLOYEE"))
		{
			return "employee";
		}
		else if(userFound.getRole().equals("MANAGER"))
		{
			return "manager";
		}
		else {
			return "index";	
		}
	}
}
