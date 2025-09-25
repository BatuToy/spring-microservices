package com.batu.demo.persistence.repo;

import com.batu.demo.persistence.entity.event.OrderEventStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderEventStoreRepository extends JpaRepository<OrderEventStore, UUID> {
    Optional<List<OrderEventStore>> findByAggregateId(String aggregateId);
}
