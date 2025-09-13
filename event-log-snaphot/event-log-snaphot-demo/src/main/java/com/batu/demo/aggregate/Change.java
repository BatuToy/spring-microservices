package com.batu.demo.aggregate;

import com.batu.demo.event.DomainEvent;
import com.batu.demo.exception.OrderAggregateException;

import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;

public class Change<T> {

    private WeakHashMap<Integer, DomainEvent<T>> changeMap = new WeakHashMap<>(); // For caching(GC) strategy !
    private Integer version = 1;

    public void addChange(DomainEvent<T> event) {
        if (event != null) {
            if (Objects.isNull(this.changeMap)) {
                this.changeMap = new WeakHashMap<>();
                this.changeMap.put(version++ ,event);
            } else {
                changeMap.put(version++, event);
            }
        } else {
            throw new OrderAggregateException("Adding changed event must not be a null value !");
        }
    }

    public Map<Integer, DomainEvent<T>> getChangeMap() {
        return changeMap;
    }

    public T retrieveAggregateByVersion(Integer version, Class<T> clazz) {
        return changeMap.get(version).stringToPayload(clazz);
    }

    public String rollbackAggregate() {
        return changeMap.get(--this.version).getPayload();
    }

    public String processAggregate() {
        return changeMap.get(++this.version).getPayload();
    }

}
