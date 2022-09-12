package com.finzly.fxTrading.FxTrader.errorHandler;

import java.util.HashMap;
import java.util.Map;

import com.finzly.fxTrading.FxTrader.response.ErrorResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class ErrorHandlerService {
	    public ErrorResponse inValidEntry(String message,int statusCode,Object object)
	    {
	        ErrorResponse errorResponse = new ErrorResponse(message,object,statusCode);
	        return errorResponse;
	    }
	    public ErrorResponse emptySet(String message,int statusCode,Object object)
	    {
	        ErrorResponse errorResponse = new ErrorResponse(message,object,statusCode);
	        return errorResponse;
	    }
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    public HashMap<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {
	    	 HashMap<String, String> errors = new HashMap<>();
	    	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	    	        String fieldName = ((FieldError) error).getField();
	    	        String errorMessage = error.getDefaultMessage();
	    	        errors.put(fieldName, errorMessage);
	    	    });
	    	    return errors;
	    }
	    

}
