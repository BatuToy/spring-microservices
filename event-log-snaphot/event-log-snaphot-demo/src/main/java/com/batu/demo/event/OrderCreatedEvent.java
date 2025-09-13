package com.batu.demo.event;

import com.batu.demo.aggregate.OrderAggregate;

public class OrderCreatedEvent extends DomainEvent<OrderAggregate> {
    public OrderCreatedEvent(String id, Integer version, OrderAggregate payload) {
        super(id, version, payload);
    }
}
