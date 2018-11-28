package com.stock.exchange.service;

import com.stock.exchange.domain.stockoperation.StockOperation;
import com.stock.exchange.exception.StockNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class StockOperationService {

    @Autowired
    StockService stockService;

    public StockOperation doStockOperation(@RequestBody StockOperation stockOperation) throws StockNotFoundException {
        Double unitPrice = stockService.getStock(stockOperation.getStockCode()).getPrice();
        Double total = calculateTotalAmount(stockOperation, unitPrice);

        stockOperation.setTotal(total);
        stockOperation.setUnitPrice(unitPrice);

        return stockOperation;
    }

    private double calculateTotalAmount(StockOperation stockOperation, Double unitPrice) {
        unitPrice = stockOperation.isBuy() ? unitPrice * (-1) : unitPrice;

        return unitPrice * stockOperation.getStockCount();
    }
}
