package com.batu.demo.domain.event;

import com.batu.demo.domain.aggregate.OrderAggregate;

public class OrderDeliveredEvent extends DomainEvent<OrderAggregate> {
    public OrderDeliveredEvent(String aggregateId, OrderAggregate payload) {
        super(aggregateId, payload);
    }
}
