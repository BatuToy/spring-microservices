package com.batu.demo.persistence.repo;

import com.batu.demo.domain.dto.OrderDto;
import com.batu.demo.persistence.entity.outbox.OrderOutboxEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderOutboxRepository extends JpaRepository<OrderOutboxEntity, UUID> {

    @Query(value = """
            SELECT *
            FROm t_event AS e
            WHERE e.event_id = :id             
            """, nativeQuery = true)
    Optional<OrderOutboxEntity> findEventById(@Param(value = "id") UUID id);

    @Modifying
    @Query(value = "UPDATE t_order_outbox AS e SET e.outbox_status = :outboxStatus WHERE e.event_id = :eventId", nativeQuery = true)
    void updateOutboxStatusOfOrderOutboxMessage(@Param(value = "eventId") UUID eventId, @Param(value = "outboxStatus") String outboxStatus);

    @Query(value = "SELECT * FROM t_event AS e WHERE e.outbox_status = :outboxStatus", nativeQuery = true)
    Optional<List<OrderOutboxEntity>> findEventsByOutboxStatus(@Param(value = "outboxStatus") String outboxStatus);

    @Query(value = """
            SELECT *
            FROM t_event AS e
            WHERE e.aggregate_id = :aggreagateId  
            """, nativeQuery = true)
    Optional<List<OrderOutboxEntity>> findByAggregateId(@Param(value = "aggregateId") String aggregateId);

    @Query(value = """
            SELECT *
            FROM t_event AS e 
            WHERE e.aggregate_id = :aggregateId 
            AND e.version = :version            
            """, nativeQuery = true)
    Optional<OrderOutboxEntity> findEventByAggregateAndVersion(@Param(value = "aggregateId") String aggregateId, @Param(value = "version") Integer version);

    @Query(value = """
            SELECT e.payload
            FROM t_event AS e
            WHERE e.aggregate_id = :aggregateId
            AND e.version = :version
            """, nativeQuery = true)
    Optional<OrderDto> findPayloadByAggregateAndVersion(@Param(value = "aggregateId") String aggregateId, @Param(value = "version") Integer version);

}

