package com.finzly.fxTrading.FxTrader.fxTraderDao;

import java.text.NumberFormat;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Stack;

import com.finzly.fxTrading.FxTrader.controller.FxTradeController;
import com.finzly.fxTrading.FxTrader.entity.FxTradingData;
import com.finzly.fxTrading.FxTrader.entity.User;
import com.finzly.fxTrading.FxTrader.errorHandler.ErrorHandlerService;
import com.finzly.fxTrading.FxTrader.response.ErrorResponse;
import com.finzly.fxTrading.FxTrader.response.SuccessResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

@Component
public class BookDao {
	private int tradeNo = 0;
	private double rate = 66.00;
	private double ConvertedAmount;
	private static LinkedHashSet<FxTradingData> tradeData = new LinkedHashSet<>();
	private static Stack<FxTradingData> recentlyEnteredData = new Stack<>();
	
	@Autowired
	private ErrorHandlerService errorHandlerService;
	
	public Object printAll(){
		if(tradeData.isEmpty()) {
			ErrorResponse errorResponse = errorHandlerService.emptySet("Empty set cannot be displayed", 400, null);
			return errorResponse;
		}else {
			return tradeData;
		}
	}
	
	
	public Object save(User user,EntityModel<FxTradeController> entity) {
		ConvertedAmount = user.getAmount()*rate;
		if(user.getGetRateYesOrNo().equalsIgnoreCase("yes")) {
				recentlyEnteredData.add(new FxTradingData(++tradeNo, user.getCustomerName(), user.getCurrencyPair(), ConvertedAmount, rate));
						
				SuccessResponse successResponse = new SuccessResponse(
							"you are transferring "+formatedAmount(ConvertedAmount)+" to "+user.getCustomerName()+
							"\nClick the bellow link to BookorCancel (Enter BookorCancel in the body):",entity , 200);
					return successResponse;
							
				}
				else if(user.getGetRateYesOrNo().equalsIgnoreCase("no")) {
					recentlyEnteredData.add(new FxTradingData(++tradeNo, user.getCustomerName(), user.getCurrencyPair(), ConvertedAmount, rate));
						
					SuccessResponse successResponse = new SuccessResponse(
							"Click the link and post BookorCancel:",entity , 200);
					return successResponse;
				}
				else {
					ErrorResponse errorResponse = errorHandlerService.inValidEntry("Invalid Entry of rate", 400, "Enter yes or no");
					return errorResponse;
				}
				
		}

	
	public String formatedAmount(double amount) {
		 NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
		 return formatter.format(amount);
		
	}


	public Object bookTrade(String bookorCancel, EntityModel<FxTradeController> entity) {
		if(bookorCancel.equalsIgnoreCase("book")) {
			FxTradingData data = recentlyEnteredData.pop();
			tradeData.add(data);
			SuccessResponse successResponse = new SuccessResponse(
					"Trade for "+ data.getCurrencyPair() +" has been booked with "+data.getRate()+", The amount of Rs"
				+formatedAmount(ConvertedAmount)+ " will be transferred in 2 working days to "+ data.getCustomerName(),data ,200);
			return successResponse;
			
		}
		else if(bookorCancel.equalsIgnoreCase("cancel")) {
			recentlyEnteredData.pop();
			SuccessResponse successResponse = new SuccessResponse(
					"trade is cancelled",entity ,200);
			return successResponse;
		}
		else {
			ErrorResponse errorResponse = errorHandlerService.inValidEntry("Invalid Entry", 400, "Enter only Book or Cancel");
			return errorResponse;
		}
	}
	

}
