package com.stock.exchange.service;

import com.stock.exchange.domain.stock.StockWithPrice;
import com.stock.exchange.domain.stockoperation.StockOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class StockOperationService {

    @Autowired
    StockService stockService;

    public StockOperation doStockOperation(@RequestBody StockOperation stockOperation) {
        StockWithPrice stock = stockService.getStock(stockOperation.getStockCode());

        if (stock == null) {
            return null;
        } else {
            Double unitPrice = stock.getPrice();
            Double total = calculateTotalAmount(stockOperation, unitPrice);

            stockOperation.setTotal(total);
            stockOperation.setUnitPrice(unitPrice);

            return stockOperation;
        }
    }

    private double calculateTotalAmount(StockOperation stockOperation, Double unitPrice) {
        unitPrice = stockOperation.isBuy() ? unitPrice * (-1) : unitPrice;

        return unitPrice * stockOperation.getStockCount();
    }
}
