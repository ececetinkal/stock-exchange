package com.stock.exchange.controller;

import com.stock.exchange.domain.stock.Stock;
import com.stock.exchange.domain.stock.StockWithPrice;
import com.stock.exchange.exception.StockNotFoundException;
import com.stock.exchange.service.StockService;
import com.stock.exchange.util.ApiResponseHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Api(value = "StockControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping(path = "/api/")
@RestController
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/stocks")
    @ApiOperation(value = "Get All Stocks", authorizations = {@Authorization(value = "apiKey")})
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public ResponseEntity<Object> getAllStocks() {

        return ApiResponseHelper.getResponseEntity(HttpStatus.OK, "Retrieved Stocks", stockService.getAllStocks());
    }

    @GetMapping("/stocks/{code}")
    @ApiOperation(value = "Get Stock By Code", authorizations = {@Authorization(value = "apiKey")})
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public ResponseEntity<Object> retrieveStock(@PathVariable String code) {
        StockWithPrice retrievedStock;

        try {
            retrievedStock = stockService.getStock(code);
        } catch (StockNotFoundException ex) {
            return ApiResponseHelper.getResponseEntity(HttpStatus.NOT_FOUND,
                    "Stock could not be retrieved : " + ex.getMessage());
        }

        return ApiResponseHelper.getResponseEntity(HttpStatus.OK, "Retrieved Stock", retrievedStock);
    }

    @PostMapping("/stocks")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    @ApiOperation(value = "Create Stock", authorizations = {@Authorization(value = "apiKey")})
    public ResponseEntity<Object> createStock(@RequestBody Stock stock) {
        Stock createdStock = stockService.createStock(stock);

        return ApiResponseHelper.getResponseEntity(HttpStatus.CREATED, "Created Stock", createdStock);
    }

    @DeleteMapping("/stocks/{code}")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    @ApiOperation(value = "Delete Stock", authorizations = {@Authorization(value = "apiKey")})
    public ResponseEntity<Object> deleteStock(@PathVariable String code) {
        Stock deletedStock = stockService.deleteStock(code);

        if (deletedStock == null)
            return ApiResponseHelper.getResponseEntity(HttpStatus.NOT_FOUND, "Stock with the given code does not exist");

        return ApiResponseHelper.getResponseEntity(HttpStatus.NO_CONTENT, "Deleted Stock");
    }

    @PutMapping("/stocks/{code}")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    @ApiOperation(value = "Update Stock", authorizations = {@Authorization(value = "apiKey")})
    public ResponseEntity<Object> updateStock(@RequestBody Stock stock, @PathVariable String code) {
        Stock updatedStock = stockService.updateStock(stock, code);

        if (updatedStock == null)
            return ApiResponseHelper.getResponseEntity(HttpStatus.NOT_FOUND, "Stock with the given code does not exist");

        return ApiResponseHelper.getResponseEntity(HttpStatus.NO_CONTENT, "Updated Stock");
    }
}
