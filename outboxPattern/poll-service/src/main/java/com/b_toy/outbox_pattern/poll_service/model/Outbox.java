package com.b_toy.outbox_pattern.poll_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@Table(name = "t_outbox")
@NoArgsConstructor
@Builder
public class Outbox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OUTBOX_ID")
    private Long id;
    // Order.getId().toString() -> aggregateId -> Serializing the orderId to refer this Outbox!
    @Column(name = "AGGREGATE_ID")
    private String aggregateId;
    // We want to send the payload in a String type, so we don't need to use an
    @Column(name= "PAYLOAD")
    private String payload;

    @CreatedDate
    @Column(name = "CREATED_AT")
    private LocalDateTime createdTime;

    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_DATE")
    private LocalDateTime lastModifiedDate;

    @Column(name = "IS_PROCESSED", nullable = false)
    private Boolean isProcessed;
}
