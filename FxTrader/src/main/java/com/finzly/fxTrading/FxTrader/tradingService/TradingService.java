package com.finzly.fxTrading.FxTrader.tradingService;

import com.finzly.fxTrading.FxTrader.controller.FxTradeController;
import com.finzly.fxTrading.FxTrader.entity.User;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
@Service
public interface TradingService {
	public ResponseEntity<?> printAll();
	public ResponseEntity<?> saveTradeBeforeBooking(User user,EntityModel<FxTradeController> entity);
	public ResponseEntity<?> bookTrade(String bookorCancel, EntityModel<FxTradeController> entity);
	public ResponseEntity<?> setRate(double rate);

}
