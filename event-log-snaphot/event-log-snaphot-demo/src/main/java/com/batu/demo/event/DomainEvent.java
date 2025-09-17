package com.batu.demo.event;

import java.util.UUID;

public abstract class DomainEvent<T> {

    private final String id;
    private T payload;


    protected DomainEvent(T payload) {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public T getPayload() {
        return payload;
    }
}
