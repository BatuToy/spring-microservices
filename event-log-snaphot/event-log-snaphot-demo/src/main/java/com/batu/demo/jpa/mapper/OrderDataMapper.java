package com.batu.demo.jpa.mapper;

import com.batu.demo.domain.aggregate.OrderAggregate;
import com.batu.demo.domain.event.DomainEvent;
import com.batu.demo.domain.event.EventId;
import com.batu.demo.jpa.entity.OrderEventEntity;
import com.batu.demo.mapper.OrderMapper;

public final class OrderDataMapper {

    private OrderDataMapper() {
        throw new UnsupportedOperationException("Could not be reach this instance from outside of this class!");
    }

    public static DomainEvent<OrderAggregate> toDomainEvent(OrderEventEntity orderEventEntity) {
        return new DomainEvent<>(new EventId(orderEventEntity.getId()),
                orderEventEntity.getAggregateId(),
                OrderMapper.toDomain(orderEventEntity.getPayload()),
                orderEventEntity.getVersion());
    }

    public static OrderEventEntity toEntity(DomainEvent<OrderAggregate> domainEvent) {
        return OrderEventEntity.builder()
                .id(domainEvent.getEventId().getVal())
                .aggregateId(domainEvent.getAggregateId())
                .payload(OrderMapper.toDto(domainEvent.getPayload()))
                .eventType(domainEvent.getClass().getSimpleName())
                .version(domainEvent.getVersion())
                .build();
    }
}
