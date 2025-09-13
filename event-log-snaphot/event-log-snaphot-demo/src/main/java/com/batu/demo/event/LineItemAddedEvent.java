package com.batu.demo.event;

import com.batu.demo.aggregate.OrderAggregate;

public class LineItemAddedEvent extends DomainEvent<OrderAggregate> {
    public LineItemAddedEvent(OrderAggregate payload) {
        super(payload);
    }
}
