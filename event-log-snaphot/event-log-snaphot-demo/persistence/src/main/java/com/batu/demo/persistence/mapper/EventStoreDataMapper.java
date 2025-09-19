package com.batu.demo.persistence.mapper;

import com.batu.demo.domain.event.DomainEvent;
import com.batu.demo.persistence.entity.event.EventStore;

public final class EventStoreDataMapper {

    private EventStoreDataMapper() {
        throw new UnsupportedOperationException("Could not reach this class from outside !");
    }

    public static <T> DomainEvent<T> toDomainEvent(EventStore event) {
        return null;
    }

    public static <T> EventStore toEventStore(DomainEvent<T> event) {
        return EventStore.builder()
                .build();
    }
}
