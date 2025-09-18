package com.batu.demo.persistence.adapter;

import com.batu.demo.domain.aggregate.OrderAggregate;
import com.batu.demo.domain.event.DomainEvent;
import com.batu.demo.domain.event.EventId;
import com.batu.demo.domain.port.OutboxJpaPort;
import com.batu.demo.persistence.entity.OutboxStatus;
import com.batu.demo.persistence.mapper.OrderDataMapper;
import com.batu.demo.persistence.repo.OrderOutboxRepository;
import com.batu.demo.domain.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component("orderOutbox")
@RequiredArgsConstructor
public class OrderOutboxRepositoryAdapter implements OutboxJpaPort<OrderAggregate> {

    private final OrderOutboxRepository orderOutboxRepository;

    @Override
    public Optional<DomainEvent<OrderAggregate>> retrieveEventByEventId(EventId eventId) {
        return orderOutboxRepository.findEventById(eventId.getVal()).map(OrderDataMapper::toDomainEvent);
    }

    @Override
    public Optional<DomainEvent<OrderAggregate>> retrieveEventByAggregateIdAndVersion(String aggregateId, Integer version) {
        return orderOutboxRepository.findEventByAggregateAndVersion(aggregateId, version).map(OrderDataMapper::toDomainEvent);
    }

    @Override
    public Optional<List<DomainEvent<OrderAggregate>>> retrieveEventsByAggregateId(String aggregateId) {
        return Optional.of(orderOutboxRepository.findByAggregateId(aggregateId).stream().flatMap(List::stream)
                .map(OrderDataMapper::toDomainEvent).toList());
    }

    @Override
    public Optional<OrderAggregate> retrievePayloadByAggregateIdAndVersion(String aggregateId, Integer version) {
        return orderOutboxRepository.findPayloadByAggregateAndVersion(aggregateId, version).map(OrderMapper::toDomain);
    }

    @Override
    public void saveOutboxEvent(DomainEvent<OrderAggregate> domainEvent) {
        orderOutboxRepository.save(OrderDataMapper.toEntity(domainEvent));
    }

    @Override
    public Optional<List<DomainEvent<OrderAggregate>>> retrieveEventsByOutboxStatus(OutboxStatus status) {
        return Optional.of(orderOutboxRepository.findEventsByOutboxStatus(status.name()).stream()
                .flatMap(List::stream)
                .map(OrderDataMapper::toDomainEvent).toList());
    }

    @Override
    public void updateOutboxStatusOfEvent(EventId eventId, OutboxStatus status) {
        orderOutboxRepository.updateOutboxStatusOfOrderOutboxMessage(eventId.getVal(), status.name());
    }

    @Override
    public void deleteAllOutboxMessages(List<DomainEvent<OrderAggregate>> events) {
        events.forEach(event ->
                orderOutboxRepository.delete(OrderDataMapper.toEntity(event)));
    }


}
