package com.batu.demo.domain.vo;

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
