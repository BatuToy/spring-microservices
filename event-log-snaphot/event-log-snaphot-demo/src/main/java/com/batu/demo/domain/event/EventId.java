package com.batu.demo.domain.event;

import java.util.Objects;
import java.util.UUID;

public class EventId {

    private final UUID val;

    public EventId(UUID id) {
        this.val = id;
    }

    public UUID getVal() {
        return val;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EventId eventId = (EventId) o;
        return Objects.equals(val, eventId.val);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(val);
    }
}
