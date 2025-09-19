package com.batu.demo.domain.aggregate;

import com.batu.demo.domain.event.Change;
import com.batu.demo.domain.vo.OrderId;
import com.batu.demo.domain.vo.Status;
import com.batu.demo.domain.event.LineItemAddedEvent;
import com.batu.demo.domain.event.OrderCreatedEvent;
import com.batu.demo.domain.event.OrderDeliveredEvent;
import com.batu.demo.domain.event.OrderPaidEvent;
import com.batu.demo.domain.exception.OrderAggregateException;

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

    public OrderAggregate() {
        this.orderId = new OrderId(UUID.randomUUID());
        this.totalPrice = BigDecimal.valueOf(0L);
        this.status = Status.startProcess();
        this.change = new Change<>();
        change.addChange(new OrderCreatedEvent(this.orderId.getVal().toString(), this));
    }

    public void addItem(LineItem item) {
        initializeLineItem(item);
        if(Objects.isNull(this.items) || this.items.isEmpty()) {
            this.items = new ArrayList<>();
            this.items.add(item);
        } else {
            this.items.add(item);
        }
        updateOrderTotalPrice();
        validateTotalPrice();
        this.change.addChange(new LineItemAddedEvent(this.orderId.getVal().toString(), this));
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

    private void initializeLineItem(LineItem item) {
        long itemId;
        itemId = this.items.isEmpty() ? 1 : this.items.get(this.items.size() -1).getId().getVal() + 1L;
        item.initializeItem(orderId, itemId);
    }

    public void deliver() {
        if(!(this.status.equals(Status.DELIVERED))) {
            this.status = this.status.next();
            change.addChange(new OrderDeliveredEvent(this.orderId.getVal().toString(), this));
        } else {
            throw new OrderAggregateException("Order with id= " + this.orderId.getVal() + " could not be delivered cause of already delivered !");
        }
    }

    public void pay() {
        if(this.status.equals(Status.CREATED)) {
            this.status = this.status.next();
            change.addChange(new OrderPaidEvent(orderId.getVal().toString(), this));
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
