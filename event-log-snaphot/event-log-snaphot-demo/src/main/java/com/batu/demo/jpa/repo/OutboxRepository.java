package com.batu.demo.jpa.repo;

import com.batu.demo.jpa.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OutboxRepository extends JpaRepository<EventEntity, UUID> {
}
