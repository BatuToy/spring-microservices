package com.batu.demo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@Getter
public class OrderLineItemDto {

    private final String productName;
    private final BigDecimal itemPrice;
    private final BigDecimal subTotalPrice;
    private final Integer quantity;
}
