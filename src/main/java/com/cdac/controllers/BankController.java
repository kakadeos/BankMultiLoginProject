package com.cdac.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cdac.bean.AmountWithdrawl;
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
	public ModelAndView login(@ModelAttribute("user") User user) {
		User userFound = iBankService.login(user);
		
		if(userFound == null) {
			return new ModelAndView("index","message","Please check credentials. No user found");
		}
		else if(userFound.getRole().equals("USER"))
		{
			ModelAndView modelAndView = new ModelAndView("user");
			modelAndView.addObject("user", userFound);
			return modelAndView;
		}
		else if(userFound.getRole().equals("EMPLOYEE"))
		{
			ModelAndView modelAndView = new ModelAndView("employee");
			modelAndView.addObject("user", userFound);
			return modelAndView;
		}
		else if(userFound.getRole().equals("MANAGER"))
		{
			ModelAndView modelAndView = new ModelAndView("manger");
			modelAndView.addObject("user", userFound);
			return modelAndView;
		}
		else {
			return new ModelAndView("index","message","Please check credentials. No user found");
		}
	}
	
	@RequestMapping(value = "/withdrawl", method=RequestMethod.GET)
	public String withdrawlMoney(Model model) {
		model.addAttribute("withdrawl", new AmountWithdrawl());
		return "amountWithdrawlForm";
		
	}
	
	@RequestMapping(value = "/requestMoney", method = RequestMethod.POST)
	public String MoneyRequest(@ModelAttribute("amountWithdrawl") AmountWithdrawl amountWithdrawl, Model model) {
		int result = iBankService.withDrawlMoney(amountWithdrawl);
		if(result > 0) {
			model.addAttribute("withdrawl", new AmountWithdrawl());
			model.addAttribute("message", "Request Successfully Sent");
			return "amountWithdrawlForm";
		} else {
			model.addAttribute("withdrawl", new AmountWithdrawl());
			model.addAttribute("message", "Transaction failed");
			return "amountWithdrawlForm";
		}
	}
	
	@RequestMapping(value="/viewRequests", method = RequestMethod.GET)
	public String viewRequests(Model model) {
		List<AmountWithdrawl> requestList = iBankService.getRequestList();
		model.addAttribute("list", requestList);
		return "moneyRequestForm";
	}
	
	@RequestMapping(value="/acceptRequest/{id}", method = RequestMethod.POST)
	public String accpetRequest(@PathVariable("id")int id, Model model) {
		List<AmountWithdrawl> requestList = iBankService.getRequestList();
		model.addAttribute("list", requestList);
		return "moneyRequestForm";
	}
	
	@RequestMapping(value="/rejectRequest/{id}", method = RequestMethod.POST)
	public String rejectRequest(@PathVariable("id")int id, Model model) {
		List<AmountWithdrawl> requestList = iBankService.getRequestList();
		model.addAttribute("list", requestList);
		return "moneyRequestForm";
	}
}
