package com.batu.demo.event;

import com.batu.demo.aggregate.OrderAggregate;

public class OrderCreatedEvent extends DomainEvent<OrderAggregate> {
    public OrderCreatedEvent(String aggregateId, OrderAggregate payload, Integer version) {
        super(aggregateId, payload, version);
    }
}
