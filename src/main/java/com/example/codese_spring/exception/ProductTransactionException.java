package com.example.codese_spring.exception;

public class ProductTransactionException extends RuntimeException{

    public ProductTransactionException(String message) {
        super(message);
    }
}
