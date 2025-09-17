package com.batu.demo.domain.event;

import java.util.UUID;

public class DomainEvent<T> {

    private final EventId id;
    private final String aggregateId;
    private final T payload;
    private Integer version;


    public DomainEvent(String aggregateId, T payload) {
        this.id = new EventId(UUID.randomUUID());
        this.payload = payload;
        this.aggregateId = aggregateId;
    }

    public DomainEvent(EventId id, String aggregateId, T payload, Integer version) {
        this.id = id;
        this.aggregateId = aggregateId;
        this.payload = payload;
        this.version = version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public EventId getEventId() {
        return id;
    }

    public String getAggregateId() {
        return aggregateId;
    }

    public T getPayload() {
        return payload;
    }

    public Integer getVersion() {
        return version;
    }
}
