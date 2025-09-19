package com.batu.demo.persistence.repo;

import com.batu.demo.persistence.entity.order.OrderEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderJpaRepository extends JpaRepository<OrderEntity, UUID> {

    @EntityGraph(value = "order.with.items", type = EntityGraph.EntityGraphType.LOAD)
    Optional<OrderEntity> findOrderById(UUID id);
}
