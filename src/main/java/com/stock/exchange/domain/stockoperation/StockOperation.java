package com.stock.exchange.domain.stockoperation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StockOperation {

    private String stockCode;
    private Integer stockCount;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OperationType operationType;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private double total;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private double unitPrice;

    @JsonIgnore
    public boolean isBuy(){ return operationType.isBuy();}

    @JsonIgnore
    public boolean isSell(){ return operationType.isSell();}
}