package com.batu.micro.common.domain.exception;

/*
 * @created 28/07/2025 ~~ 12:39
 * author: batu
 */
public abstract class DomainException extends RuntimeException{
    public DomainException(String message) {
        super(message);
    }

    public DomainException() {
    }
}
