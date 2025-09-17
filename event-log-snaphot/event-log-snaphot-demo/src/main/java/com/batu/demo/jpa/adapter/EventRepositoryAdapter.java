package com.batu.demo.jpa.adapter;

import com.batu.demo.domain.aggregate.OrderAggregate;
import com.batu.demo.domain.event.DomainEvent;
import com.batu.demo.domain.event.EventId;
import com.batu.demo.domain.port.OutboxJpaPort;
import com.batu.demo.jpa.mapper.OrderDataMapper;
import com.batu.demo.jpa.repo.OutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EventRepositoryAdapter implements OutboxJpaPort<OrderAggregate> {

    private final OutboxRepository outboxRepository;


    @Override
    public Optional<DomainEvent<OrderAggregate>> retrieveEventByEventId(EventId eventId) {
        return outboxRepository.findEventById(eventId.getVal()).map(orderAggregateEventEntity ->
                OrderDataMapper.toDomainEvent(orderAggregateEventEntity));
    }

    @Override
    public Optional<DomainEvent<OrderAggregate>> retrieveEventByAggregateIdAndVersion(String aggregateId, Integer version) {
        return Optional.empty();
    }

    @Override
    public Optional<List<DomainEvent<OrderAggregate>>> retrieveEventsByAggregateId(String aggregateId) {
        return Optional.empty();
    }

    @Override
    public Optional<OrderAggregate> retrievePayloadByAggregateIdAndVersion(String aggregateId, Integer version) {
        return Optional.empty();
    }

    @Override
    public void saveOutboxEvent(DomainEvent<OrderAggregate> domainEvent) {
        outboxRepository.save(OrderDataMapper::toEntity)
    }
}
