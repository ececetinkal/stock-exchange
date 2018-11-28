package com.stock.exchange.domain.stockoperation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

public enum OperationType {
    buy,
    sell;

    public boolean isBuy() {
        return this == buy;
    }

    public boolean isSell() {
        return this == sell;
    }
}
