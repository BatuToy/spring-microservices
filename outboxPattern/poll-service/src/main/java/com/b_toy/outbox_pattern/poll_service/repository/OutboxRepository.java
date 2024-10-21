package com.b_toy.outbox_pattern.poll_service.repository;

import com.b_toy.outbox_pattern.poll_service.model.Outbox;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OutboxRepository extends JpaRepository<Outbox, Long> {
    @Query("""
        SELECT outbox
        FROM Outbox outbox
        WHERE outbox.isProcessed = false
        """)
    List<Outbox> unProcessedMessages();
}
