package com.batu.demo.persistence.mapper;

import com.batu.demo.domain.aggregate.OrderAggregate;
import com.batu.demo.domain.event.DomainEvent;
import com.batu.demo.domain.event.EventId;
import com.batu.demo.persistence.entity.outbox.OrderOutboxEntity;
import com.batu.demo.domain.mapper.OrderMapper;

public final class OrderOutboxDataMapper {

    private OrderOutboxDataMapper() {
        throw new UnsupportedOperationException("Could not be reach this instance from outside of this class!");
    }

    public static DomainEvent<OrderAggregate> toDomainEvent(OrderOutboxEntity orderEventEntity) {
        return new DomainEvent<>(new EventId(orderEventEntity.getId()),
                orderEventEntity.getAggregateId(),
                OrderMapper.toDomain(orderEventEntity.getPayload()),
                orderEventEntity.getVersion());
    }

    public static OrderOutboxEntity toEntity(DomainEvent<OrderAggregate> domainEvent) {
        return OrderOutboxEntity.builder()
                .id(domainEvent.getEventId().getVal())
                .aggregateId(domainEvent.getAggregateId())
                .payload(OrderMapper.toDto(domainEvent.getPayload()))
                .eventType(domainEvent.getClass().getSimpleName())
                .version(domainEvent.getVersion())
                .build();
    }
}
