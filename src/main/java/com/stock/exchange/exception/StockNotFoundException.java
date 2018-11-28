package com.stock.exchange.exception;

public class StockNotFoundException extends Exception {

    public StockNotFoundException(String message) {
        super(message);
    }
}
