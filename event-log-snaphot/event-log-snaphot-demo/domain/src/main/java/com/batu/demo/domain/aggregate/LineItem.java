package com.batu.demo.domain.aggregate;

import com.batu.demo.domain.vo.OrderId;

import java.math.BigDecimal;
import java.util.Objects;

// Sub-Entity for Order Aggregate !
public class LineItem {

    private LineItemId id;
    private OrderId orderId;

    private final String productName;
    private final Integer quantity;
    private final BigDecimal itemPrice;
    private final BigDecimal subTotalPrice;

    public LineItem(String productName, Integer quantity, BigDecimal itemPrice, BigDecimal subTotalPrice) {
        this.productName = productName;
        this.quantity = quantity;
        this.itemPrice = itemPrice;
        this.subTotalPrice = subTotalPrice;
    }

    public LineItem(LineItemId id, OrderId orderId, String productName, Integer quantity, BigDecimal itemPrice, BigDecimal subTotalPrice) {
        this.id = id;
        this.orderId = orderId;
        this.productName = productName;
        this.quantity = quantity;
        this.itemPrice = itemPrice;
        this.subTotalPrice = subTotalPrice;
    }

    boolean validateItemPrice() {
        return Objects.nonNull(this.quantity) &&
                Objects.nonNull(this.itemPrice) &&
                Objects.nonNull(this.subTotalPrice) &&
                this.itemPrice.multiply(BigDecimal.valueOf(this.quantity)).equals(this.getSubTotalPrice());
    }

    void initializeItem(OrderId orderId, Long itemId) {
        this.orderId = orderId;
        this.id = new LineItemId(itemId);
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public BigDecimal getSubTotalPrice() {
        return subTotalPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getProductName() {
        return productName;
    }

    public LineItemId getId() {
        return id;
    }

    public OrderId getOrderId() {
        return orderId;
    }
}
