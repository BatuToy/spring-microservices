package com.batu.demo.event;

import com.batu.demo.aggregate.OrderAggregate;

public class LineItemAddedEvent extends DomainEvent<OrderAggregate> {
    public LineItemAddedEvent(String id, Integer version, OrderAggregate payload) {
        super(id, version, payload);
    }
}
