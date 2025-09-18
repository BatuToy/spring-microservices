package com.batu.demo.domain.port;

import com.batu.demo.domain.event.DomainEvent;
import com.batu.demo.domain.event.EventId;
import com.batu.demo.persistence.entity.OutboxStatus;

import java.util.List;
import java.util.Optional;

public interface OutboxJpaPort<T> {

    Optional<DomainEvent<T>> retrieveEventByEventId(EventId eventId);

    Optional<DomainEvent<T>> retrieveEventByAggregateIdAndVersion(String aggregateId, Integer version);

    Optional<List<DomainEvent<T>>> retrieveEventsByAggregateId(String aggregateId);

    Optional<T> retrievePayloadByAggregateIdAndVersion(String aggregateId, Integer version);

    void saveOutboxEvent(DomainEvent<T> domainEvent);

    Optional<List<DomainEvent<T>>> retrieveEventsByOutboxStatus(OutboxStatus status);

    void updateOutboxStatusOfEvent(EventId eventId, OutboxStatus status);

    void deleteAllOutboxMessages(List<DomainEvent<T>> events);

}
