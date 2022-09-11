package com.finzly.fxTrading.FxTrader.entity;

public class FxTradingData {
	private int tradeNumber;
	private String customerName;
	private String currencyPair;
	private double amount;
	private double rate;
	
	public FxTradingData() {
	}
	
	public FxTradingData(int tradeNumber, String customerName,String currencyPair, double amount, double rate) {
		super();
		this.tradeNumber = tradeNumber;
		this.currencyPair = currencyPair;
		this.customerName = customerName;
		this.amount = amount;
		this.rate = rate;
	}

	public int getTradeNumber() {
		return tradeNumber;
	}

	public void setTradeNumber(int tradeNumber) {
		this.tradeNumber = tradeNumber;
	}

	public String getCurrencyPair() {
		return currencyPair;
	}

	public void setCurrencyPair(String currencyPair) {
		this.currencyPair = currencyPair;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "FxTradingData [tradeNumber=" + tradeNumber + ", customerName=" + customerName + ", currencyPair="
				+ currencyPair + ", amount=" + amount + ", rate=" + rate + "]";
	}
	
	
	
	
	
	
	
	

}
