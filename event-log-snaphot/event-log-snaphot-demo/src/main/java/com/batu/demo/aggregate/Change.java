package com.batu.demo.aggregate;

import com.batu.demo.event.DomainEvent;
import com.batu.demo.exception.OrderAggregateException;

import java.util.ArrayList;
import java.util.List;

public class Change<T> {

    private List<DomainEvent<T>> eventList;

    public void addChange(DomainEvent<T> event) {
        if (event != null) {
            if (eventList.isEmpty()) {
                this.eventList = new ArrayList<>();
                this.eventList.add(event);
            } else {
                eventList.add(event);
            }
        } else {
            throw new OrderAggregateException("Adding changed event must not be a null value !");
        }
    }

    public List<DomainEvent<T>> getEventList() {
        return eventList;
    }


}
