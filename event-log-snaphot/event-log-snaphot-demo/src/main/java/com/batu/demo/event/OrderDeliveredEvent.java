package com.batu.demo.event;

import com.batu.demo.aggregate.OrderAggregate;

public class OrderDeliveredEvent extends DomainEvent<OrderAggregate> {
    public OrderDeliveredEvent(String aggregateId, OrderAggregate payload, Integer version) {
        super(aggregateId, payload, version);
    }
}
