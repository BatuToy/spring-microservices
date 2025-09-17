package com.batu.demo.jpa.entity;

import com.batu.demo.jpa.base_converter.OrderConverter;
import com.batu.demo.jpa.base_converter.PayloadConverter;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import java.util.UUID;

@Entity(name = "event")
@Table(name = "t_event", indexes = {
        @Index(name = "idx_event_aggregate_id", columnList = "AGGREGATE_ID"),
        @Index(name = "idx_event_version", columnList = "VERSION")
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@SuperBuilder
@MappedSuperclass
public abstract class EventEntity {

    // Multi-Aggregate Outbox Table Design

    @Id
    @Column(name = "EVENT_ID")
    private UUID id; // Primary Index for the table "t_event"

    @Column(name = "AGGREGATE_ID")
    private String aggregateId; // Aggregates reference id !

    @Column(name = "EVENT_TYPE")
    private String eventType; //  Aggregate Event Type via 'OrderLineItemAddedEvent.class.getSimpleName();'

    @Column(name = "VERSION")
    private Integer version; // Version of the aggregates event flow
}

