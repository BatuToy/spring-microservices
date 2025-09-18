package com.batu.demo.domain.dto;

import com.batu.demo.domain.event.Change;
import com.batu.demo.domain.aggregate.OrderAggregate;
import com.batu.demo.domain.vo.Status;
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

    private UUID orderId;
    private List<OrderLineItemDto> items;
    private BigDecimal totalPrice;
    private Change<OrderAggregate> eventHistory;
    private Status orderStatus;

}
