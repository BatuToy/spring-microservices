package com.batu.demo.jpa.repo;

import com.batu.demo.jpa.entity.EventEntity;
import com.batu.demo.jpa.entity.OrderEventEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderOutboxRepository extends OutboxRepository {

    @Override
    Optional<EventEntity> findEventById(UUID id);

    @Override
    Optional<List<OrderEventEntity>> findByAggregateId(String aggregateId);

    @Override
    Optional<EventEntity> findEventByAggregateAndVersion(String aggregateId, Integer version);

    @Override
    Optional<String> findPayloadByAggregateAndVersion(String aggregateId, Integer version);
}
