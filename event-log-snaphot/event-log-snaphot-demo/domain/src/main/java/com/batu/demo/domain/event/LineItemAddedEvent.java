package com.batu.demo.domain.event;

import com.batu.demo.domain.aggregate.OrderAggregate;

public class LineItemAddedEvent extends DomainEvent<OrderAggregate> {
    public LineItemAddedEvent(String aggregateId, OrderAggregate payload) {
        super(aggregateId, payload);
    }
}
