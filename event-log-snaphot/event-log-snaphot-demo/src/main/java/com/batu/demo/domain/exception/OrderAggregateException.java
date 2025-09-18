package com.batu.demo.domain.exception;

public class OrderAggregateException extends RuntimeException{

    public OrderAggregateException(String message) {
        super(message);
    }

}
