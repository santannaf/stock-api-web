package com.exceptions.handler;

import com.exceptions.model.ErrorDetails;
import com.exceptions.stock.StockResponseNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(StockResponseNotFoundException.class)
    public ResponseEntity<?> businessExceptionNotFound(StockResponseNotFoundException exception) {
        ErrorDetails resourceNotFoundDetails = ErrorDetails.builder()
                .detail(exception.getMessage())
                .developerMessage(exception.getClass().getName())
                .status(NOT_FOUND.value())
                .title("Resource not found")
                .build();
        return new ResponseEntity<>(resourceNotFoundDetails, NOT_FOUND);
    }
}