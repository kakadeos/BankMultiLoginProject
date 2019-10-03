package com.cdac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
