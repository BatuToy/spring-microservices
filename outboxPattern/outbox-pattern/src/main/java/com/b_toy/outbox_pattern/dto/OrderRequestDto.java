package com.b_toy.outbox_pattern.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderRequestDto {
    private String orderName;
    private String customerId;
    private String productType;
    private Integer quantity;
    private BigDecimal price;
}
