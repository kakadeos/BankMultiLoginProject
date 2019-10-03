package com.cdac.service;

import java.util.List;

import com.cdac.bean.AmountWithdrawl;
import com.cdac.bean.User;

public interface IBankService {
	public User login(User user);

	public int withDrawlMoney(AmountWithdrawl amountWithdrawl);

	public List<AmountWithdrawl> getRequestList();
}
