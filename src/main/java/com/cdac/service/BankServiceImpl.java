package com.cdac.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.bean.AmountWithdrawl;
import com.cdac.bean.User;
import com.cdac.dao.IBankDao;

@Service
public class BankServiceImpl implements IBankService{

	@Autowired
	IBankDao iBankDao;
	
	@Override
	public User login(User user) {
		return iBankDao.login(user);
	}

	@Override
	public int withDrawlMoney(AmountWithdrawl amountWithdrawl, User user) {
		return iBankDao.withDrawlMoney(amountWithdrawl,user);
	}

	@Override
	public List<AmountWithdrawl> getRequestList() {
		return iBankDao.getRequestList();
	}

	@Override
	public void acceptRequest(int id) {
		iBankDao.acceptRequest(id);
	}

	@Override
	public void rejectRequest(int id) {
		iBankDao.rejectRequest(id);
	}

	@Override
	public List<AmountWithdrawl> viewAllRequest() {
		return iBankDao.viewAllRequest();
	}

	@Override
	public List<AmountWithdrawl> getUserRequest(String userName) {
		return iBankDao.getUserRequest(userName);
	}

	@Override
	public List<AmountWithdrawl> getMyRequest(String reason, String username) {
		return iBankDao.getMyRequest(reason, username);
	}
	
	

}
