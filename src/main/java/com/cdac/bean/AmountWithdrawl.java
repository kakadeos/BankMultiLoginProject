package com.cdac.bean;

public class AmountWithdrawl {
	
	private int id;
	private String username;
	private String accountNumber;
	private int amount;
	private String reason;
	private String paymentStatus;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
	@Override
	public String toString() {
		return "AmountWithdrawl [id=" + id + ", username=" + username + ", accountNumber=" + accountNumber + ", amount="
				+ amount + ", reason=" + reason + ", paymentStatus=" + paymentStatus + "]";
	}
	
	
	
		
}
