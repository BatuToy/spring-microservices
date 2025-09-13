package com.batu.demo.event;

import com.batu.demo.aggregate.OrderAggregate;

public class OrderPaidEvent extends DomainEvent<OrderAggregate>  {
    public OrderPaidEvent(String id, Integer version, OrderAggregate payload) {
        super(id, version, payload);
    }
}
