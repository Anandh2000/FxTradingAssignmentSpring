package com.finzly.fxTrading.FxTrader.tradingServiceImpl;

import java.util.LinkedHashSet;
import java.util.Stack;

import com.finzly.fxTrading.FxTrader.controller.FxTradeController;
import com.finzly.fxTrading.FxTrader.entity.FxTradingData;
import com.finzly.fxTrading.FxTrader.entity.User;
import com.finzly.fxTrading.FxTrader.errorHandler.ErrorHandlerService;
import com.finzly.fxTrading.FxTrader.repository.TradingRepository;
import com.finzly.fxTrading.FxTrader.response.ErrorResponse;
import com.finzly.fxTrading.FxTrader.response.SuccessResponse;
import com.finzly.fxTrading.FxTrader.tradingService.TradingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@Component
public class TradingServiceImpl implements TradingService{
	private static int tradeNo = 0;
	private static double rate = 66.00;
	private double ConvertedAmount;
	private static Stack<FxTradingData> recentlyEnteredData = new Stack<>();

	@Autowired
	private ErrorHandlerService errorHandlerService;
	
	@Autowired
	private FxTradingData trading;
	
	@Autowired
	private TradingRepository repository;
	
	@Override
	public ResponseEntity<?> printAll(){
		if(repository.findAll().isEmpty()) {
			ErrorResponse errorResponse = errorHandlerService.emptySet("Empty set cannot be displayed", 400, null);
			return new ResponseEntity<>(errorResponse, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
		}
	}
	
	@Override
	public ResponseEntity<?> saveTradeBeforeBooking(User user,EntityModel<FxTradeController> entity) {
		ConvertedAmount = user.getAmount()*rate;
		if(user.getGetRateYesOrNo().equalsIgnoreCase("yes")) {
				recentlyEnteredData.add(new FxTradingData(++tradeNo, user.getCustomerName(), user.getCurrencyPair(), ConvertedAmount, rate));
						
				SuccessResponse successResponse = new SuccessResponse(
							"you are transferring "+trading.formatedAmount(ConvertedAmount)+" to "+user.getCustomerName()+
							"\nClick the bellow link to BookorCancel (Enter BookorCancel in the body):",entity , 200);
				return new ResponseEntity<>(successResponse, HttpStatus.OK);
							
			}
			else {
				recentlyEnteredData.add(new FxTradingData(++tradeNo, user.getCustomerName(), user.getCurrencyPair(), ConvertedAmount, rate));
						
				SuccessResponse successResponse = new SuccessResponse(
						"Click the link and post BookorCancel:",entity , 200);
				return new ResponseEntity<>(successResponse, HttpStatus.OK);
			}
				
		}
	
	@Override
	public ResponseEntity<?> bookTrade(String bookorCancel, EntityModel<FxTradeController> entity) {
		if(bookorCancel.equalsIgnoreCase("book")) {
			FxTradingData data = recentlyEnteredData.pop();
			repository.save(data);
			SuccessResponse successResponse = new SuccessResponse(
					"Trade for "+ data.getCurrencyPair() +" has been booked with "+data.getRate()+", The amount of Rs"
				+trading.formatedAmount(ConvertedAmount)+ " will be transferred in 2 working days to "+ data.getCustomerName(),data ,200);
			return new ResponseEntity<>(successResponse, HttpStatus.OK);
			
		}
		else if(bookorCancel.equalsIgnoreCase("cancel")) {
			--tradeNo;
			recentlyEnteredData.pop();
			SuccessResponse successResponse = new SuccessResponse(
					"trade is cancelled",entity ,200);
			return new ResponseEntity<>(successResponse, HttpStatus.OK);
		}
		else {
			ErrorResponse errorResponse = errorHandlerService.inValidEntry("Invalid Entry", 400, "Enter only Book or Cancel");
			return new ResponseEntity<>(errorResponse, HttpStatus.OK);
		}
	}
	
	@Override
	public ResponseEntity<?> setRate(double rate) {
		TradingServiceImpl.rate = rate;
		SuccessResponse successResponse = new SuccessResponse("Succesfully rate changed", null, 200);
		return new ResponseEntity<>(successResponse, HttpStatus.OK);
	}

	

}
