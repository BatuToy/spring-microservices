package com.batu.demo.domain.mapper;

import com.batu.demo.domain.aggregate.LineItem;
import com.batu.demo.domain.aggregate.OrderAggregate;
import com.batu.demo.domain.vo.OrderId;
import com.batu.demo.domain.dto.OrderDto;
import com.batu.demo.domain.dto.OrderLineItemDto;

public final class OrderMapper {

    private OrderMapper() {
        throw new UnsupportedOperationException("Could not reach this instance from outside of this class!");
    }

    public static OrderDto toDto(OrderAggregate order) {
        return OrderDto.builder()
                .orderId(order.getOrderId().getVal())
                .orderStatus(order.getStatus())
                .eventHistory(order.getChange())
                .totalPrice(order.getTotalPrice())
                .items(order.getItems().stream().map(item -> {
                    return OrderLineItemDto.builder()
                            .itemPrice(item.getItemPrice())
                            .productName(item.getProductName())
                            .quantity(item.getQuantity())
                            .subTotalPrice(item.getSubTotalPrice())
                            .build();
                }).toList())
                .build();
    }

    public static OrderAggregate toDomain(OrderDto order) {
        return OrderAggregate.builder()
                .orderId(new OrderId(order.getOrderId()))
                .status(order.getOrderStatus())
                .change(order.getEventHistory())
                .totalPrice(order.getTotalPrice())
                .items(order.getItems().stream().map(item ->
                                new LineItem(item.getProductName(),
                                        item.getQuantity(),
                                        item.getItemPrice(),
                                        item.getSubTotalPrice()))
                        .toList())
                .build();
    }

}
