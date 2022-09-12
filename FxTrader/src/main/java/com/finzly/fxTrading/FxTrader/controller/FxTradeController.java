package com.finzly.fxTrading.FxTrader.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.validation.Valid;

import com.finzly.fxTrading.FxTrader.entity.MenuDisplayer;
import com.finzly.fxTrading.FxTrader.entity.User;
import com.finzly.fxTrading.FxTrader.errorHandler.ErrorHandlerService;
import com.finzly.fxTrading.FxTrader.response.ErrorResponse;
import com.finzly.fxTrading.FxTrader.tradingServiceImpl.TradingServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class FxTradeController{
	@Autowired
	TradingServiceImpl service;
	@Autowired
	private ErrorHandlerService errorHandlerService;
	
	public FxTradeController() {}

	@GetMapping("/menu")
	public  EntityModel<MenuDisplayer> displayMenuWithLinks(){
		EntityModel<MenuDisplayer> entity = EntityModel.of(new MenuDisplayer("BookTrade", "PrintTrade", "Exit"));
		WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).printTrade());
		entity.add(link1.withRel("PrintTrade"));
		WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).bookTrade(new User()));
		entity.add(link2.withRel("BookTrade"));
		WebMvcLinkBuilder link3 = linkTo(methodOn(this.getClass()).exit(new String()));
		entity.add(link3.withRel("Exit"));
		WebMvcLinkBuilder link4 = linkTo(methodOn(this.getClass()).formatOfBooking());
		entity.add(link4.withRel("To know format of booking"));
		return entity;
	}
	
	@PostMapping("/BookTrade")
	public ResponseEntity<?> bookTrade(@RequestBody @Valid User user){
		EntityModel<FxTradeController> enter = EntityModel.of(new FxTradeController());
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).bookOrCancel(new String()));
		enter.add(link.withRel("BookOrCancel"));
		 ResponseEntity<?> bookingTrade = service.saveTradeBeforeBooking(user, enter);
		return bookingTrade;
	}
	
	@GetMapping("/PrintTrade")
	public Object printTrade(){
		return service.printAll();
	}

	@PostMapping("/Exit")
	public Object exit(@RequestBody String value) {
		RedirectView redirect = new RedirectView(); 
		if (value.equalsIgnoreCase("yes")) {
			redirect.setUrl("Exit/message");
			return redirect;
		}
		else if(value.equalsIgnoreCase("no")) {
			redirect.setUrl("menu");
			return redirect;
		}
		else {
			ErrorResponse errorResponse = errorHandlerService.inValidEntry("Invalid Entry", 400, "Enter yes or no");
			return errorResponse;
		}
	}
	
	@GetMapping("/Exit/message")
	public String exitMessage(){
		return "Bye Have a good day";
	}
	
	@PostMapping("/bookorCancel")
	public ResponseEntity<?> bookOrCancel(@RequestBody String bookorCancel){
		EntityModel<FxTradeController> entity = EntityModel.of(new FxTradeController());
		WebMvcLinkBuilder link4 = linkTo(methodOn(this.getClass()).displayMenuWithLinks());
		entity.add(link4.withRel("Menu"));
		ResponseEntity<?> bookOrCancel = service.bookTrade(bookorCancel,entity);
		return bookOrCancel;
	}
	
	@GetMapping("/bookformat")
	public User formatOfBooking(){
		User use = new User();
		return use;
	}
	
	@PostMapping("/RateChanger/{rate}")
	public ResponseEntity<?> changeRate(@PathVariable double rate){
		ResponseEntity<?> rateChanger =  service.setRate(rate);
		return rateChanger;
	}
	


}
