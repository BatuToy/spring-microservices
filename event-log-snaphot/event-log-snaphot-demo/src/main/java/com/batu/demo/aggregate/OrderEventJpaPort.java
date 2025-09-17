package com.batu.demo.aggregate;

import com.batu.demo.event.DomainEvent;

import java.util.List;
import java.util.Optional;

public interface OrderEventJpaPort {

    Optional<DomainEvent<OrderAggregate>> retrieveEventByOrderIdAndVersion(String aggregateId, Integer version);

    Optional<List<DomainEvent<OrderAggregate>>> retrieveEventsByOrderId(String aggregateId);

    Optional<OrderAggregate> retrieveOrderByOrderIdAndVersion(String aggregateId, Integer version);

}
