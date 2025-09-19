package com.batu.demo.persistence.mapper;

import com.batu.demo.domain.aggregate.LineItem;
import com.batu.demo.domain.aggregate.LineItemId;
import com.batu.demo.domain.aggregate.OrderAggregate;
import com.batu.demo.domain.dto.OrderLineItemDto;
import com.batu.demo.domain.vo.OrderId;
import com.batu.demo.persistence.entity.order.OrderEntity;
import com.batu.demo.persistence.entity.order.OrderLineItemEntity;
import com.batu.demo.persistence.entity.order.OrderLineItemId;

public final  class OrderDataMapper {

    private OrderDataMapper() {

    }

    // TODO= Do not fetch the Change for now !!
    public static OrderAggregate toDomain(OrderEntity order) {
        return OrderAggregate.builder()
                .orderId(new OrderId(order.getId()))
                .items(order.getItems().stream().map(OrderDataMapper::toLineItem).toList())
                .status(order.getStatus())
                .totalPrice(order.getTotalPrice())
                .build();
    }

    public static OrderEntity toEntity(OrderAggregate orderAggregate) {
        return OrderEntity.builder()
                .id(orderAggregate.getOrderId().getVal())
                .items(orderAggregate.getItems().stream().map(OrderDataMapper::toLineItemEntity).toList())
                .totalPrice(orderAggregate.getTotalPrice())
                .status(orderAggregate.getStatus())
                .build();
    }

    private static LineItem toLineItem(OrderLineItemEntity orderLineItemEntity) {
        return new LineItem(
                new LineItemId(orderLineItemEntity.getId().getId()),
                new OrderId(orderLineItemEntity.getId().getOrderId()),
                orderLineItemEntity.getProductName(),
                orderLineItemEntity.getQuantity(),
                orderLineItemEntity.getItemPrice(),
                orderLineItemEntity.getSubTotalPrice());
    }

    private static OrderLineItemEntity toLineItemEntity(LineItem item) {
        return OrderLineItemEntity.builder()
                .id(new OrderLineItemId(item.getId().getVal(), item.getOrderId().getVal()))
                .itemPrice(item.getItemPrice())
                .subTotalPrice(item.getSubTotalPrice())
                .quantity(item.getQuantity())
                .productName(item.getProductName())
                .build();
    }
}
