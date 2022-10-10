package com.finzly.fxTrading.FxTrader.entity;

import org.springframework.stereotype.Component;

@Component
public class MenuDisplayer {
	private String Menu1;
	private String Menu2;
	private String Menu3;
	
	
	public MenuDisplayer(String menu1, String menu2, String menu3) {
		super();
		Menu1 = menu1;
		Menu2 = menu2;
		Menu3 = menu3;
	}
	public MenuDisplayer() {
		
	}
	public String getMenu1() {
		return Menu1;
	}
	public void setMenu1(String menu1) {
		Menu1 = menu1;
	}
	public String getMenu2() {
		return Menu2;
	}
	public void setMenu2(String menu2) {
		Menu2 = menu2;
	}
	public String getMenu3() {
		return Menu3;
	}
	public void setMenu3(String menu3) {
		Menu3 = menu3;
	}

	@Override
	public String toString() {
		return "MenuDisplayer [Menu1=" + Menu1 + ", Menu2=" + Menu2 + ", Menu3=" + Menu3 + "]";
	}
	
	
	

}
