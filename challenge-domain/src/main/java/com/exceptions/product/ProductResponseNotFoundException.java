package com.exceptions.product;

import com.exceptions.stock.StockResponseNotFoundException;

public class ProductResponseNotFoundException extends StockResponseNotFoundException {
    public ProductResponseNotFoundException(String message) {
        super(message);
    }
}