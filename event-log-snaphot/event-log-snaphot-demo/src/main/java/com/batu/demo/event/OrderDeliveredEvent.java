package com.batu.demo.event;

import com.batu.demo.aggregate.OrderAggregate;

public class OrderDeliveredEvent extends DomainEvent<OrderAggregate> {
    public OrderDeliveredEvent(String id, Integer version, OrderAggregate payload) {
        super(id, version, payload);
    }
}
