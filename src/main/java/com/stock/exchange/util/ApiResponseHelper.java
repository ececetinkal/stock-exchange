package com.stock.exchange.util;

import com.stock.exchange.model.ApiResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponseHelper {
    public static ResponseEntity<Object> getResponseEntity(HttpStatus httpStatus, String message, Object data) {
        return ResponseEntity.status(httpStatus).body(ApiResponseBody.builder()
                .httpStatus(httpStatus.value())
                .message(message)
                .data(data)
                .build());
    }

    public static ResponseEntity<Object> getResponseEntity(HttpStatus httpStatus, String message) {
        return ResponseEntity.status(httpStatus).body(ApiResponseBody.builder()
                .httpStatus(httpStatus.value())
                .message(message)
                .build());
    }
}
