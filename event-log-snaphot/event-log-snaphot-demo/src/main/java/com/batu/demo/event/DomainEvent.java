package com.batu.demo.event;

import com.batu.demo.mapper.ObjectMapperUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

public abstract class DomainEvent<T> {

    private final String id;
    private final Integer version;
    private String payload;

    protected DomainEvent(String id, Integer version, T payload) {
        this.id = id;
        this.version = version;
        toPayload(payload);
    }

    private void toPayload(T o)  {
        this.payload = ObjectMapperUtil.toString(o);
    }

    public String getId() {
        return id;
    }

    public Integer getVersion() {
        return version;
    }

    public String getPayload() {
        return payload;
    }
}
