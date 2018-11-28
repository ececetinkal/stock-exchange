package com.stock.exchange.domain.stock;

import com.stock.exchange.util.RandomGenerator;
import lombok.Data;

@Data
public class StockWithPrice extends Stock {

    protected StockWithPrice(Stock stock){
        this.setCode(stock.getCode());
        this.setName(stock.getName());
    }

    public double getPrice(){
        return RandomGenerator.getRandomDouble();
    }
}
