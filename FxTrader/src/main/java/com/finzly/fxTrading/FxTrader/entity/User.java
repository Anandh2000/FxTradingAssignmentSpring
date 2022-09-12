package com.finzly.fxTrading.FxTrader.entity;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

public class User {
	@Pattern(regexp = "[a-zA-Z\\s+]+[.]+",message = "Invalid User Name")
	private String customerName;
	@Pattern(regexp = "USDINR", message = "Invalid CurrencyPair")
	private String currencyPair;
	@Min(1)
	private double amount;
	private String getRateYesOrNo;
	public User(String customerName, String currencyPair, double amount, String getRateYesOrNo, String bookOrCancel) {
		super();
		this.customerName = customerName;
		this.currencyPair = currencyPair;
		this.amount = amount;
		this.getRateYesOrNo = getRateYesOrNo;
	}
	public User() {	
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
	
	

}
