package com.cdac.service;

import java.util.List;

import com.cdac.bean.AmountWithdrawl;
import com.cdac.bean.User;

public interface IBankService {
	public User login(User user);

	public int withDrawlMoney(AmountWithdrawl amountWithdrawl, User user);

	public List<AmountWithdrawl> getRequestList();

	public void acceptRequest(int id);

	public void rejectRequest(int id);

	public List<AmountWithdrawl> viewAllRequest();

	public List<AmountWithdrawl> getUserRequest(String userName);

	public List<AmountWithdrawl> getMyRequest(String reason, String username);
}
