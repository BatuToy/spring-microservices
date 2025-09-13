package com.batu.demo.repo;

import com.batu.demo.event.DomainEvent;

import java.util.Map;
import java.util.UUID;

public class EventRepository {

    static Map<UUID, DomainEvent> eventRepository;

    public DomainEvent getEvent(UUID aggregateId) {
        return eventRepository.get(aggregateId);
    }

    public void saveEvent(DomainEvent event) {
        eventRepository.put(UUID.fromString(event.getId()), event);
    }
}
