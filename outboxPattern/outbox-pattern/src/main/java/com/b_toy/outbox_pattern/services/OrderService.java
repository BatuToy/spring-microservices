package com.b_toy.outbox_pattern.services;

import com.b_toy.outbox_pattern.dto.OrderRequestDto;
import com.b_toy.outbox_pattern.dto.OrderResponseDto;

public interface OrderService {
    OrderResponseDto createOrder(OrderRequestDto orderRequestDto);
}
