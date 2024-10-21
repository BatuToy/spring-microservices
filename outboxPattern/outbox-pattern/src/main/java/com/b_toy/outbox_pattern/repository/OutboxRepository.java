package com.b_toy.outbox_pattern.repository;

import com.b_toy.outbox_pattern.model.Order;
import com.b_toy.outbox_pattern.model.Outbox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutboxRepository extends JpaRepository<Outbox, Long> {}
