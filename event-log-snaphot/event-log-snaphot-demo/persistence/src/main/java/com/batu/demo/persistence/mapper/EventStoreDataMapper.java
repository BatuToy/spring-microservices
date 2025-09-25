package com.batu.demo.persistence.mapper;

import com.batu.demo.domain.aggregate.OrderAggregate;
import com.batu.demo.domain.event.DomainEvent;
import com.batu.demo.domain.event.EventId;
import com.batu.demo.domain.mapper.OrderMapper;
import com.batu.demo.persistence.entity.event.OrderEventStore;

public final class EventStoreDataMapper {

    private EventStoreDataMapper() {
        throw new UnsupportedOperationException("Could not reach this class from outside !");
    }

    public static DomainEvent<OrderAggregate> toOrderDomainEvent(OrderEventStore eventEntity) {
        return new DomainEvent<>(new EventId(eventEntity.getId()),
                eventEntity.getAggregateId(),
                OrderMapper.toDomain(eventEntity.getPayload()),
                eventEntity.getVersion());
    }

    public static OrderEventStore toEventStore(DomainEvent<OrderAggregate> event) {
        return OrderEventStore.builder()
                .id(event.getEventId().getVal())
                .aggregateId(event.getAggregateId())
                .payload(OrderMapper.toDto(event.getPayload()))
                .version(event.getVersion())
                .eventName(event.getClass().getSimpleName())
                .build();
    }
}
