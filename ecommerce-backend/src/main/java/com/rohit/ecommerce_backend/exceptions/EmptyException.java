package com.rohit.ecommerce_backend.exceptions;

public class EmptyException extends RuntimeException {
    public EmptyException(String message) {
        super(message);
    }
}
