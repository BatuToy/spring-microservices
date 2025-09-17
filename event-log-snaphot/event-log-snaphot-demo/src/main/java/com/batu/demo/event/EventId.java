package com.batu.demo.event;

import java.util.Objects;
import java.util.UUID;

public class EventId {

    private final UUID id;

    public EventId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EventId eventId = (EventId) o;
        return Objects.equals(id, eventId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
