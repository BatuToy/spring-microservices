package com.batu.demo.persistence.entity.event;

import com.batu.demo.domain.dto.OrderDto;
import com.batu.demo.persistence.base_converter.OrderPayloadConverter;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@Entity(name = "event_store")
@Table(name = "t_event_store", indexes = {
        @Index(name = "idx_event_store_aggregate_id", columnList = "AGGREGATE_ID"),
        @Index(name = "idx_event_store_version", columnList = "VERSION")
})
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderEventStore {

    // Compensating and Process scenarios

    @Id
    @Column(name = "EVENT_ID")
    private UUID id;

    @Column(name = "AGGREGATE_ID")
    private String aggregateId;

    @Column(name = "PAYLOAD")
    @Convert(converter = OrderPayloadConverter.class)
    private OrderDto payload;

    @Column(name = "VERSION")
    private Integer version;

    @Column(name = "EVENT_NAME")
    private String eventName;

    @PrePersist
    private void initializeId() {
        this.id = UUID.randomUUID();
    }
}
