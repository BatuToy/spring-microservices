package com.batu.demo.domain.event;

import com.batu.demo.domain.aggregate.OrderAggregate;

public class OrderPaidEvent extends DomainEvent<OrderAggregate>  {
    public OrderPaidEvent(String aggregateId, OrderAggregate payload) {
        super(aggregateId, payload);
    }
}
