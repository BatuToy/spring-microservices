package com.batu.demo.aggregate;

import com.batu.demo.event.LineItemAddedEvent;
import com.batu.demo.event.OrderCreatedEvent;
import com.batu.demo.event.OrderDeliveredEvent;
import com.batu.demo.event.OrderPaidEvent;
import com.batu.demo.exception.OrderAggregateException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

// POJO CLASS
public class OrderAggregate {

    private final OrderId orderId;
    private final Change<OrderAggregate> change;

    private BigDecimal totalPrice;
    private List<LineItem> items;
    private Status status;

    public OrderAggregate(BigDecimal totalPrice) {
        this.orderId = new OrderId(UUID.randomUUID());
        this.totalPrice = totalPrice;
        this.status = Status.startProcess();
        this.change = new Change<>();
        change.addChange(new OrderCreatedEvent(this.orderId.getVal().toString(), this, Change.initiateNewVersion()));
    }

    public void addItem(LineItem item) {
        addItems(List.of(item));
        this.change.addChange(new LineItemAddedEvent(this.orderId.getVal().toString(), this, Change.initiateNewVersion()));
    }

    private void addItems(List<LineItem> items) {
        if(Objects.isNull(items) || items.isEmpty()) {
            throw new OrderAggregateException("Line items could not be null or empty !");
        }
        if(Objects.isNull(this.items) || this.items.isEmpty()) {
            this.items = new ArrayList<>();
            this.items.addAll(items);
        } else {
            this.items.addAll(items);
        }
        updateOrderTotalPrice();
        validateTotalPrice();
    }

    private void validateTotalPrice() {
        if(Objects.isNull(this.totalPrice) || this.totalPrice.compareTo(BigDecimal.ZERO) < 0) {
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
        throw new OrderAggregateException("Order with order id= " + orderId.getVal() + " item is not sufficient !");
        }
    }

    private void updateOrderTotalPrice() {
        this.totalPrice = this.items.stream().map(LineItem::getSubTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void deliver() {
        if(!(this.status.equals(Status.DELIVERED))) {
            this.status = this.status.next();
            change.addChange(new OrderDeliveredEvent(this.orderId.getVal().toString(), this, Change.initiateNewVersion()));
        } else {
            throw new OrderAggregateException("Order with id= " + this.orderId.getVal() + " could not be delivered cause of already delivered !");
        }
    }

    public void pay() {
        if(this.status.equals(Status.CREATED)) {
            this.status = this.status.next();
            change.addChange(new OrderPaidEvent(orderId.getVal().toString(), this, Change.initiateNewVersion()));
        } else {
            throw new OrderAggregateException("Order with id= " + orderId.getVal() + " could not be paid cause of wrong state !");
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

    private OrderAggregate(Builder builder) {
        orderId = builder.orderId;
        change = builder.change;
        totalPrice = builder.totalPrice;
        items = builder.items;
        status = builder.status;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private OrderId orderId;
        private Change<OrderAggregate> change;
        private BigDecimal totalPrice;
        private List<LineItem> items;
        private Status status;

        public Builder orderId(OrderId val) {
            this.orderId = val;
            return this;
        }

        public Builder change(Change<OrderAggregate> change) {
            this.change = change;
            return this;
        }

        public Builder totalPrice(BigDecimal val) {
            totalPrice = val;
            return this;
        }

        public Builder items(List<LineItem> val) {
            items = val;
            return this;
        }

        public Builder status(Status val) {
            status = val;
            return this;
        }

        public OrderAggregate build() {
            return new OrderAggregate(this);
        }
    }
}
