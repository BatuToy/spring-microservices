package com.batu.demo.aggregate;

import com.batu.demo.event.LineItemAddedEvent;
import com.batu.demo.event.OrderDeliveredEvent;
import com.batu.demo.exception.OrderAggregateException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

// POJO CLASS
public class OrderAggregate {

    private final OrderId orderId;
    private List<LineItem> items;
    private final BigDecimal totalPrice;
    private Change<OrderAggregate> change;

    private Status status;

    public OrderAggregate(OrderId orderId, BigDecimal totalPrice) {
        this.orderId = orderId;
        this.totalPrice = totalPrice;
    }

    public void addItems(List<LineItem> items) {
        if(Objects.isNull(items) || items.isEmpty()) {
            throw new OrderAggregateException("Line items could not be null or empty !");
        }
        if(this.items.isEmpty()) {
            this.items = new ArrayList<>();
            this.items.addAll(items);
        } else {
            this.items.addAll(items);
        }
        validateTotalPrice();
    }

    public void addItem(LineItem item) {
        addItems(List.of(item));
        change.addChange(new LineItemAddedEvent(UUID.randomUUID().toString(), 1, this));
    }

    private void validateTotalPrice() {
        if(Objects.isNull(this.totalPrice) || this.totalPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new OrderAggregateException("Order's total price could not be null, zero or lower then zero !");
        }
        BigDecimal itemsTotal = this.items.stream().map(item -> {
            validateLineItem(item);
            return item.getSubTotalPrice();
        }).reduce(BigDecimal.ZERO, BigDecimal::add);
        if(!totalPrice.equals(itemsTotal)) {
            throw new OrderAggregateException("Order total is not equal to items total !");
        }
    }

    private void validateLineItem(LineItem item) {
        if(!item.validateItemPrice()) {
        throw new OrderAggregateException("Order with order id= " + orderId.getOrderId() + " item is not sufficient !");
        }
    }

    public void deliver() {
        if(!(this.status.equals(Status.DELIVERED))) {
            this.status = this.status.next();
            change.addChange(new OrderDeliveredEvent(UUID.randomUUID().toString(), 1, this));
        } else {
            throw new OrderAggregateException("Order with id= " + this.orderId.getOrderId() + " could not be delivered cause of already delivered !");
        }
    }

    public void pay() {
        if(this.status.equals(Status.CREATED)) {
            this.status = this.status.next();
        } else {
            throw new OrderAggregateException("Order with id= " + orderId.getOrderId() + " could not be paid cause of wrong state !");
        }
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public List<LineItem> getItems() {
        return items;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public Status getStatus() {
        return status;
    }

    public Change<OrderAggregate> getChange() {
        return change;
    }

}
