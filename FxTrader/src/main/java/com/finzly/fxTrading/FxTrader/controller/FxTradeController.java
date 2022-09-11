package com.finzly.fxTrading.FxTrader.controller;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.finzly.fxTrading.FxTrader.entity.FxTradingData;
import com.finzly.fxTrading.FxTrader.entity.MenuDisplayer;
import com.finzly.fxTrading.FxTrader.entity.User;
import com.finzly.fxTrading.FxTrader.errorHandler.ErrorHandlerService;
import com.finzly.fxTrading.FxTrader.fxTraderDao.BookDao;
import com.finzly.fxTrading.FxTrader.response.ErrorResponse;

@RestController
public class FxTradeController{
	@Autowired
	BookDao service;
	@Autowired
	private ErrorHandlerService errorHandlerService;
	
	public FxTradeController() {}

	@GetMapping("/menu")
	public  EntityModel<MenuDisplayer> menuDisplayer(){
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

	
	@GetMapping("/PrintTrade")
	public Object printTrade(){
		return service.printAll();
	}
	
	@PostMapping("/BookTrade")
	public Object bookTrade(@RequestBody User user){
		EntityModel<FxTradeController> enter = EntityModel.of(new FxTradeController());
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).bookOrCancel(new String()));
		enter.add(link.withRel("BookOrCancel"));
		Object bookingTrade = service.save(user, enter);
		return bookingTrade;
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
	public Object bookOrCancel(@RequestBody String bookorCancel){
		EntityModel<FxTradeController> entity = EntityModel.of(new FxTradeController());
		WebMvcLinkBuilder link4 = linkTo(methodOn(this.getClass()).menuDisplayer());
		entity.add(link4.withRel("Menu"));
		Object bookOrCancel = service.bookTrade(bookorCancel,entity);
		return bookOrCancel;
	}
	
	
	@GetMapping("/bookformat")
	public User formatOfBooking(){
		User use = new User();
		return use;
	}

}
