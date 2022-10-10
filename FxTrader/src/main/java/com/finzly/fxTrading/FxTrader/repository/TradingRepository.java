package com.finzly.fxTrading.FxTrader.repository;

import com.finzly.fxTrading.FxTrader.entity.FxTradingData;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TradingRepository extends JpaRepository<FxTradingData, Integer>{

}
