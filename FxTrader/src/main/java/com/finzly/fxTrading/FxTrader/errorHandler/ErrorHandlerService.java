package com.finzly.fxTrading.FxTrader.errorHandler;

import org.springframework.stereotype.Component;

import com.finzly.fxTrading.FxTrader.response.ErrorResponse;
@Component
public class ErrorHandlerService {
	 public ErrorResponse inValidName(String message, int statusCode, Object object)
	    {
	        ErrorResponse errorResponse = new ErrorResponse(message,object,statusCode);
	        return errorResponse;
	    }

	    public ErrorResponse inValidCurrencyPair(String message,int statusCode,Object object)
	    {
	        ErrorResponse errorResponse = new ErrorResponse(message,object,statusCode);
	        return errorResponse;
	    }

	    public ErrorResponse inValidAmount(String message,int statusCode,Object object)
	    {
	        ErrorResponse errorResponse = new ErrorResponse(message,object,statusCode);
	        return errorResponse;
	    }
	    public ErrorResponse inValidEntry(String message,int statusCode,Object object)
	    {
	        ErrorResponse errorResponse = new ErrorResponse(message,object,statusCode);
	        return errorResponse;
	    }

}
