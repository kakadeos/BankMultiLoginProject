package com.cdac.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.cdac.bean.AmountWithdrawl;
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
		User userFind = new User();
		try {
		userFind = (User) jdbcTemplate.queryForObject(sql, new Object[]{user.getUserName(), user.getPassword()}, new BeanPropertyRowMapper<User>(User.class));
		}
		catch(EmptyResultDataAccessException e) {
			userFind = null;
		}
		return userFind;
	}



	@Override
	public int withDrawlMoney(AmountWithdrawl amountWithdrawl, User user) {
		System.out.println(amountWithdrawl);
		String sql="INSERT INTO banktransaction (accountnumber, amount, reason, paymentstatus, username) VALUES ('"+amountWithdrawl.getAccountNumber()+"', '"+amountWithdrawl.getAmount()+"', '"+amountWithdrawl.getReason()+"','PENDING','"+user.getUserName()+"')";    
		return jdbcTemplate.update(sql);
	}



	@Override
	public List<AmountWithdrawl> getRequestList() {
		return jdbcTemplate.query("select * from banktransaction where paymentstatus='PENDING'",new RowMapper<AmountWithdrawl>(){    
			public AmountWithdrawl mapRow(ResultSet rs, int row) throws SQLException {    
				AmountWithdrawl amountWithdrawl=new AmountWithdrawl();    
				amountWithdrawl.setId(rs.getInt(1));
				amountWithdrawl.setAccountNumber(rs.getString(2));
				amountWithdrawl.setAmount(rs.getInt(3));
				amountWithdrawl.setReason(rs.getString(4));
				amountWithdrawl.setPaymentStatus(rs.getString(5));
				amountWithdrawl.setUsername(rs.getString(6));
				return amountWithdrawl;    
			}    
		});    
	}



	@Override
	public void acceptRequest(int id) {
		String sql="UPDATE banktransaction SET  paymentstatus='APPROVED' WHERE id='"+id+"'";    
		jdbcTemplate.update(sql);		
	}



	@Override
	public void rejectRequest(int id) {
		String sql="UPDATE banktransaction SET  paymentstatus='REJECTED' WHERE id='"+id+"'";    
		jdbcTemplate.update(sql);
	}



	@Override
	public List<AmountWithdrawl> viewAllRequest() {
		return jdbcTemplate.query("select * from banktransaction",new RowMapper<AmountWithdrawl>(){    
			public AmountWithdrawl mapRow(ResultSet rs, int row) throws SQLException {    
				AmountWithdrawl amountWithdrawl=new AmountWithdrawl();    
				amountWithdrawl.setId(rs.getInt(1));
				amountWithdrawl.setAccountNumber(rs.getString(2));
				amountWithdrawl.setAmount(rs.getInt(3));
				amountWithdrawl.setReason(rs.getString(4));
				amountWithdrawl.setPaymentStatus(rs.getString(5));
				amountWithdrawl.setUsername(rs.getString(6));
				return amountWithdrawl;    
			}    
		});
	}
	
	

}
