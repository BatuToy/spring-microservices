package com.batu.demo.exception;

public class OrderAggregateException extends RuntimeException{

    public OrderAggregateException(String message) {
        super(message);
    }

}
