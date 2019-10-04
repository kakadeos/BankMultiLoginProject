package com.cdac.dao;

import java.util.List;

import com.cdac.bean.AmountWithdrawl;
import com.cdac.bean.User;

public interface IBankDao {

	public User login(User user);

	public int withDrawlMoney(AmountWithdrawl amountWithdrawl, User user);

	public List<AmountWithdrawl> getRequestList();

	public void acceptRequest(int id);

	public void rejectRequest(int id);

	public List<AmountWithdrawl> viewAllRequest();

}
