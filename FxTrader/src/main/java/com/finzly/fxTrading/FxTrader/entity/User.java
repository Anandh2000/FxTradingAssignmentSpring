package com.finzly.fxTrading.FxTrader.entity;

public class User {
	private String customerName;
	private String currencyPair;
	private double amount;
	private String getRateYesOrNo;
	public User(String customerName, String currencyPair, double amount, String getRateYesOrNo, String bookOrCancel) {
		super();
		this.customerName = customerName;
		this.currencyPair = currencyPair;
		this.amount = amount;
		this.getRateYesOrNo = getRateYesOrNo;
	}
	
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCurrencyPair() {
		return currencyPair;
	}
	public void setCurrencyPair(String currencyPair) {
		this.currencyPair = currencyPair;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getGetRateYesOrNo() {
		return getRateYesOrNo;
	}
	public void setGetRateYesOrNo(String getRateYesOrNo) {
		this.getRateYesOrNo = getRateYesOrNo;
	}
	
	
	@Override
	public String toString() {
		return "User [customerName=" + customerName + ", currencyPair=" + currencyPair + ", amount=" + amount
				+ ", getRateYesOrNo=" + getRateYesOrNo + "]";
	}


	public User() {
		
	}
	
	
	

}
