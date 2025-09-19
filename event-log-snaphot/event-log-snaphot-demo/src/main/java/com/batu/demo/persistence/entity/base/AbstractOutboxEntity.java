package com.batu.demo.persistence.entity.base;

import com.batu.demo.persistence.entity.outbox.OutboxStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import java.util.UUID;

@Entity(name = "event")
@Table(name = "t_event", indexes = {
        @Index(name = "idx_event_aggregate_id", columnList = "AGGREGATE_ID"),
})
@SuperBuilder
@MappedSuperclass
@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractOutboxEntity {

    // Multi-Aggregate Outbox Table Design

    @Id
    @Column(name = "OUTBOX_ID")
    private UUID id; // Primary Index for the table "t_event"

    @Column(name = "AGGREGATE_ID")
    private String aggregateId; // Aggregates reference id !

    @Column(name = "EVENT_TYPE")
    private String eventType; //  Aggregate Event Type via 'OrderLineItemAddedEvent.class.getSimpleName();'

    @Column(name = "VERSION")
    private Integer version; // Version of the aggregates event flow

    @Column(name = "OUTBOX_STATUS")
    @Enumerated(value = EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private OutboxStatus status;
}

