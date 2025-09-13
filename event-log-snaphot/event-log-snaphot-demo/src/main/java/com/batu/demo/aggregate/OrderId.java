package com.batu.demo.aggregate;

import java.util.UUID;

public class OrderId {

    private final UUID val;

    public OrderId(UUID orderId) {
        this.val = orderId;
    }

    public UUID getVal() {
        return val;
    }
}
