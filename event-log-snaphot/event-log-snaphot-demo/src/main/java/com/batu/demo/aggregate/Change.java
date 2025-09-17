package com.batu.demo.aggregate;

import com.batu.demo.event.DomainEvent;
import com.batu.demo.exception.OrderAggregateException;

import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;

// Aggregate private event diff store ! In-mem to Outbox

public class Change<T> {

    private static final Integer INITIAL_VERSION = 1;

    private WeakHashMap<Integer, DomainEvent<T>> changeMap; // For caching(GC) strategy !
    private final ThreadLocal<Integer> version = new ThreadLocal<>();

    public void addChange(DomainEvent<T> event) {
        if (Objects.nonNull(event)) {
            if (Objects.isNull(this.changeMap)) {
                this.changeMap = new WeakHashMap<>();
                version.set(INITIAL_VERSION);
                this.changeMap.put(version.get(), event);
            } else if (this.changeMap.isEmpty()) {
                version.set(INITIAL_VERSION);
                changeMap.put(version.get(), event);
                version.set(version.get() + 1);
            } else {
                this.version.set(version.get() + 1);
                this.changeMap.put(version.get(), event);
            }
        } else {
            throw new OrderAggregateException("Adding changed event must not be a null value !");
        }
    }

    public Map<Integer, DomainEvent<T>> getChangeMap() {
        return changeMap;
    }

    public T retrieveAggregateByVersion(Integer version) {
        return changeMap.get(version).getPayload();
    }

    public T rollbackAggregate() {
        return changeMap.get(this.version.get() - 1).getPayload();
    }

    public T processAggregate() {
        return changeMap.get(this.version.get() + 1).getPayload();
    }

}
