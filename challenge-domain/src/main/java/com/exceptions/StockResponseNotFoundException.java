package com.exceptions;

public class StockResponseNotFoundException extends RuntimeException{
    public StockResponseNotFoundException(String message) {
        super(message);
    }
}