package com.cdac.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cdac.bean.AmountWithdrawl;
import com.cdac.bean.User;
import com.cdac.service.IBankService;

@Controller
public class BankController {
	
	@Autowired
	IBankService iBankService;
	
		
	@RequestMapping(value = "/withdrawl", method=RequestMethod.GET)
	public String withdrawlMoney(Model model, HttpSession session) {
		model.addAttribute("user", session.getAttribute("user"));
		model.addAttribute("withdrawl", new AmountWithdrawl());
		return "amountWithdrawlForm";
		
	}
	
	@RequestMapping(value = "/requestMoney", method = RequestMethod.POST)
	public String MoneyRequest(@ModelAttribute("amountWithdrawl") AmountWithdrawl amountWithdrawl, Model model, HttpSession session) {
		int result = iBankService.withDrawlMoney(amountWithdrawl, (User) session.getAttribute("user"));
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
	
	@RequestMapping(value="/viewRequests")
	public String viewRequests(Model model) {
		List<AmountWithdrawl> requestList = iBankService.getRequestList();
		model.addAttribute("list", requestList);
		return "moneyRequestForm";
	}
	
	
	@RequestMapping(value="/acceptRequest/{id}")
	public String acceptRequest(@PathVariable("id")int id, Model model) {
		System.out.println(id);
		iBankService.acceptRequest(id);
		return "redirect:/viewRequests";
	}
	
	@RequestMapping(value="/rejectRequest/{id}")
	public String rejectRequest(@PathVariable("id")int id, Model model) {
		System.out.println(id);
		iBankService.rejectRequest(id);
		return "redirect:/viewRequests";
	}
	
	@RequestMapping(value="/viewAllRequests")
	public String ViewAllRequests(Model model) {
		List<AmountWithdrawl> list= iBankService.viewAllRequest();
		model.addAttribute("list", list);
		return "monitorAllTransaction";		
	}
	
	@RequestMapping(value="/viewUserRequests")
	public String viewUserRequest(Model model, HttpSession session) {
		User user = (User)session.getAttribute("user");
		List<AmountWithdrawl> requestList = iBankService.getUserRequest(user.getUserName());
		model.addAttribute("list", requestList);
		return "viewUserRequest";
	}
}
