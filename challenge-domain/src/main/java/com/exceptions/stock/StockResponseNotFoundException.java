package com.exceptions.stock;

public class StockResponseNotFoundException extends RuntimeException{
    public StockResponseNotFoundException(String message) {
        super(message);
    }
}