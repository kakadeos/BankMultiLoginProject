package com.cdac.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.cdac.bean.User;

public class BankDaoImpl implements IBankDao{

	@Autowired
	JdbcTemplate jdbcTemplate;    

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}



	@Override
	public User login(User user) {
		String sql = "SELECT * FROM bankuserdetails WHERE username = ? AND password = ?";
		User userFind = (User) jdbcTemplate.queryForObject(sql, new Object[]{user.getUserName(), user.getPassword()}, new BeanPropertyRowMapper<User>(User.class));
		return userFind;
	}

}
