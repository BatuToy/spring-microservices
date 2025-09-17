package com.batu.demo.event;

import com.batu.demo.aggregate.OrderAggregate;

public class LineItemAddedEvent extends DomainEvent<OrderAggregate> {
    public LineItemAddedEvent(String aggregateId, OrderAggregate payload, Integer version) {
        super(aggregateId, payload, version);
    }
}
