package com.rohit.ecommerce_backend.exceptions;

public class OutOfStockException extends RuntimeException {

    public OutOfStockException(String message) {
        super(message);
    }
}
