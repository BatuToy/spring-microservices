package com.batu.demo.persistence.mapper;

import com.batu.demo.domain.aggregate.OrderAggregate;
import com.batu.demo.persistence.entity.order.OrderEntity;

public final  class OrderDataMapper {

    private OrderDataMapper() {

    }

    public static OrderAggregate toDomain(OrderEntity order) {
        return OrderAggregate.builder()
                .build();
    }

    public static OrderEntity toEntity(OrderAggregate orderAggregate) {
        return OrderEntity.builder()
                .build();
    }
}
