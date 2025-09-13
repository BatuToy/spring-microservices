package com.batu.demo.event;

import com.batu.demo.mapper.ObjectMapperUtil;

import java.util.UUID;

public abstract class DomainEvent<T> {

    private final String id;

    private String payload;

    protected DomainEvent(T payload) {
        this.id = UUID.randomUUID().toString();
        // todo -> Find a generic method for mapping not Order specific!
        payloadToString(payload);
    }

    private void payloadToString(T o)  {
        this.payload = ObjectMapperUtil.toString(o);
    }

    public T stringToPayload(Class<T> clazz) {
        return ObjectMapperUtil.toObject(this.payload, clazz);
    }

    public String getId() {
        return id;
    }

    public String getPayload() {
        return payload;
    }
}
