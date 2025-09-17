package com.batu.demo.jpa.adapter;

import com.batu.demo.aggregate.OrderAggregate;
import com.batu.demo.aggregate.OrderEventJpaPort;
import com.batu.demo.event.DomainEvent;
import com.batu.demo.jpa.repo.OutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderEventRepositoryAdapter implements OrderEventJpaPort {

    private final OutboxRepository<OrderAggregate> outboxRepository;

    @Override
    public Optional<DomainEvent<OrderAggregate>> retrieveEventByOrderIdAndVersion(String aggregateId, Integer version) {
        return Optional.empty();
    }

    @Override
    public Optional<List<DomainEvent<OrderAggregate>>> retrieveEventsByOrderId(String aggregateId) {
        return Optional.empty();
    }

    @Override
    public Optional<OrderAggregate> retrieveOrderByOrderIdAndVersion(String aggregateId, Integer version) {
        return Optional.empty();
    }
}
