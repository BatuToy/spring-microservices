package com.batu.demo.domain.port.output;

import com.batu.demo.domain.event.DomainEvent;
import com.batu.demo.domain.vo.OrderId;

import java.util.List;
import java.util.UUID;

public interface OrderEventStoreRepositoryPort<OrderAggregate> {

    DomainEvent<OrderAggregate> saveOrderEventToEventStore(DomainEvent<OrderAggregate> orderDomainEvent);

    List<DomainEvent<OrderAggregate>> retrieveAllEventsOfOrderByAggregateId(OrderId orderId);
}
