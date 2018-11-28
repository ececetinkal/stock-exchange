package com.stock.exchange.service;

import com.stock.exchange.domain.stock.Stock;
import com.stock.exchange.domain.stock.StockWithPrice;
import com.stock.exchange.exception.StockNotFoundException;
import com.stock.exchange.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public List<StockWithPrice> getAllStocks() {
        List<Stock> stocks = stockRepository.findAll();

        return stocks.stream().map(s -> s.getStockWithPrice()).collect(Collectors.toList());
    }

    public StockWithPrice getStock(String code) throws StockNotFoundException {
        Optional<Stock> stock = stockRepository.findById(code);

        if (!stock.isPresent())
            throw new StockNotFoundException("Code: " + code);

        return stock.get().getStockWithPrice();
    }

    public Stock createStock(Stock stock) {
        return stockRepository.save(stock);
    }

    public Stock deleteStock(String code) {
        Optional<Stock> stockOptional = stockRepository.findById(code);

        if (!stockOptional.isPresent())
            return null;

        stockRepository.deleteById(code);

        return stockOptional.get();
    }

    public Stock updateStock(Stock stock, String code) {
        Optional<Stock> stockOptional = stockRepository.findById(code);

        if (!stockOptional.isPresent())
            return null;

        stock.setCode(code);
        stockRepository.save(stock);

        return stock;
    }
}
