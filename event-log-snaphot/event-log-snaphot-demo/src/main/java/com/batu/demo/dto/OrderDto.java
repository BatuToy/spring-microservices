package com.batu.demo.dto;

import com.batu.demo.aggregate.Change;
import com.batu.demo.aggregate.OrderAggregate;
import com.batu.demo.aggregate.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Builder
public class OrderDto {

    private final UUID orderId;
    private final List<OrderLineItemDto> items;
    private final BigDecimal totalPrice;
    private final Change<OrderAggregate> eventHistory;
    private final Status orderStatus;

}
