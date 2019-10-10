package com.cdac.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cdac.bean.AmountWithdrawl;
import com.cdac.bean.User;
import com.cdac.service.IBankService;

@Controller
public class BankController {

	@Autowired
	IBankService iBankService;

	@Autowired
	HttpSession session;

	@RequestMapping(value = "/withdrawl", method=RequestMethod.GET)
	public String withdrawlMoney(Model model) {
		if(session.getAttribute("user") != null) {
			model.addAttribute("user", session.getAttribute("user"));
			model.addAttribute("withdrawl", new AmountWithdrawl());
			return "amountWithdrawlForm";
		}
		model.addAttribute("message", "Oops Error occured");
		return "error";
	}

	@RequestMapping(value = "/requestMoney", method = RequestMethod.POST)
	public String MoneyRequest(@ModelAttribute("amountWithdrawl") AmountWithdrawl amountWithdrawl, Model model, HttpSession session) {
		if(session.getAttribute("user") != null) {
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
		model.addAttribute("message", "Oops Error occured");
		return "error";
	}

	@RequestMapping(value="/viewRequests")
	public String viewRequests(Model model) {
		if(session.getAttribute("user") != null) {
			List<AmountWithdrawl> requestList = iBankService.getRequestList();
			model.addAttribute("list", requestList);
			return "moneyRequestForm"; 
		}
		model.addAttribute("message", "Oops Error occured");
		return "error";
	}


	@RequestMapping(value="/acceptRequest/{id}")
	public String acceptRequest(@PathVariable("id")int id, Model model) {
		if(session.getAttribute("user") != null) {
			System.out.println(id);
			iBankService.acceptRequest(id);
			return "redirect:/viewRequests";
		}
		model.addAttribute("message", "Oops Error occured");
		return "error";
	}

	@RequestMapping(value="/rejectRequest/{id}")
	public String rejectRequest(@PathVariable("id")int id, Model model) {
		if(session.getAttribute("user") != null) {
			System.out.println(id);
			iBankService.rejectRequest(id);
			return "redirect:/viewRequests";
		}
		model.addAttribute("message", "Oops Error occured");
		return "error";
	}

	@RequestMapping(value="/viewAllRequests")
	public String ViewAllRequests(Model model) {
		if(session.getAttribute("user") != null) {
			List<AmountWithdrawl> list= iBankService.viewAllRequest();
			model.addAttribute("list", list);
			return "monitorAllTransaction";
		}
		model.addAttribute("message", "Oops Error occured");
		return "error";
	}

	@RequestMapping(value="/viewUserRequests")
	public String viewUserRequest(Model model) {
		if(session.getAttribute("user") != null) {
			User user = (User)session.getAttribute("user");
			List<AmountWithdrawl> requestList = iBankService.getUserRequest(user.getUserName());
			model.addAttribute("list", requestList);
			return "viewUserRequest";
		}
		model.addAttribute("message", "Oops Error occured");
		return "error";
	}

	@RequestMapping(value="/searchRequest")
	public String searchRequest() {
		return "searchRequest";

	}

	@RequestMapping(value = "/{reason}", method = RequestMethod.POST)
	public String sqlAttck(@RequestParam("reason")String reason, Model model) {
		if(session.getAttribute("user") != null) {
			System.out.println(reason);
			User user = (User)session.getAttribute("user");
			System.out.println(user.getUserName());
			List<AmountWithdrawl> list = iBankService.getMyRequest(reason, user.getUserName());
			model.addAttribute("list", list);
			return "searchRequest";
		}
		model.addAttribute("message", "error");
		return "searchRequest";
	}


}
