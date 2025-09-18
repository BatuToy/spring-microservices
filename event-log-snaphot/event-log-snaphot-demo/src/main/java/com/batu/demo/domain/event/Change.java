package com.batu.demo.domain.event;

import com.batu.demo.domain.exception.OrderAggregateException;

import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;

// Aggregate private event diff store ! In-mem to Outbox

public class Change<T> {

    private static final Integer INITIAL_VERSION = 1;

    private WeakHashMap<Integer, DomainEvent<T>> changeMap; // For caching(GC) strategy !
    private final ThreadLocal<Integer> version = ThreadLocal.withInitial(() -> INITIAL_VERSION);

    public void addChange(DomainEvent<T> event) {
        if (Objects.nonNull(event)) {
            if (Objects.isNull(this.changeMap)) {
                this.changeMap = new WeakHashMap<>();
                this.changeMap.put(version.get(), event);
                event.setVersion(version.get());
            } else if (this.changeMap.isEmpty()) {
                changeMap.put(version.get(), event);
                event.setVersion(version.get());
                version.set(version.get() + 1);
            } else {
                this.changeMap.put(version.get(), event);
                event.setVersion(version.get());
                version.set(version.get() + 1);
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
        return changeMap.get(version.get() - 1).getPayload();
    }

    public T processAggregate() {
        return changeMap.get(version.get() + 1).getPayload();
    }

}
