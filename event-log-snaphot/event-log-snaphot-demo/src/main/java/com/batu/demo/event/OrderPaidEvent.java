package com.batu.demo.event;

import com.batu.demo.aggregate.OrderAggregate;

public class OrderPaidEvent extends DomainEvent<OrderAggregate>  {
    public OrderPaidEvent(OrderAggregate payload) {
        super(payload);
    }
}
