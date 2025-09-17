package com.batu.demo.event;

import com.batu.demo.aggregate.OrderAggregate;

public class OrderPaidEvent extends DomainEvent<OrderAggregate>  {
    public OrderPaidEvent(String aggregateId, OrderAggregate payload, Integer version) {
        super(aggregateId, payload, version);
    }
}
