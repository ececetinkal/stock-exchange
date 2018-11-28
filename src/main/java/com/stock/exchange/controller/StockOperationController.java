package com.stock.exchange.controller;

import com.stock.exchange.domain.stockoperation.OperationType;
import com.stock.exchange.domain.stockoperation.StockOperation;
import com.stock.exchange.service.StockOperationService;
import com.stock.exchange.util.ApiResponseHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "StockOperationControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping(path = "/api/operation")
@RestController
@PreAuthorize("hasAuthority('STANDARD_USER')")
public class StockOperationController {

    @Autowired
    StockOperationService stockOperationService;

    @PostMapping("/buy")
    @ApiOperation(value = "Buy Stock", authorizations = {@Authorization(value = "apiKey")})
    public ResponseEntity<Object> buyStock(@RequestBody StockOperation stockOperation) {
        stockOperation.setOperationType(OperationType.buy);
        StockOperation resultOperation = stockOperationService.doStockOperation(stockOperation);

        if(resultOperation == null){
            return ApiResponseHelper.getResponseEntity(HttpStatus.NOT_FOUND,
                    "Failed buy operation. Stock does not exist. ");
        }

        return ApiResponseHelper.getResponseEntity(HttpStatus.OK, "Bought Stock", resultOperation);
    }

    @PostMapping("/sell")
    @ApiOperation(value = "Sell Stock", authorizations = {@Authorization(value = "apiKey")})
    public ResponseEntity<Object> sellStock(@RequestBody StockOperation stockOperation)  {
        stockOperation.setOperationType(OperationType.sell);
        StockOperation resultOperation = stockOperationService.doStockOperation(stockOperation);

        if(resultOperation == null){
            return ApiResponseHelper.getResponseEntity(HttpStatus.NOT_FOUND,
                    "Failed sell operation. Stock does not exist. ");
        }

        return ApiResponseHelper.getResponseEntity(HttpStatus.OK, "Sold Stock", resultOperation);
    }
}
