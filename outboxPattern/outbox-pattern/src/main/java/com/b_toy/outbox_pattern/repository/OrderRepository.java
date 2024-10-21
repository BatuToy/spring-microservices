package com.b_toy.outbox_pattern.repository;

import com.b_toy.outbox_pattern.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {}
