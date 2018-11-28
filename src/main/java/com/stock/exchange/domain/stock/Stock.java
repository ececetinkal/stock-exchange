package com.stock.exchange.domain.stock;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "stock")
public class Stock {

    @Id
    private String code;
    private String name;

    @JsonIgnore
    public StockWithPrice getStockWithPrice(){
        return new StockWithPrice(this);
    }
}
