package com.stock.exchange.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponseBody {

    private int httpStatus;
    private String message;
    private Object data;

}
