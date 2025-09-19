package com.batu.demo.domain.event;

import com.batu.demo.domain.aggregate.OrderAggregate;

public class OrderCreatedEvent extends DomainEvent<OrderAggregate> {
    public OrderCreatedEvent(String aggregateId, OrderAggregate payload) {
        super(aggregateId, payload);
    }
}
