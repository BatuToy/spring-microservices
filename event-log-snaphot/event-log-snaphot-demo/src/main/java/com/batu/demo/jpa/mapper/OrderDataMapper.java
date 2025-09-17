package com.batu.demo.jpa.mapper;

import com.batu.demo.aggregate.OrderAggregate;
import com.batu.demo.event.DomainEvent;
import com.batu.demo.jpa.entity.EventEntity;

public final class OrderDataMapper {

    private OrderDataMapper() {

    }

    public static DomainEvent<OrderAggregate> toDomainEvent(EventEntity<OrderAggregate> outbox) {
        // TODO= Could make a structure for each event! Check the event type !
        return null;
    }
}
