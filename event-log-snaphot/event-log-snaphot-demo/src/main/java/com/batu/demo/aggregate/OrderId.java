package com.batu.demo.aggregate;

import java.util.UUID;

public class OrderId {

    private final UUID orderId;

    public OrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public UUID getOrderId() {
        return orderId;
    }
}
