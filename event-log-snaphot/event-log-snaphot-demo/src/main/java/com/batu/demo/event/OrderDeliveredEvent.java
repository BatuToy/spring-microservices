package com.batu.demo.event;

import com.batu.demo.aggregate.OrderAggregate;

public class OrderDeliveredEvent extends DomainEvent<OrderAggregate> {
    public OrderDeliveredEvent(OrderAggregate payload) {
        super(payload);
    }
}
