package com.batu.demo.jpa.repo;

import com.batu.demo.jpa.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OutboxRepository extends JpaRepository<EventEntity, UUID> {

    @Query(value = """
            SELECT *
            FROm t_event AS e
            WHERE e.event_id = :id             
            """, nativeQuery = true)
    Optional<EventEntity> findEventById(@Param(value = "id") UUID id);

    @Query(value = """
            SELECT *
            FROM t_event AS e
            WHERE e.aggregate_id = :aggreagateId  
            """, nativeQuery = true)
    Optional<List<EventEntity>> findByAggregateId(@Param(value = "aggregateId") String aggregateId);

    @Query(value = """
            SELECT *
            FROM t_event AS e 
            WHERE e.aggregate_id = :aggregateId 
            AND e.version = :version            
            """, nativeQuery = true)
    Optional<EventEntity> findEventByAggregateAndVersion(@Param(value = "aggregateId") String aggregateId, @Param(value = "version") Integer version);

    @Query(value = """
            SELECT e.payload
            FROM t_event AS e
            WHERE e.aggregate_id = :aggregateId
            AND e.version = :version
            """, nativeQuery = true)
    Optional<String> findPayloadByAggregateAndVersion(@Param(value = "aggregateId") String aggregateId, @Param(value = "version") Integer version);
}

